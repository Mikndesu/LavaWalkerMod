package com.github.mitsukigoto.lavawalker.asm.mixin;

import com.github.mitsukigoto.lavawalker.enchantment.LavaWalkerEnchantment;
import com.github.mitsukigoto.lavawalker.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method="onChangedBlock(Lnet/minecraft/util/math/BlockPos;)V", at=@At("HEAD"))
    private void inject(BlockPos p_184594_1_, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        int k = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), livingEntity);
        if (k > 0) {
            LavaWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, p_184594_1_, k);
        }
    }
}
