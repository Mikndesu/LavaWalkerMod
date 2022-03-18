package com.github.mikn.lava_walker.config;

import com.github.mikn.lava_walker.LavaWalker;
import net.minecraftforge.common.config.Config;

public class LavaWalkerConfig {

    @Config(modid = LavaWalker.MODID, type = Config.Type.INSTANCE, name = LavaWalker.MODID + "_types")
    public static class CONFIG_TYPES {
        public static boolean affectEnchantment = true;
        @Config.RangeInt(min=1)
        public static int max_enchant_level = 2;
    }
}
