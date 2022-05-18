package com.github.mikn.lavawalker.block

import com.github.mikn.lavawalker.LavaWalker.Companion.HOLDER
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.MaterialColor


class ModdedObsidian : Block(
    Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops()
        .strength(50.0f, 1200.0f).lightLevel { 10 }) {

    companion object {
        private val AGE = IntegerProperty.create("age", 0, 3)
    }

    init {
        registerDefaultState(stateDefinition.any().setValue(AGE, Integer.valueOf(1)))
    }

    override fun tick(
        blockState: BlockState,
        serverLevel: ServerLevel,
        blockPos: BlockPos,
        randomSource: RandomSource
    ) {
        val meltProbability = HOLDER.config.module.meltSpeed.int
        if (!(randomSource.nextInt(meltProbability) == 0 && slightlyMelt(blockState, serverLevel, blockPos))) {
            serverLevel.scheduleTick(blockPos, this, Mth.nextInt(randomSource, 20, 40))
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    private fun slightlyMelt(blockState: BlockState, level: Level, blockPos: BlockPos): Boolean {
        val MAX_AGE_BEFORE_LAVA = 2
        val i = blockState.getValue(AGE)
        return if (i < MAX_AGE_BEFORE_LAVA) {
            level.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2)
            false
        } else {
            melt(level, blockPos)
            true
        }
    }

    private fun melt(level: Level, blockPos: BlockPos) {
        level.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState())
    }
}