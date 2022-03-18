package com.github.mikn.lava_walker.asm.mixin;

import com.github.mikn.lava_walker.LavaWalker;
import com.github.mikn.lava_walker.enchantment.LavaWalkerEnchantment;
import com.github.mikn.lava_walker.event.OnEntityUpdateEvent;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {
    @Redirect(method = "onEntityUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;frostWalk(Lnet/minecraft/util/math/BlockPos;)V"))
    private void redirect(EntityLivingBase instance, BlockPos pos) {
        EntityLivingBase entityLivingBase = (EntityLivingBase) (Object) this;
        MinecraftForge.EVENT_BUS.post(new OnEntityUpdateEvent(pos, entityLivingBase));
    }
}
