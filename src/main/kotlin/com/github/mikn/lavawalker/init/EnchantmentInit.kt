package com.github.mikn.lavawalker.init;

import com.github.mikn.lavawalker.enchantment.LavaWalkerEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

class EnchantmentInit {
    companion object {
        @JvmField
        val LAVA_WALKER = LavaWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET)
    }
}
