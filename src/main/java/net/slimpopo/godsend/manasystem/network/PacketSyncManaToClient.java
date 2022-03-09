package net.slimpopo.godsend.manasystem.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.manasystem.client.ClientManaData;

import java.util.function.Supplier;

public class PacketSyncManaToClient {
    private final int mana;
    private final int manaMax;
    private final int manaLvl;
    private final int soulGiven;
    private final int soulNeed;
    private final boolean hasBook;

    public PacketSyncManaToClient(int mana, int manaMax,int manaLvl,int soulGiven,int soulNeed,boolean hasBook){
        this.mana = mana;
        this.manaMax = manaMax;
        this.manaLvl = manaLvl;
        this.soulGiven = soulGiven;
        this.soulNeed = soulNeed;
        this.hasBook = hasBook;
    }

    public PacketSyncManaToClient(FriendlyByteBuf buf){
        mana = buf.readInt();
        manaMax = buf.readInt();
        manaLvl = buf.readInt();
        soulGiven = buf.readInt();
        soulNeed = buf.readInt();
        hasBook = buf.readBoolean();

    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(mana);
        buf.writeInt(manaMax);
        buf.writeInt(manaLvl);
        buf.writeInt(soulGiven);
        buf.writeInt(soulNeed);
        buf.writeBoolean(hasBook);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
           ClientManaData.set(mana,manaMax,manaLvl,soulGiven,soulNeed,hasBook);
        });
        return true;
    }
}
