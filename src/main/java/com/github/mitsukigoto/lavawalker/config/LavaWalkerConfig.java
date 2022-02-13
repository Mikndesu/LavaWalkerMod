package com.github.mitsukigoto.lavawalker.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class LavaWalkerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> max_enchantment_level;

    static {
        BUILDER.push("Config for LavaWalker Enchantment Mod");
        max_enchantment_level = BUILDER.comment("This defines the max enchantment level").define("max_enchantment_level", 2);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
