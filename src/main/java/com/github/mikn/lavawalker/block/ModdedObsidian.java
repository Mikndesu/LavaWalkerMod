package com.github.mikn.lavawalker.block;

import com.github.mikn.lavawalker.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.Random;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AGE_3;

public class ModdedObsidian extends Block {

    public ModdedObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(50.0f, 1200.0f).lightLevel((p_235435_0_) -> 10));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE_3, Integer.valueOf(1)));
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        if ((random.nextInt(3) == 0 && this.slightlyMelt(blockState, serverLevel, blockPos))) {

        } else {
            serverLevel.scheduleTick(blockPos, this, Mth.nextInt(random, 20, 40));
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_3);
    }

    private boolean slightlyMelt(BlockState blockState, Level level, BlockPos blockPos) {
        int i = blockState.getValue(AGE_3);
        if (i < 2) {
            level.setBlock(blockPos, blockState.setValue(AGE_3, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(blockState, level, blockPos);
            return true;
        }
    }

    private void melt(BlockState blockState, Level level, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState());
    }

}