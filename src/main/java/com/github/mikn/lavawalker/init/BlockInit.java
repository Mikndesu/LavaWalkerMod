package com.github.mikn.lavawalker.init;

import com.github.mikn.lavawalker.LavaWalker;
import com.github.mikn.lavawalker.block.ModdedCryingObsidian;
import com.github.mikn.lavawalker.block.ModdedObsidian;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LavaWalker.MODID);
    public static final RegistryObject<Block> MODDED_OBSIDIAN = BLOCKS.register("modded_obsidian", ModdedObsidian::new);
    public static final RegistryObject<Block> MODDED_CRYING_OBSIDIAN = BLOCKS.register("modded_crying_obsidian", ModdedCryingObsidian::new);
}