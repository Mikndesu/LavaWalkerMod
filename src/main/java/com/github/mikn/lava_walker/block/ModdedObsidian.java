package com.github.mikn.lava_walker.block;

import com.github.mikn.lava_walker.LavaWalker;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class ModdedObsidian extends Block {

    public static final PropertyInteger AGE_1 = PropertyInteger.create("age", 0, 5);

    public ModdedObsidian() {
        super(Material.ROCK);
        this.setHardness(50.0F).setResistance(2000.0F).setSoundType(SoundType.STONE);
        this.setRegistryName(LavaWalker.MODID, "modded_obsidian");
        this.setTranslationKey("modded_obsidian");
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE_1, 0));
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE_1);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE_1, MathHelper.clamp(meta, 0, 3));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(3) == 0 )
        {
            this.slightlyMelt(worldIn, pos, state, rand);
        }
        else
        {
            worldIn.scheduleUpdate(pos, this, MathHelper.getInt(rand, 20, 40));
        }
    }

    protected void slightlyMelt(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int i = (Integer) state.getValue(AGE_1);

        if (i < 4)
        {
            worldIn.setBlockState(pos, state.withProperty(AGE_1, i + 1), 2);
            worldIn.scheduleUpdate(pos, this, MathHelper.getInt(rand, 20, 40));
        }
        else
        {
            this.turnIntoLava(worldIn, pos);
        }
    }

    protected void turnIntoLava(World worldIn, BlockPos pos)
    {
        worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE_1});
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

}
