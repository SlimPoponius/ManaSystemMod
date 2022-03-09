package net.slimpopo.godsend.capability.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.slimpopo.godsend.manasystem.network.PacketSyncManaToClient;
import net.slimpopo.godsend.setup.Messages;

import javax.annotation.Nonnull;
import java.util.Random;

public class ManaManager extends SavedData {

    public ManaCapability mc = new ManaCapability();
    private int counter = 0;
    private int regenCounter = 0;

    public ManaManager(){

    }

    public ManaManager(CompoundTag tag){
        mc = new ManaCapability(tag.getInt("mana"),tag.getInt("maxmana"),
                tag.getInt("manalevel"),tag.getInt("soulgiven"),tag.getInt("soulneeded"),tag.getBoolean("hasbook") );
    }

    public int getMana(){
        return mc.getMana();
    }

    public int getManaMax(){
        return mc.getMaxMana();
    }

    public int getManaLevel(){
        return mc.getManaLevel();
    }

    public int getSoulGiven(){
        return mc.getSoulGiven();
    }

    public int getSoulNeeded(){
        return mc.getSoulNeeded();
    }

    public boolean getSpellBook(){
        return mc.spellBookCrafted();
    }

    public int levelUp(){
        int soulLeft = mc.getSoulNeeded() - (mc.getSoulGiven() + 1);
        if(soulLeft <= 0){
            mc.addManaLevel();
            setDirty();
            return 0;
        }
        else{
            mc.addSoulGiven(1);
            setDirty();
            soulLeft = mc.getSoulNeeded() - mc.getSoulGiven();
            return soulLeft;
        }
    }

    public boolean setupSpellBook(){
        if(mc.spellBookCrafted() == false){
            mc.setup();
            setDirty();
        }
        return true;

    }

    @Nonnull
    public static ManaManager get(Level level){
        if(level.isClientSide){
            throw new RuntimeException("Not accessible on client-side.");
        }
        DimensionDataStorage storage = ((ServerLevel)level).getDataStorage();
        return storage.computeIfAbsent(ManaManager::new,ManaManager::new,"manamanager");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("mana",mc.getMana());
        tag.putInt("maxmana",mc.getMaxMana());
        tag.putInt("manalevel",mc.getManaLevel());
        tag.putInt("soulgiven",mc.getSoulGiven());
        tag.putInt("soulneeded",mc.getSoulNeeded());
        tag.putBoolean("hasbook",mc.spellBookCrafted());
        return tag;
    }

    public void tick(Level world) {
        counter--;
        regenCounter--;

        if(counter <= 0){
            counter = 10;
            world.players().forEach(player ->{
                if(player instanceof ServerPlayer sPlayer){
                    int mana = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                            .orElse(0);
                    int manaLvl = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getManaLevel)
                            .orElse(0);
                    int manaMax = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMaxMana)
                            .orElse(manaLvl * 100);
                    int soul = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getSoulGiven)
                            .orElse(0);
                    int soulN = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getSoulNeeded)
                            .orElse(0);
                    boolean hasBook = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::spellBookCrafted)
                            .orElse(false);
                    System.out.println("Mana: " + mana +
                            "\nMana Max: " + manaMax +
                            "\nMana Level: " + manaLvl +
                            "\nSoul Given: " + soul +
                            "\nSoul Need: " + soulN +
                            "\nhas Spell Book: " + hasBook);
                    Messages.sendToPlayer(new PacketSyncManaToClient(mana,manaMax,manaLvl,soul,soulN,hasBook),sPlayer);
                }
            });
        }

//        if(regenCounter <= 0){
//            regenCounter = 300;
//            world.players().forEach(player -> {
//                if(player instanceof ServerPlayer sPlayer) {
//                    int manaLvl = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
//                            .map(ManaCapability::getManaLevel)
//                            .orElse(1);
//
//                    int rand = (int)(Math.random()+manaLvl);
//
//
//                }
//            });
//        }
    }
}
