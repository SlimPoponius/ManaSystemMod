package net.slimpopo.godsend.manasystem.network.spellbook;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.manasystem.client.ClientSpellBookData;
import net.slimpopo.godsend.other.SpellList;

import java.util.function.Supplier;

public class PacketSpellBookSyncToServer {
    private final String iOne, iTwo, iThree;

    public PacketSpellBookSyncToServer(String iOne, String iTwo, String iThree){
        this.iOne = iOne;
        this.iTwo = iTwo;
        this.iThree = iThree;
    }

    public PacketSpellBookSyncToServer(FriendlyByteBuf buf){
        iOne = buf.readUtf();
        iTwo = buf.readUtf();
        iThree = buf.readUtf();

    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeUtf(iOne);
        buf.writeUtf(iTwo);
        buf.writeUtf(iThree);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ClientSpellBookData.set(iOne,iTwo,iThree);
        });
        return true;
    }
}
