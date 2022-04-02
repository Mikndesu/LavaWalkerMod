package com.github.mikn.lavawalker.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;

//public interface OnChangedBlockCallback {
//
//    Event<OnChangedBlockCallback> EVENT = EventFactory.createArrayBacked(OnChangedBlockCallback.class,
//            (listeners) -> (player, sheep) -> {
//                for (OnChangedBlockCallback listener : listeners) {
//                    InteractionResult result = listener.interact(player, sheep);
//                    if(result != InteractionResult.PASS) {
//                        return result;
//                    }
//                }
//                return InteractionResult.PASS;
//            });
//
//    InteractionResult interact(BlockPos blockPos, LivingEntity livingEntity);
//}
