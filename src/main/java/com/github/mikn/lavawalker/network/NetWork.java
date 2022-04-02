//package com.github.mikn.lavawalker.network;
//
//import com.github.mikn.lavawalker.LavaWalker;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.network.NetworkRegistry;
//import net.minecraftforge.network.simple.SimpleChannel;
//
//public class NetWork {
//
//    public static final String NETWORK_VERSION = "0.1.0";
//
//    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
//            new ResourceLocation(LavaWalker.MODID, "network"), () -> NETWORK_VERSION,
//            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
//
//    public static void init() {
//        CHANNEL.registerMessage(0, Message.class, Message::encode, Message::decode, Message::handle);
//    }
//
//}