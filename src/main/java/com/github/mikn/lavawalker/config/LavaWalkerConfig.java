package com.github.mikn.lavawalker.config;

import com.github.mikn.lavawalker.LavaWalker;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = LavaWalker.MODID)
public class LavaWalkerConfig implements ConfigData {
    public boolean isTreasure = true;
    public int maxEnchantmentLevel = 2;
}
