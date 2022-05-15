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
    enum VanishmentSpeedEnum {
        SLOW, NORMAL, FAST
    }

    @Config(name = "module")
    public static class Module implements ConfigData {
        public boolean isTreasure = true;
        @RequiresRestart
        public int maxEnchantmentLevel = 2;
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        private VanishmentSpeedEnum anEnumWithButton = VanishmentSpeedEnum.NORMAL;
    }
}
