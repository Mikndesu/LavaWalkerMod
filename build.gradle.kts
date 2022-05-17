plugins {
    id("fabric-loom")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}
base {
    val archivesBaseName: String by project
    archivesName.set(archivesBaseName)
}
loom {
    mixin.defaultRefmapName.set("lava_walker.refmap.json")
}
val modVersion: String by project
version = modVersion
val mavenGroup: String by project
group = mavenGroup
repositories {
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/") {
        name="TerraformersMC"
    }
}
dependencies {
    val minecraftVersion: String by project
    minecraft("com.mojang", "minecraft", minecraftVersion)
    mappings(loom.officialMojangMappings())
    val loaderVersion: String by project
    modImplementation("net.fabricmc", "fabric-loader", loaderVersion)
    val fabricVersion: String by project
    modImplementation("net.fabricmc.fabric-api", "fabric-api", fabricVersion)
    val fabricKotlinVersion: String by project
    modImplementation("net.fabricmc", "fabric-language-kotlin", fabricKotlinVersion)
    modImplementation("com.terraformersmc:modmenu:4.0.0-beta.4")
    modApi("me.shedaniel.cloth:cloth-config-fabric:7.0.61") {
		exclude(group="net.fabricmc.fabric-api")
	}
}
tasks {
    val javaVersion = JavaVersion.VERSION_17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions { jvmTarget = javaVersion.toString() }
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
    }
    jar { from("LICENSE") { rename { "${it}_${base.archivesName}" } } }
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") { expand(mutableMapOf("version" to project.version)) }
    }
    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersion.toString())) }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        withSourcesJar()
    }
}
