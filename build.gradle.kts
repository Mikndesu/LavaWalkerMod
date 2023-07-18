import com.matthewprenger.cursegradle.CurseArtifact
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.Options

plugins {
	id("fabric-loom") version "1.1-SNAPSHOT"
	id("com.matthewprenger.cursegradle") version "1.4.0"
	id("com.modrinth.minotaur") version "2.+"
	id("maven-publish")
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

val minecraft_version: String by extra
val loader_version: String by extra
val fabric_version: String by extra
val archives_base_name: String by extra
val mod_version: String by extra
val maven_group: String by extra
val cloth_config_version: String by extra
val mod_menu_version: String by extra
val curseforge_project_id: String by extra
val modrinth_project_id: String by extra

repositories {
	maven("https://maven.shedaniel.me/")
	maven {
		name = "TerraformersMC"
		url = uri("https://maven.terraformersmc.com/")
	}
	maven {
		name = "ParchmentMC"
		 url = uri("https://maven.parchmentmc.org")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${minecraft_version}")
	mappings(loom.layered {
    	officialMojangMappings()
    	parchment("org.parchmentmc.data:parchment-1.20.1:2023.07.09@zip")
  	})
	modImplementation("net.fabricmc:fabric-loader:${loader_version}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")
	modImplementation("com.terraformersmc:modmenu:${mod_menu_version}")
	modApi("me.shedaniel.cloth:cloth-config-fabric:${cloth_config_version}") {
		exclude(group = "net.fabricmc.fabric-api")
	}
}

tasks.processResources {
	inputs.property("version", mod_version)
	filesMatching("fabric.mod.json") {
		expand(mapOf("version" to mod_version))
	}
}

tasks.withType<JavaCompile> {
	options.release.set(17)
}

java {
	withSourcesJar()
}

tasks.jar {
	from("LICENSE") {
		rename { "${it}_${archives_base_name}"}
	}
}

loom {
	mixin.defaultRefmapName.set("lava_walker.refmap.json")
}

curseforge {
    apiKey = System.getenv("curse_api_key") ?: ""
	project(closureOf<CurseProject> {
		id = curseforge_project_id
		releaseType = "release"
		addGameVersion("1.20")
		addGameVersion("1.20.1")
        addGameVersion("1.20-Snapshot")
        addGameVersion("Java 17")
        addGameVersion("Fabric")
		mainArtifact(tasks.findByName("remapJar"), closureOf<CurseArtifact>{
			displayName = "${archives_base_name}_${mod_version}"
		})
	})
    options(closureOf<Options> {
        forgeGradleIntegration = false
        javaVersionAutoDetect = false
    })
}

tasks.curseforge {
	dependsOn("build")
}

modrinth {
    token.set(System.getenv("modrinth_token"))
	projectId.set(modrinth_project_id)
    versionNumber.set(mod_version)
    versionName.set(archives_base_name)
    uploadFile.set(tasks.remapJar.get())
    gameVersions.addAll("1.20", "1.20.1")
    loaders.add("fabric")
    dependencies {
        required.project("fabric-api")
		required.project("cloth-config")
        optional.project("modmenu")
    }
}
tasks.modrinth {
	dependsOn("build")
}
