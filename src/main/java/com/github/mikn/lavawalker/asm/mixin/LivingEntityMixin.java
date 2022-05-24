package com.github.mikn.lavawalker.asm.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import com.github.mikn.lavawalker.enchantment.LavaWalkerEnchantment;
import com.github.mikn.lavawalker.init.EnchantmentInit;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onChangedBlock(Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"), cancellable = true)
    private void inject(BlockPos blockPos, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        int k = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), livingEntity);
        if (k > 0) {
            LavaWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, blockPos, k);
        }
    }
}
