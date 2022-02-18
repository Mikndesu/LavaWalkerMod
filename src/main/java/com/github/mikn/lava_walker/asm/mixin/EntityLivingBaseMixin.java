package com.github.mikn.lava_walker.asm.mixin;

import com.github.mikn.lava_walker.LavaWalker;
import com.github.mikn.lava_walker.enchantment.LavaWalkerEnchantment;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {
    @Redirect(method = "onEntityUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;frostWalk(Lnet/minecraft/util/math/BlockPos;)V"))
    private void redirect(EntityLivingBase instance, BlockPos pos) {
        EntityLivingBase entityLivingBase = (EntityLivingBase) (Object) this;
        int k = EnchantmentHelper.getMaxEnchantmentLevel(LavaWalker.LAVA_WALKER, entityLivingBase);
        if (k > 0) {
            LavaWalkerEnchantment.freezeNearby(entityLivingBase, entityLivingBase.world, pos, k);
        }
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, entityLivingBase);
        if (i > 0) {
            EnchantmentFrostWalker.freezeNearby(entityLivingBase, entityLivingBase.world, pos, i);
        }
    }
}
