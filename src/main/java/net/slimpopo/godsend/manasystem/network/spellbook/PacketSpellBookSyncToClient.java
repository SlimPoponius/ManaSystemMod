package net.slimpopo.godsend.manasystem.network.spellbook;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.slimpopo.godsend.manasystem.client.ClientManaData;
import net.slimpopo.godsend.manasystem.client.ClientSpellBookData;
import net.slimpopo.godsend.other.SpellList;

import java.util.function.Supplier;

public class PacketSpellBookSyncToClient {
    private final String iOne,iTwo,iThree;

    public PacketSpellBookSyncToClient(String iOne, String iTwo, String iThree){
        this.iOne = iOne;
        this.iTwo = iTwo;
        this.iThree = iThree;
    }

    public PacketSpellBookSyncToClient(FriendlyByteBuf buf){
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
