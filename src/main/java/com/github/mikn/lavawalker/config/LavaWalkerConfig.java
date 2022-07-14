package com.github.mikn.lavawalker.config;

import com.github.mikn.lavawalker.LavaWalker;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
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
    @RequiresRestart
    public boolean isTreasure = true;
    @RequiresRestart
    public int maxEnchantmentLevel = 2;
    @RequiresRestart
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public MeltSpeedEnum meltSpeed = MeltSpeedEnum.NORMAL;
    @RequiresRestart
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Enchantment.Rarity rarity = Enchantment.Rarity.RARE;
}
