package com.github.mikn.lava_walker.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class OnEntityUpdateEvent extends Event {
    private BlockPos blockPos;
    private EntityLivingBase livingEntity;

    public OnEntityUpdateEvent(BlockPos blockPos, EntityLivingBase livingEntity) {
        this.blockPos = blockPos;
        this.livingEntity = livingEntity;
    }

    public EntityLivingBase getLivingEntity() {
        return this.livingEntity;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }
}
