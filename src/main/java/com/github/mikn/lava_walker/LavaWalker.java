package com.github.mikn.lava_walker;

import com.github.mikn.lava_walker.block.ModdedObsidian;
import com.github.mikn.lava_walker.config.LavaWalkerConfig;
import com.github.mikn.lava_walker.enchantment.LavaWalkerEnchantment;
import com.github.mikn.lava_walker.event.OnEntityUpdateEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LavaWalker.MODID, updateJSON = LavaWalker.UPDATE_URL)
public class LavaWalker {
    public static final String MODID = "lava_walker";
    public static final String UPDATE_URL = "https://raw.githubusercontent.com/MitsukiGoto/LavaWalkerMod/1.12.2/versions/versions.json";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");

    public static final Enchantment LAVA_WALKER = new LavaWalkerEnchantment(Enchantment.Rarity.RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
    public static final Block MODDED_OBSIDIAN = new ModdedObsidian();

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
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> evt) {
        evt.getRegistry().register(new ItemBlock(MODDED_OBSIDIAN).setRegistryName(MODDED_OBSIDIAN.getRegistryName()).setTranslationKey("modded_obsidian"));
    }

    @SubscribeEvent
    public void updateCheck(PlayerEvent.PlayerLoggedInEvent evt) {
        ModContainer container = Loader.instance().getIndexedModList().get(MODID);
        ForgeVersion.Status status = ForgeVersion.getResult(container).status;
        LOGGER.info(status);
        if(status == ForgeVersion.Status.OUTDATED) {
            evt.player.sendMessage(new TextComponentString("LavaWalker Mod: New Version Available!"));
        }
    }

    @SubscribeEvent
    public void onChangedBlock(OnEntityUpdateEvent evt) {
        BlockPos blockPos = evt.getBlockPos();
        EntityLivingBase entityLivingBase = evt.getLivingEntity();
        if (!LavaWalkerConfig.CONFIG_TYPES.affectEnchantment) {
            evt.setCanceled(true);
            return;
        }
        int k = EnchantmentHelper.getMaxEnchantmentLevel(LavaWalker.LAVA_WALKER, entityLivingBase);
        if (k > 0) {
            LavaWalkerEnchantment.freezeNearby(entityLivingBase, entityLivingBase.world, blockPos, k);
        }
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, entityLivingBase);
        if (i > 0) {
            EnchantmentFrostWalker.freezeNearby(entityLivingBase, entityLivingBase.world, blockPos, i);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent evt) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MODDED_OBSIDIAN), 0, new ModelResourceLocation(new ResourceLocation("lava_walker", "modded_obsidian"), "inventory"));
    }
}
