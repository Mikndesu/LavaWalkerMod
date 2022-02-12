package com.github.mitsukigoto.lavawalker;

import com.github.mitsukigoto.lavawalker.init.BlockInit;
import com.github.mitsukigoto.lavawalker.init.EnchantmentInit;
import com.github.mitsukigoto.lavawalker.init.ItemInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(LavaWalker.MODID)
public class LavaWalker {

    public static final String MODID = "lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");

    public LavaWalker() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HisabisaConfig.SPEC, "hisabisamod-common.toml");
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityMoved(LivingEvent.LivingUpdateEvent evt) {
//        LivingEntity entityLiving = evt.getEntityLiving();
//        if (!(entityLiving instanceof PlayerEntity)) {
//            return;
//        }
//        ItemStack boots = entityLiving.getItemBySlot(EquipmentSlotType.FEET);
//        int isEnchantedFrostWalker = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FROST_WALKER, boots);
//        if(isEnchantedFrostWalker==0&&HisabisaConfig.isAlways_frosted_walk.get()) {
//            FrostWalkerEnchantment.onEntityMoved(entityLiving, entityLiving.getCommandSenderWorld(), entityLiving.blockPosition(),2);
//        }
//        int isEnchantedLavaWalker = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), boots);
//        if(isEnchantedLavaWalker==0&&HisabisaConfig.isAlways_lava_walk.get()) {
//            LavaWalkerEnchantment.onEntityMoved(entityLiving, entityLiving.getCommandSenderWorld(), entityLiving.blockPosition(),2);
//        }
    }

}
