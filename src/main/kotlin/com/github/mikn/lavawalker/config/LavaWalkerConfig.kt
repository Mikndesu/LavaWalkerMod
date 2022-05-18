package com.github.mikn.lavawalker.config

import com.github.mikn.lavawalker.LavaWalker
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.*
import me.shedaniel.autoconfig.serializer.PartitioningSerializer.GlobalData

@Config(name = LavaWalker.MODID)
class LavaWalkerConfig : GlobalData() {
    @ConfigEntry.Category("module")
    @TransitiveObject
    var module = Module()

    enum class MeltSpeedEnum(val int: Int) {
        SLOW(4), NORMAL(3), FAST(2);
    }

    @Config(name = "module")
    class Module : ConfigData {
        var isTreasure = true

        @RequiresRestart
        var maxEnchantmentLevel = 2

        @EnumHandler(option = EnumHandler.EnumDisplayOption.BUTTON)
        var meltSpeed = MeltSpeedEnum.NORMAL
    }
}
