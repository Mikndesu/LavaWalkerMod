package com.github.mikn.lavawalker.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext.Builder;

import java.util.List;
import java.util.Random;

import com.github.mikn.lavawalker.config.LavaWalkerConfig;

public class ModdedObsidian extends Block {

    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public ModdedObsidian() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                .strength(-1.0F, 3600000.0F).lightLevel((p_235435_0_) -> 10));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(1)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getDestroyProgress(BlockState p_60466_, Player p_60467_, BlockGetter p_60468_, BlockPos p_60469_) {
        // This value is the same as that of obsidian
        final float HARDNESS = 50.0f;
        return LavaWalkerConfig.shouldDropObsidian.get() ? HARDNESS : super.getDestroyProgress(p_60466_, p_60467_, p_60468_, p_60469_);
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<ItemStack> getDrops(BlockState blockState, Builder p_60538_) {
        var list = super.getDrops(blockState, p_60538_);
        return LavaWalkerConfig.shouldDropObsidian.get() ? determineDrop(blockState) : list;
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        int meltProbability = LavaWalkerConfig.meltSpeed.get().getInt(); 
        if (!((random.nextInt(meltProbability) == 0 && this.slightlyMelt(blockState, serverLevel, blockPos)))) {
            serverLevel.scheduleTick(blockPos, this, Mth.nextInt(random, 20, 40));
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

    private List<ItemStack> determineDrop(BlockState blockState) {
        int age = blockState.getValue(AGE);
        // ModdedObsidian with age 0 or 1 looks obsidian, with other ages looks crying obsidian.
        return (age==0||age==1) ? List.of(new ItemStack(Items.OBSIDIAN)) : List.of(new ItemStack(Items.CRYING_OBSIDIAN));
    }

}