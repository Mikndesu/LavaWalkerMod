package com.github.mikn.lavawalker.init;

import com.github.mikn.lavawalker.enchantment.LavaWalkerEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentInit {
    public static final Enchantment LAVA_WALKER = new LavaWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET);
}
