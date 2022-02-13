package com.github.mikn.lavawalker.init;

import com.github.mikn.lavawalker.LavaWalker;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LavaWalker.MODID);
    public static final RegistryObject<Item> MODDED_OBSIDIAN = ITEMS.register("modded_obsidian", () -> new BlockItem(BlockInit.MODDED_OBSIDIAN.get(), new Item.Properties()));
    public static final RegistryObject<Item> MODDED_CRYING_OBSIDIAN = ITEMS.register("modded_crying_obsidian", () -> new BlockItem(BlockInit.MODDED_CRYING_OBSIDIAN.get(), new Item.Properties()));
}
