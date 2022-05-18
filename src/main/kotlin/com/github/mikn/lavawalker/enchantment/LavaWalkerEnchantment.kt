package com.github.mikn.lavawalker.enchantment

import com.github.mikn.lavawalker.LavaWalker.Companion.HOLDER
import com.github.mikn.lavawalker.init.BlockInit
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.util.Mth
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.material.Material
import net.minecraft.world.phys.shapes.CollisionContext

class LavaWalkerEnchantment(rarity: Rarity?, vararg equipmentSlots: EquipmentSlot?) :
    Enchantment(rarity, EnchantmentCategory.ARMOR_FEET, equipmentSlots) {
    override fun getMinCost(p_45017_: Int): Int {
        return p_45017_ * 10
    }

    override fun getMaxCost(p_45027_: Int): Int {
        return getMinCost(p_45027_) + 15
    }

    override fun isTreasureOnly(): Boolean {
        return HOLDER.config.module.isTreasure
    }

    override fun getMaxLevel(): Int {
        return HOLDER.config.module.maxEnchantmentLevel
    }

    public override fun checkCompatibility(enchantment: Enchantment): Boolean {
        // Fix bug: LavaWalker wrongly had been compatible with FrostWalker.
        return super.checkCompatibility(enchantment) && enchantment !== Enchantments.DEPTH_STRIDER && enchantment !== Enchantments.FROST_WALKER
    }

    companion object {
        @JvmStatic
        fun onEntityMoved(livingEntity: LivingEntity, level: Level, blockPos: BlockPos, enchantmentLevel: Int) {
            if (livingEntity.isOnGround) {
                val blockstate = BlockInit.MODDED_OBSIDIAN.defaultBlockState()
                val effectiveRadius = (2 + enchantmentLevel).toFloat()
                val `blockpos$mutableblockpos` = MutableBlockPos()
                for (blockpos in BlockPos.betweenClosed(
                    blockPos.offset((-effectiveRadius).toDouble(), -1.0, (-effectiveRadius).toDouble()),
                    blockPos.offset(effectiveRadius.toDouble(), -1.0, effectiveRadius.toDouble())
                )) {
                    if (blockpos.closerToCenterThan(livingEntity.position(), effectiveRadius.toDouble())) {
                        `blockpos$mutableblockpos`[blockpos.x, blockpos.y + 1] = blockpos.z
                        val blockstate1 = level.getBlockState(`blockpos$mutableblockpos`)
                        if (blockstate1.isAir) {
                            val blockstate2 = level.getBlockState(blockpos)
                            val isFull = (blockstate2.block === Blocks.LAVA
                                    && blockstate2.getValue(LiquidBlock.LEVEL) == 0)
                            if (blockstate2.material == Material.LAVA && isFull
                                && blockstate.canSurvive(level, blockpos)
                                && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty())
                            ) {
                                level.setBlockAndUpdate(blockpos, blockstate)
                                level.scheduleTick(
                                    blockpos, BlockInit.MODDED_OBSIDIAN,
                                    Mth.nextInt(level.getRandom(), 20, 40)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
