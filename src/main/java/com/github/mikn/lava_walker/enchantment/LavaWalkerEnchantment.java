package com.github.mikn.lava_walker.enchantment;

import com.github.mikn.lava_walker.LavaWalker;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class LavaWalkerEnchantment extends Enchantment {
    public LavaWalkerEnchantment(Enchantment.Rarity rarityIn, EntityEquipmentSlot... slots) {
        super(rarityIn, EnumEnchantmentType.ARMOR_FEET, slots);
        this.setRegistryName("lava_walker", "lava_walker");
        this.setName("lava_walker");
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    public int getMaxLevel() {
        return 2;
    }

    public static void freezeNearby(EntityLivingBase living, World worldIn, BlockPos pos, int level) {
        if (living.onGround) {
            float f = 2 + level;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double) (-f), -1.0D, (double) (-f)), pos.add((double) f, -1.0D, (double) f))) {
                if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double) (f * f)) {
                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.AIR) {
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);

                        if (iblockstate1.getMaterial() == Material.LAVA && (iblockstate1.getBlock() == net.minecraft.init.Blocks.LAVA || iblockstate1.getBlock() == Blocks.FLOWING_LAVA) && ((Integer) iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.mayPlace(LavaWalker.MODDED_OBSIDIAN, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity) null)) {
                            worldIn.setBlockState(blockpos$mutableblockpos1, LavaWalker.MODDED_OBSIDIAN.getDefaultState());
                            worldIn.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), LavaWalker.MODDED_OBSIDIAN, MathHelper.getInt(living.getRNG(), 20, 40));
                        }
                    }
                }
            }
        }
    }

    public boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment) && enchantment != Enchantments.DEPTH_STRIDER && enchantment != Enchantments.FROST_WALKER;
    }
}
