package com.github.mikn.lavawalker.init;

import com.github.mikn.lavawalker.LavaWalker;
import com.github.mikn.lavawalker.block.ModdedObsidian;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ItemInit {
    public static final Item MODDED_OBSIDIAN = new BlockItem(BlockInit.MODDED_OBSIDIAN, new Item.Properties());
}
