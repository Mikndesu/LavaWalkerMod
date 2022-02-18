package com.github.mikn.lavawalker.enchantment;

import com.github.mikn.lavawalker.config.LavaWalkerConfig;
import com.github.mikn.lavawalker.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

public class LavaWalkerEnchantment extends Enchantment {

    public LavaWalkerEnchantment(Enchantment.Rarity p_i46728_1_, EquipmentSlotType... p_i46728_2_) {
        super(p_i46728_1_, EnchantmentType.ARMOR_FEET, p_i46728_2_);
    }

    public int getMinCost(int p_77321_1_) {
        return p_77321_1_ * 10;
    }

    public int getMaxCost(int p_223551_1_) {
        return this.getMinCost(p_223551_1_) + 15;
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public int getMaxLevel() {
        return LavaWalkerConfig.max_enchantment_level.get();
    }

    public static void onEntityMoved(LivingEntity livingEntity, World world, BlockPos blockPos, int level) {
        if (livingEntity.isOnGround()) {
            BlockState blockstate = BlockInit.MODDED_OBSIDIAN.get().defaultBlockState();
            float f = (float) (2 + level);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            for (BlockPos blockpos : BlockPos.betweenClosed(blockPos.offset((double) (-f), -1.0D, (double) (-f)), blockPos.offset((double) f, -1.0D, (double) f))) {
                if (blockpos.closerThan(livingEntity.position(), (double) f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = world.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir(world, blockpos$mutable)) {
                        BlockState blockstate2 = world.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(FlowingFluidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(world, blockpos) && world.isUnobstructed(blockstate, blockpos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(livingEntity, net.minecraftforge.common.util.BlockSnapshot.create(world.dimension(), world, blockpos), net.minecraft.util.Direction.UP)) {
                            world.setBlockAndUpdate(blockpos, blockstate);
                            world.getBlockTicks().scheduleTick(blockpos, BlockInit.MODDED_OBSIDIAN.get(), MathHelper.nextInt(livingEntity.getRandom(), 10, 20));
                        }
                    }
                }
            }
        }
    }

    public boolean checkCompatibility(Enchantment p_77326_1_) {
        return super.checkCompatibility(p_77326_1_) && p_77326_1_ != Enchantments.FROST_WALKER;
    }
}

