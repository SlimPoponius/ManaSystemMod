package net.slimpopo.godsend.manasystem.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;

import java.util.function.Supplier;

public class PacketSpellBookPlayerHandler {

    public PacketSpellBookPlayerHandler(){

    }

    public PacketSpellBookPlayerHandler(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();


            boolean spellbook = ManaManager.get(player.level).setupSpellBook();
            int mCur= ManaManager.get(player.level).getMana();
            int mMax= ManaManager.get(player.level).getManaMax();
            int mLvl= ManaManager.get(player.level).getManaLevel();

            int sGiv= ManaManager.get(player.level).getSoulGiven();
            int sNdd= ManaManager.get(player.level).getSoulNeeded();

            if(spellbook == false && !player.level.isClientSide){
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                    playerMana.setMana(mCur);
                    playerMana.setMaxMana(mMax);
                    playerMana.setLevel(mLvl);
                    playerMana.setSoulGiven(sGiv);
                    playerMana.setSoulNeeded(sNdd);
                    playerMana.setSpellBookCrafted(spellbook);
                    player.sendMessage(new TextComponent("A great power awoken within you"),
                            player.getUUID());
                });
            }
        });
        return true;
    }
}
