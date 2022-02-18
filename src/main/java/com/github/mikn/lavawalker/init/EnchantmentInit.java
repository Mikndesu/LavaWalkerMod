package com.github.mikn.lavawalker.init;

import com.github.mikn.lavawalker.LavaWalker;
import com.github.mikn.lavawalker.enchantment.LavaWalkerEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, LavaWalker.MODID);
    public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker", () -> new LavaWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));
}
