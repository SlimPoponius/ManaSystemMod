package net.slimpopo.godsend.manasystem.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.manasystem.client.ClientManaData;

import java.util.function.Supplier;

public class PacketManaSyncToServer {
    private final int mana;
    private final int manaMax;
    private final int manaLvl;
    private final int soulGiven;
    private final int soulNeed;


    public PacketManaSyncToServer(int mana, int manaMax,int manaLvl,int soulGiven,int soulNeed){
        this.mana = mana;
        this.manaLvl = manaLvl;
        this.soulGiven = soulGiven;
        this.soulNeed = soulNeed;

        this.manaMax = manaMax;
    }

    public PacketManaSyncToServer(FriendlyByteBuf buf){
        mana = buf.readInt();
        manaMax = buf.readInt();
        manaLvl = buf.readInt();
        soulGiven = buf.readInt();
        soulNeed = buf.readInt();


    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(mana);
        buf.writeInt(manaMax);
        buf.writeInt(manaLvl);
        buf.writeInt(soulGiven);
        buf.writeInt(soulNeed);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ClientManaData.set(mana,manaMax,manaLvl,soulGiven,soulNeed);
        });
        return true;
    }
}
