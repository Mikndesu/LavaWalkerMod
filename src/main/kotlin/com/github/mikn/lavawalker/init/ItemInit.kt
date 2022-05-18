package com.github.mikn.lavawalker.init

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item

class ItemInit {
    companion object {
        @JvmField
        val MODDED_OBSIDIAN = BlockItem(BlockInit.MODDED_OBSIDIAN, Item.Properties())
    }
}