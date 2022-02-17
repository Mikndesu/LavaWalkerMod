package com.github.mikn.lava_walker;

import com.github.mikn.lava_walker.block.ModdedCryingObsidian;
import com.github.mikn.lava_walker.block.ModdedObsidian;
import com.github.mikn.lava_walker.enchantment.LavaWalkerEnchantment;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LavaWalker.MODID)
public class LavaWalker {
    public static final String MODID="lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");

    public static final Enchantment LAVA_WALKER = new LavaWalkerEnchantment(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET});
    public static final Block MODDED_OBSIDIAN = new ModdedObsidian();
    public static final Block MODDED_CRYING_OBSIDIAN = new ModdedCryingObsidian();

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> evt) {
        evt.getRegistry().register(LAVA_WALKER);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> evt) {
        evt.getRegistry().register(MODDED_OBSIDIAN);
        evt.getRegistry().register(MODDED_CRYING_OBSIDIAN);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> evt) {
    }
}
