package com.github.mikn.lavawalker.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class LavaWalkerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> max_enchantment_level;
    public static final ForgeConfigSpec.ConfigValue<Boolean> affectEnchantment;

    static {
        BUILDER.push("Config for LavaWalker Enchantment Mod");
        max_enchantment_level = BUILDER.comment("This defines the max enchantment level").define("max_enchantment_level", 2);
        affectEnchantment = BUILDER.comment("This defines whether your enchantment affects or not").define("affectEnchantment", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
