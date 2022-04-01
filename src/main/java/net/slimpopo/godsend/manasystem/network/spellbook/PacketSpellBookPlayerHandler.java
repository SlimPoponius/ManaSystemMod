package net.slimpopo.godsend.manasystem.network.spellbook;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.capability.spellbook.SpellBookManager;
import net.slimpopo.godsend.capability.spellbook.SpellBookProvider;
import net.slimpopo.godsend.other.SpellList;

import java.util.function.Supplier;

public class PacketSpellBookPlayerHandler {
    private String spellOne, spellTwo, spellThree;

    public PacketSpellBookPlayerHandler(String a, String b, String c){
        this.spellOne = a;
        this.spellTwo = b;
        this.spellThree = c;

    }

    public PacketSpellBookPlayerHandler(FriendlyByteBuf buf){

    }

    public PacketSpellBookPlayerHandler() {

    }

    public void encode(String one, String two, String three){
        spellOne = one;
        spellTwo = two;
        spellThree = three;

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();

            String spella = SpellBookManager.get(player.level).getSpellOne();
            String spellb = SpellBookManager.get(player.level).getSpellTwo();
            String spellc = SpellBookManager.get(player.level).getSpellThree();

            if(!player.level.isClientSide){
                player.getCapability(SpellBookProvider.SPELLBOOK_CAP).ifPresent(playerSpellBook -> {
                    playerSpellBook.setSp1(spella);
                    playerSpellBook.setSp2(spellb);
                    playerSpellBook.setSp3(spellc);
                });
            }
        });
        return true;
    }
}
