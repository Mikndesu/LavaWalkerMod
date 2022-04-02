package com.github.mikn.lavawalker;

import com.github.mikn.lavawalker.init.BlockInit;
import com.github.mikn.lavawalker.init.EnchantmentInit;
import com.github.mikn.lavawalker.init.ItemInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LavaWalker implements ModInitializer {

    public static final String MODID = "lava_walker";
    public static final Logger LOGGER = LogManager.getLogger("LavaWalker/Main");
//    public static KeyMapping[] keyMappings;
//    public static ArrayList<UUID> availablePlayers;
//    private boolean isEnchantmentAvailable = true;

    @Override
    public void onInitialize() {
        Registry.register(Registry.ENCHANTMENT, new ResourceLocation(LavaWalker.MODID, "lava_walker"), EnchantmentInit.LAVA_WALKER);
        Registry.register(Registry.BLOCK, new ResourceLocation(LavaWalker.MODID, "modded_obsidian"), BlockInit.MODDED_OBSIDIAN);
        Registry.register(Registry.ITEM, new ResourceLocation(LavaWalker.MODID, "modded_obsidian"), ItemInit.MODDED_OBSIDIAN);
    }

//    private void doClientStuff(final FMLClientSetupEvent event) {
//        keyMappings = new KeyMapping[1];
//        keyMappings[0] = new KeyMapping("Enable/Disable LavaWalker", GLFW.GLFW_KEY_J, "LavaWalker");
//        ClientRegistry.registerKeyBinding(keyMappings[0]);
//    }
//
//    private void commonSetup(final FMLCommonSetupEvent evt) {
//        NetWork.init();
//        availablePlayers = new ArrayList<>();
//    }

//    @SubscribeEvent
//    public void toggleAffect(InputEvent.KeyInputEvent evt) {
//        if(keyMappings[0].isDown()) {
//            if(isEnchantmentAvailable) {
//                sendClientMessage("LavaWalker Enchantment is unavailable now");
//            } else {
//                sendClientMessage("LavaWalker Enchantment is available now");
//            }
//            NetWork.CHANNEL.sendToServer(new Message(1));
//            isEnchantmentAvailable = !isEnchantmentAvailable;
//        }
//    }

//    @SubscribeEvent
//    public void updateCheck(PlayerEvent.PlayerLoggedInEvent evt) {
//        ModContainer container = ModList.get().getModContainerById(MODID).get();
//        VersionChecker.Status status = VersionChecker.getResult(container.getModInfo()).status();
//        LOGGER.info(status);
//        if(status == VersionChecker.Status.OUTDATED) {
//            Player player = evt.getPlayer();
//            player.sendMessage(new TextComponent("LavaWalker Mod: New Version Available!"), player.getUUID());
//        }
//    }

//    @SubscribeEvent
//    public void onChangedBlock(OnChangedBlockEvent evt) {
//        BlockPos blockPos = evt.getBlockPos();
//        LivingEntity livingEntity = evt.getLivingEntity();
//        if (livingEntity instanceof Player && !isEnchantmentAvailable || availablePlayers.stream().anyMatch(s -> s.equals(livingEntity.getUUID()))) {
//            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FROST_WALKER, livingEntity);
//            if (i > 0) {
//                FrostWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, blockPos, i);
//            }
//            evt.setCanceled(true);
//            return;
//        }
//        int k = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), livingEntity);
//        if (k > 0) {
//            LavaWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, blockPos, k);
//        }
//    }

//    public static void sendClientMessage(String message) {
//        LocalPlayer player = Minecraft.getInstance().player;
//        player.sendMessage(new TextComponent(message),player.getUUID());
//    }

}