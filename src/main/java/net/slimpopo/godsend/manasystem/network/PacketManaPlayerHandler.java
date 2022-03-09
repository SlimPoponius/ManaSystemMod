package net.slimpopo.godsend.manasystem.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;

import java.util.function.Supplier;

public class PacketManaPlayerHandler {

    public PacketManaPlayerHandler(){

    }

    public PacketManaPlayerHandler(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();


            boolean spellbook = ManaManager.get(player.level).getSpellBook();
            int mCur = ManaManager.get(player.level).getMana();
            int mMax = ManaManager.get(player.level).getManaMax();
            int mLvl = ManaManager.get(player.level).getManaLevel();
            int sGiv = ManaManager.get(player.level).getSoulGiven();
            int sNdd = ManaManager.get(player.level).getSoulNeeded();
            int canLevelUp = ManaManager.get(player.level).levelUp();


            if(spellbook == true && !player.level.isClientSide){
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                    if(canLevelUp <= 0) {
                        playerMana.setMana(mCur);
                        playerMana.setMaxMana(mMax);
                        playerMana.setLevel(mLvl);
                        playerMana.setSoulGiven(sGiv);
                        playerMana.setSoulNeeded(sNdd);
                        player.sendMessage(new TextComponent("You have now reached LEVEL " + playerMana.getManaLevel()),
                                player.getUUID());
                    }
                    else{
                        playerMana.setSoulGiven(sGiv);
                        player.sendMessage(new TextComponent("To next Level: " + canLevelUp),
                                player.getUUID());
                        playerMana.setSoulNeeded(canLevelUp);
                    }

                });
            }
            else{
                player.sendMessage(new TextComponent("You don't have access to you SpellBook yet to gain power."),player.getUUID());
            }

        });
        return true;
    }
}
