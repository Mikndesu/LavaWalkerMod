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

    protected FrostWalkerEnchantmentMixin(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Inject(method = "checkCompatibility(Lnet/minecraft/world/item/enchantment/Enchantment;)Z", at = @At("RETURN"), cancellable = true)
    private void inject(Enchantment p_77326_1_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.checkCompatibility(p_77326_1_) && p_77326_1_ != Enchantments.DEPTH_STRIDER && p_77326_1_ != EnchantmentInit.LAVA_WALKER);
    }
}
