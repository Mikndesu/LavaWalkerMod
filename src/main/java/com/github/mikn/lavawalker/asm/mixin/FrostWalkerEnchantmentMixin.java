package com.github.mikn.lavawalker.asm.mixin;

import com.github.mikn.lavawalker.init.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerEnchantmentMixin extends Enchantment {

    protected FrostWalkerEnchantmentMixin(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] equipmentSlots) {
        super(rarity, category, equipmentSlots);
    }

    @Inject(method = "checkCompatibility(Lnet/minecraft/world/item/enchantment/Enchantment;)Z", at = @At("RETURN"), cancellable = true)
    private void inject(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.checkCompatibility(enchantment) && enchantment != Enchantments.DEPTH_STRIDER && enchantment != EnchantmentInit.LAVA_WALKER);
    }
}
