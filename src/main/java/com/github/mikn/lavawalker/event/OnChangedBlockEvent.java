package com.github.mikn.lavawalker.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class OnChangedBlockEvent extends Event {

    private BlockPos blockPos;
    private LivingEntity livingEntity;

    public OnChangedBlockEvent(BlockPos blockPos, LivingEntity livingEntity) {
        this.blockPos = blockPos;
        this.livingEntity = livingEntity;
    }

    public LivingEntity getLivingEntity() {
        return this.livingEntity;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

}
