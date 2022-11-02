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

package com.github.mikn.lavawalker.enchantment;

import com.github.mikn.lavawalker.LavaWalker;
import com.github.mikn.lavawalker.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;

public class LavaWalkerEnchantment extends Enchantment {
    public LavaWalkerEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.ARMOR_FEET, equipmentSlots);
    }

    public int getMinCost(int p_45017_) {
        return p_45017_ * 10;
    }

    public int getMaxCost(int p_45027_) {
        return this.getMinCost(p_45027_) + 15;
    }

    public boolean isTreasureOnly() {
        return LavaWalker.HOLDER.isTreasure;
    }

    public int getMaxLevel() {
        return LavaWalker.HOLDER.maxEnchantmentLevel;
    }

    public static void onEntityMoved(LivingEntity livingEntity, Level level, BlockPos blockPos, int enchantmentLevel) {
        if (livingEntity.isOnGround()) {
            BlockState blockstate = BlockInit.MODDED_OBSIDIAN.defaultBlockState();
            float effectiveRadius = 2 + enchantmentLevel;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            for (BlockPos blockpos : BlockPos.betweenClosed(
                    blockPos.offset((double) (-effectiveRadius), -1.0D, (double) (-effectiveRadius)),
                    blockPos.offset((double) effectiveRadius, -1.0D, (double) effectiveRadius))) {
                if (blockpos.closerToCenterThan(livingEntity.position(), (double) effectiveRadius)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = level.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA
                                && blockstate2.getValue(LiquidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.LAVA && isFull
                                && blockstate.canSurvive(level, blockpos)
                                && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty())) {
                            if(LavaWalker.HOLDER.isCompatibleWithBedrock) {
                                blockstate = Blocks.FROSTED_ICE.defaultBlockState();
                                level.setBlockAndUpdate(blockpos, blockstate);
                                level.scheduleTick(blockpos, Blocks.FROSTED_ICE,
                                    Mth.nextInt(level.getRandom(), 20, 40));
                            } else {
                                level.setBlockAndUpdate(blockpos, blockstate);
                                level.scheduleTick(blockpos, BlockInit.MODDED_OBSIDIAN,
                                    Mth.nextInt(level.getRandom(), 20, 40));
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkCompatibility(Enchantment enchantment) {
        // Fix bug: LavaWalker wrongly had been compatible with FrostWalker.
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.DEPTH_STRIDER
                && LavaWalker.HOLDER.shouldExclusiveWithFrost ? enchantment != Enchantments.FROST_WALKER : true;
    }
}
