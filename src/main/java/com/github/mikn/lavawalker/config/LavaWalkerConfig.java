/*
 Copyright (c) 2022 Mikndesu

 Permission is hereby granted, free of charge, to any person obtaining a copy of
 this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to
 use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 the Software, and to permit persons to whom the Software is furnished to do so,
 subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.mikn.lavawalker.config;

import net.minecraft.world.item.enchantment.Enchantment;
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
    public static final ForgeConfigSpec.ConfigValue<Boolean> exclusiveWithFrostWalker;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isTreasure;
    public static final ForgeConfigSpec.EnumValue<MeltSpeedEnum> meltSpeed;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> rarity;

    static {
        BUILDER.push("Config for LavaWalker Enchantment Mod");
        exclusiveWithFrostWalker = BUILDER.comment("").define("exclusiveWithFrostWalker", true);
        isTreasure = BUILDER.comment("").define("isTreasure", true);
        max_enchantment_level = BUILDER.comment("Maximum Level of Lavawalker").define("max_enchantment_level", 2);
        meltSpeed = BUILDER.comment("How fast obsidians melt").defineEnum("meltSpeed", MeltSpeedEnum.NORMAL);
        rarity = BUILDER.comment("").defineEnum("rarity", Enchantment.Rarity.RARE);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
