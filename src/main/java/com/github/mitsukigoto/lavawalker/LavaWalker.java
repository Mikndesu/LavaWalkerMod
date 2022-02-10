package com.github.mitsukigoto.lavawalker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LavaWalker.MODID)
public class LavaWalker {
    public static final String MODID="lava_walker";
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        System.out.println("Hello world!");
    }
}
