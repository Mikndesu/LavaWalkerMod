/*
 Copyright (c) 2022 Mikndesu

 Permission is hereby granted, free of charge, to any person obtaining a copy of
 this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to
 use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 the Software, and to permit persons to whom the Software is furnished to do so,
 subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.mikn.lavawalker.block;

import java.util.Random;

import com.github.mikn.lavawalker.config.LavaWalkerConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ModdedObsidian extends Block {

    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public ModdedObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops()
                .strength(50.0f, 1200.0f).lightLevel((p_235435_0_) -> 10));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(1)));
    }

    public void tick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        int meltProbability = LavaWalkerConfig.meltSpeed.get().getInt();
        if (!((random.nextInt(meltProbability) == 0 && this.slightlyMelt(blockState, serverWorld, blockPos)))) {
            serverWorld.getBlockTicks().scheduleTick(blockPos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    private boolean slightlyMelt(BlockState blockState, World world, BlockPos blockPos) {
        final int MAX_AGE_BEFORE_LAVA = 2;
        int i = blockState.getValue(AGE);
        if (i < MAX_AGE_BEFORE_LAVA) {
            world.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(blockState, world, blockPos);
            return true;
        }
    }

    private void melt(BlockState blockState, World world, BlockPos blockPos) {
        world.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState());
    }

}