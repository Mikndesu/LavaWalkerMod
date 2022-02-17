package com.github.mikn.lava_walker.asm.mixin;

import com.github.mikn.lava_walker.LavaWalker;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentFrostWalker.class)
public abstract class FrostWalkerEnchantmentMixin extends Enchantment{
    protected FrostWalkerEnchantmentMixin(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }
    @Inject(method= "canApplyTogether(Lnet/minecraft/enchantment/Enchantment;)Z", at=@At("RETURN"), cancellable = true)
    private void inject(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.canApplyTogether(enchantment) && enchantment != Enchantments.DEPTH_STRIDER && enchantment != LavaWalker.LAVA_WALKER);
    }
}