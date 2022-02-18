package com.github.mikn.lavawalker.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.AGE_1;

public class ModdedCryingObsidian extends Block {

    public ModdedCryingObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(50.0f, 1200.0f).lightLevel((p_235435_0_) -> 10));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE_1, Integer.valueOf(1)));
    }

    @Override
    public void tick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        if ((random.nextInt(3) == 0 && this.slightlyMelt(blockState, serverWorld, blockPos))) {

        } else {
            serverWorld.getBlockTicks().scheduleTick(blockPos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE_1);
    }

    private boolean slightlyMelt(BlockState blockState, World world, BlockPos blockPos) {
        int i = blockState.getValue(AGE_1);
        if (i < 1) {
            world.setBlock(blockPos, blockState.setValue(AGE_1, Integer.valueOf(i + 1)), 2);
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
