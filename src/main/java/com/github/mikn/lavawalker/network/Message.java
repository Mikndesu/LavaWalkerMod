//package com.github.mikn.lavawalker.network;
//
//import com.github.mikn.lavawalker.LavaWalker;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraftforge.network.NetworkEvent;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.UUID;
//import java.util.function.Supplier;
//
//public class Message {
//
//    public int key;
//
//    public Message(int key) {
//        this.key = key;
//    }
//
//    public static void encode(Message message, FriendlyByteBuf buffer) {
//        buffer.writeInt(message.key);
//    }
//
//    public static Message decode(FriendlyByteBuf buffer) {
//        return new Message(buffer.readInt());
//    }
//
//    public static void handle(Message message, Supplier<NetworkEvent.Context> contextSupplier) {
//        NetworkEvent.Context context = contextSupplier.get();
//        context.enqueueWork(() -> {
//            ServerPlayer serverPlayer = context.getSender();
//            UUID playerUUID = serverPlayer.getUUID();
//            if(LavaWalker.availablePlayers.stream().anyMatch(s->s.equals(playerUUID))) {
//                LavaWalker.availablePlayers.remove(playerUUID);
//            } else {
//                LavaWalker.availablePlayers.add(playerUUID);
//            }
//        });
//        context.setPacketHandled(true);
//    }
//}