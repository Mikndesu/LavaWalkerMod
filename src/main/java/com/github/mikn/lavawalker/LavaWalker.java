package com.github.mikn.lavawalker;

import com.github.mikn.lavawalker.config.LavaWalkerConfig;
import com.github.mikn.lavawalker.enchantment.LavaWalkerEnchantment;
import com.github.mikn.lavawalker.event.OnChangedBlockEvent;
import com.github.mikn.lavawalker.init.BlockInit;
import com.github.mikn.lavawalker.init.EnchantmentInit;
import com.github.mikn.lavawalker.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.VersionChecker;
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
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LavaWalkerConfig.SPEC, "lava_walker-common.toml");
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void updateCheck(PlayerEvent.PlayerLoggedInEvent evt) {
        ModContainer container = ModList.get().getModContainerById("lava_walker").get();
        VersionChecker.Status status = VersionChecker.getResult(container.getModInfo()).status;
        LOGGER.info(status);
        if(status == VersionChecker.Status.OUTDATED) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            player.sendMessage(new StringTextComponent("LavaWalker Mod: New Version Available!"),player.getUUID());
        }
    }

    @SubscribeEvent
    public void onChangedBlock(OnChangedBlockEvent evt) {
        BlockPos blockPos = evt.getBlockPos();
        LivingEntity livingEntity = evt.getLivingEntity();
        if (!LavaWalkerConfig.affectEnchantment.get()) {
            evt.setCanceled(true);
            return;
        }
        int k = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), livingEntity);
        if (k > 0) {
            LavaWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, blockPos, k);
        }
    }
}
