package com.github.mikn.lavawalker.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import com.github.mikn.lavawalker.LavaWalker;

public class ModdedObsidian extends Block {

    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public ModdedObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops()
                .strength(50.0f, 1200.0f).lightLevel((p_235435_0_) -> 10));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(1)));
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int meltProbability = LavaWalker.HOLDER.getConfig().module.meltSpeed.getInt();
        if (!((randomSource.nextInt(meltProbability) == 0 && this.slightlyMelt(blockState, serverLevel, blockPos)))) {
            serverLevel.scheduleTick(blockPos, this, Mth.nextInt(randomSource, 20, 40));
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    private boolean slightlyMelt(BlockState blockState, Level level, BlockPos blockPos) {
        final int MAX_AGE_BEFORE_LAVA = 2;
        int i = blockState.getValue(AGE);
        if (i < MAX_AGE_BEFORE_LAVA) {
            level.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2);
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