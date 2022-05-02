package com.github.mikn.lavawalker;

import com.github.mikn.lavawalker.init.BlockInit;
import com.github.mikn.lavawalker.init.EnchantmentInit;
import com.github.mikn.lavawalker.init.ItemInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LavaWalker implements ModInitializer {

    public static final String MODID = "lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");

    @Override
    public void onInitialize() {
        Registry.register(Registry.ENCHANTMENT, new ResourceLocation(LavaWalker.MODID, "lava_walker"), EnchantmentInit.LAVA_WALKER);
        Registry.register(Registry.BLOCK, new ResourceLocation(LavaWalker.MODID, "modded_obsidian"), BlockInit.MODDED_OBSIDIAN);
        Registry.register(Registry.ITEM, new ResourceLocation(LavaWalker.MODID, "modded_obsidian"), ItemInit.MODDED_OBSIDIAN);
    }

}