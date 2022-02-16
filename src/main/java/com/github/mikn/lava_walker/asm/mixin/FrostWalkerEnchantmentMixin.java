package com.github.mikn.lava_walker.asm.mixin;

import com.github.mikn.lava_walker.LavaWalker;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class FrostWalkerEnchantmentMixin {
    @Inject(method= "run()V", at=@At("HEAD"))
    private void inject(CallbackInfo ci) {
        LavaWalker.LOGGER.info("run");
    }
}