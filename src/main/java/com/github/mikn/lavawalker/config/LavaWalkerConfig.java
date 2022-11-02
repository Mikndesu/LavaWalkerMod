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

import com.github.mikn.lavawalker.LavaWalker;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.world.item.enchantment.Enchantment;

@Config(name = LavaWalker.MODID)
public class LavaWalkerConfig implements ConfigData {
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
    public boolean isMinable = false;
    @RequiresRestart
    public boolean isTreasure = true;
    public boolean shouldExclusiveWithFrost = true;
    @Comment("""
            Make Sure Your world has no modded blocks 
            that may cause vanilla clients crash.
            This option make Frostwalker have an 
            ability of Lavawalker and generated 
            frosted ices turn to be lava.
            """)
    public boolean isCompatibleWithVanilla = true;
    @RequiresRestart
    public int maxEnchantmentLevel = 2;
    @RequiresRestart
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public MeltSpeedEnum meltSpeed = MeltSpeedEnum.NORMAL;
    @RequiresRestart
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Enchantment.Rarity rarity = Enchantment.Rarity.RARE;
}
