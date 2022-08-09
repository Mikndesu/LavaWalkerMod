package com.github.mikn.lavawalker.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class LavaWalkerConfig {

    public enum MeltSpeedEnum {
        VERY_SLOW(5), SLOW(4), NORMAL(3), FAST(2), VERY_FAST(1);
        private final int value;
        MeltSpeedEnum(final int value) {
            this.value = value;
        }
        public int getInt() {
            return this.value;
        }
    }

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> max_enchantment_level;
    public static final ForgeConfigSpec.EnumValue<MeltSpeedEnum> meltSpeed;
    public static final ForgeConfigSpec.BooleanValue shouldDropObsidian;

    static {
        BUILDER.push("Config for LavaWalker Enchantment Mod");
        max_enchantment_level = BUILDER.comment("This defines the max enchantment level").define("max_enchantment_level", 2);
        meltSpeed = BUILDER.comment("You can choose speed from VERY_FAST FAST NORMAL SLOW VERY_SLOW").defineEnum("meltSpeed", MeltSpeedEnum.NORMAL);
        shouldDropObsidian = BUILDER.comment("This field determines whether obsidians that Lavawalker generates should drop").define("should_drop", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
