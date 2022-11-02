package com.github.mikn.lavawalker.asm.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mikn.lavawalker.LavaWalker;

@Mixin(IceBlock.class)
public class IceBlockMixin {
    @Inject(method = "melt(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"), cancellable = true)
    private void inject(BlockState state, Level level, BlockPos blockPos, CallbackInfo ci) {
        if (LavaWalker.HOLDER.isCompatibleVanilla) {
            BlockState blockState = level.getBlockState(blockPos.below());
            if (blockState.is(Blocks.LAVA)) {
                level.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState());
                ci.cancel();
            }
        }
    }
}
