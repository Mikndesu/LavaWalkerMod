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

import com.github.mikn.lavawalker.config.LavaWalkerConfig;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

public class LavaWalkerEnchantment extends Enchantment {
    public LavaWalkerEnchantment(Enchantment.Rarity p_45013_, EquipmentSlot... p_45014_) {
        super(p_45013_, EnchantmentCategory.ARMOR_FEET, p_45014_);
    }

    public int getMinCost(int p_45017_) {
        return p_45017_ * 10;
    }

    public int getMaxCost(int p_45027_) {
        return this.getMinCost(p_45027_) + 15;
    }

    public boolean isTreasureOnly() {
        return LavaWalkerConfig.isTreasure.get();
    }

    public int getMaxLevel() {
        return LavaWalkerConfig.max_enchantment_level.get();
    }

    @Override
    public Enchantment.Rarity getRarity() {
        return LavaWalkerConfig.rarity.get();
    }

    public static void onEntityMoved(LivingEntity livingEntity, Level level, BlockPos blockPos, int enchantmentLevel) {
        if (!livingEntity.isOnGround()) {
            return;
        }
        BlockState blockState = BlockInit.MODDED_OBSIDIAN.get().defaultBlockState();
        int effectiveRadius = 2 + enchantmentLevel;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (BlockPos blockPos2 : BlockPos.betweenClosed(blockPos.offset(-effectiveRadius, -1, -effectiveRadius),
                blockPos.offset(effectiveRadius, -1, effectiveRadius))) {
            BlockState blockState3;
            if (!blockPos2.closerToCenterThan(livingEntity.position(), effectiveRadius))
                continue;
            mutableBlockPos.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
            BlockState blockState2 = level.getBlockState(mutableBlockPos);
            if (!blockState2.isAir()
                    || (blockState3 = level.getBlockState(blockPos2)) != Blocks.LAVA.defaultBlockState()
                    || !blockState.canSurvive(level, blockPos2)
                    || !level.isUnobstructed(blockState, blockPos2, CollisionContext.empty()))
                continue;
            level.setBlockAndUpdate(blockPos2, blockState);
            level.scheduleTick(blockPos2, Blocks.FROSTED_ICE, Mth.nextInt(livingEntity.getRandom(), 60, 120));
        }
    }

    public boolean checkCompatibility(Enchantment p_45024_) {
        return super.checkCompatibility(p_45024_) && p_45024_ != Enchantments.DEPTH_STRIDER
                && LavaWalkerConfig.exclusiveWithFrostWalker.get() ? p_45024_ != Enchantments.FROST_WALKER : true;
    }
}
