package com.github.mikn.lavawalker

import com.github.mikn.lavawalker.config.LavaWalkerConfig
import com.github.mikn.lavawalker.init.BlockInit
import com.github.mikn.lavawalker.init.EnchantmentInit
import com.github.mikn.lavawalker.init.ItemInit
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigHolder
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager

class LavaWalker: ModInitializer {

    companion object {
        const val MODID = "lava_walker"
        @JvmStatic
        val LOGGER = LogManager.getLogger("LavaWalker/Main")
        @JvmStatic
        val HOLDER: ConfigHolder<LavaWalkerConfig> = AutoConfig.register(LavaWalkerConfig::class.java, ::JanksonConfigSerializer)
    }

    override fun onInitialize() {
        Registry.register(Registry.ENCHANTMENT, ResourceLocation(MODID, "lava_walker"), EnchantmentInit.LAVA_WALKER)
        Registry.register(Registry.BLOCK, ResourceLocation(MODID, "modded_obsidian"), BlockInit.MODDED_OBSIDIAN)
        Registry.register(Registry.ITEM, ResourceLocation(MODID, "modded_obsidian"), ItemInit.MODDED_OBSIDIAN)
    }

}