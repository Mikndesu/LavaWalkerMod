package com.github.mikn.lava_walker.block;

import com.github.mikn.lava_walker.LavaWalker;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModdedObsidian extends Block {
    public ModdedObsidian() {
        super(Material.ROCK);
        this.setHardness(50.0F).setResistance(2000.0F).setSoundType(SoundType.STONE);
        this.setRegistryName(LavaWalker.MODID, "modded_obsidian");
        this.setTranslationKey("modded_obsidian");
    }
}
