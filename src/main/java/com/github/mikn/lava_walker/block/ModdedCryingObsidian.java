package com.github.mikn.lava_walker.block;
import com.github.mikn.lava_walker.LavaWalker;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModdedCryingObsidian extends Block {
    public ModdedCryingObsidian() {
        super(Material.ROCK);
        this.setHardness(50.0F).setResistance(2000.0F).setSoundType(SoundType.STONE);
        this.setRegistryName(LavaWalker.MODID, "modded_crying_obsidian");
        this.setTranslationKey("modded_crying_obsidian");
    }
}
