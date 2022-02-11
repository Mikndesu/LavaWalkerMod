package com.github.mitsukigoto.lavawalker.init;

import com.github.mitsukigoto.lavawalker.LavaWalker;
import com.github.mitsukigoto.lavawalker.enchantment.LavaWalkerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, LavaWalker.MODID);
    public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker", () -> new LavaWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.FEET));
}
