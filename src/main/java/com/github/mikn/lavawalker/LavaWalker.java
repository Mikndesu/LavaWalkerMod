package com.github.mikn.lavawalker;

import com.github.mikn.lavawalker.init.EnchantmentInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(LavaWalker.MODID)
public class LavaWalker {

    public static final String MODID = "lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");

    public LavaWalker() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HisabisaConfig.SPEC, "hisabisamod-common.toml");
        EnchantmentInit.ENCHANTMENTS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

}