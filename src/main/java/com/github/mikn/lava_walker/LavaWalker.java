package com.github.mikn.lava_walker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LavaWalker.MODID)
public class LavaWalker {
    public static final String MODID="lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        System.out.println("Hello world!");
    }
}
