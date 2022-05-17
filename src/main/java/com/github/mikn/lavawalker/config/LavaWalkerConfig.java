package com.github.mikn.lavawalker.config;

import com.github.mikn.lavawalker.LavaWalker;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.TransitiveObject;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = LavaWalker.MODID)
public class LavaWalkerConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("module")
    @TransitiveObject
    public Module module = new Module();
    public enum MeltSpeedEnum {
        SLOW(4), NORMAL(3), FAST(2);
        private final int value;
        MeltSpeedEnum(final int value) {
            this.value = value;
        }
        public int getInt() {
            return this.value;
        }
    }

    @Config(name = "module")
    public static class Module implements ConfigData {
        public boolean isTreasure = true;
        @RequiresRestart
        public int maxEnchantmentLevel = 2;
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public MeltSpeedEnum meltSpeed = MeltSpeedEnum.NORMAL;
    }
}
