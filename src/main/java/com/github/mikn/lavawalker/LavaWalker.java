package com.github.mikn.lavawalker;

import com.github.mikn.lavawalker.config.LavaWalkerConfig;
import com.github.mikn.lavawalker.enchantment.LavaWalkerEnchantment;
import com.github.mikn.lavawalker.event.OnChangedBlockEvent;
import com.github.mikn.lavawalker.init.BlockInit;
import com.github.mikn.lavawalker.init.EnchantmentInit;
import com.github.mikn.lavawalker.init.ItemInit;
import com.github.mikn.lavawalker.network.Message;
import com.github.mikn.lavawalker.network.NetWork;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;


@Mod(LavaWalker.MODID)
public class LavaWalker {

    public static final String MODID = "lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");
    public static KeyMapping[] keyMappings;
    public static ArrayList<UUID> availablePlayers;
    private boolean isEnchantmentAvailable = true;

    public LavaWalker() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LavaWalkerConfig.SPEC, "lava_walker-common.toml");
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        keyMappings = new KeyMapping[1];
        keyMappings[0] = new KeyMapping("Enable/Disable LavaWalker", GLFW.GLFW_KEY_J, "LavaWalker");
        ClientRegistry.registerKeyBinding(keyMappings[0]);
    }

    private void commonSetup(final FMLCommonSetupEvent evt) {
        NetWork.init();
        availablePlayers = new ArrayList<>();
    }

    @SubscribeEvent
    public void toggleAffect(InputEvent.KeyInputEvent evt) {
        if(keyMappings[0].isDown()) {
            if(isEnchantmentAvailable) {
                sendClientMessage("LavaWalker Enchantment is unavailable now");
            } else {
                sendClientMessage("LavaWalker Enchantment is available now");
            }
            NetWork.CHANNEL.sendToServer(new Message(1));
            isEnchantmentAvailable = !isEnchantmentAvailable;
        }
    }

    @SubscribeEvent
    public void updateCheck(PlayerEvent.PlayerLoggedInEvent evt) {
        ModContainer container = ModList.get().getModContainerById(MODID).get();
        VersionChecker.Status status = VersionChecker.getResult(container.getModInfo()).status();
        LOGGER.info(status);
        if(status == VersionChecker.Status.OUTDATED) {
            Player player = evt.getPlayer();
            player.sendMessage(new TextComponent("LavaWalker Mod: New Version Available!"), player.getUUID());
        }
    }

    @SubscribeEvent
    public void onChangedBlock(OnChangedBlockEvent evt) {
        BlockPos blockPos = evt.getBlockPos();
        LivingEntity livingEntity = evt.getLivingEntity();
        if (livingEntity instanceof Player && !isEnchantmentAvailable || availablePlayers.stream().anyMatch(s -> s.equals(livingEntity.getUUID()))) {
            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FROST_WALKER, livingEntity);
            if (i > 0) {
                FrostWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, blockPos, i);
            }
            evt.setCanceled(true);
            return;
        }
        int k = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), livingEntity);
        if (k > 0) {
            LavaWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, blockPos, k);
        }
    }

    private void sendClientMessage(String message) {
        LocalPlayer player = Minecraft.getInstance().player;
        player.sendMessage(new TextComponent(message),player.getUUID());
    }

}