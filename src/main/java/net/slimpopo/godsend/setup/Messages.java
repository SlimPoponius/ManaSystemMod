package net.slimpopo.godsend.setup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.manasystem.network.PacketManaPlayerHandler;
import net.slimpopo.godsend.manasystem.network.PacketSyncManaToClient;

public class Messages {

    private static SimpleChannel CHANNEL;

    private static int packetId = 0;
    private static int id(){return packetId++;}

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(GodSend.MOD_ID,"messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        CHANNEL = net;

        net.messageBuilder(PacketManaPlayerHandler.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketManaPlayerHandler::new)
                .encoder(PacketManaPlayerHandler::toBytes)
                .consumer(PacketManaPlayerHandler::handle)
                .add();

        net.messageBuilder(PacketSyncManaToClient.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncManaToClient::new)
                .encoder(PacketSyncManaToClient::toBytes)
                .consumer(PacketSyncManaToClient::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        CHANNEL.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),message);
    }
}
