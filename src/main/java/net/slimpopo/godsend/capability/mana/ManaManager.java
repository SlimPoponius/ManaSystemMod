package net.slimpopo.godsend.capability.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
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
        mc = new ManaCapability(tag.getInt("mana"),
                tag.getInt("maxmana"),
                tag.getInt("manalevel"),
                tag.getInt("soulgiven"),
                tag.getInt("souln"),
                tag.getBoolean("hasbook") );
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

    public void addSouls(int souls){
        mc.addSoulGiven(souls);
        setDirty();
    }

    public int levelUp(){
        int soulLeft = mc.getSoulCalculatedNeeded() - mc.getSoulGiven();
        while(soulLeft <= 0){
            mc.addManaLevel();
            soulLeft += mc.getSoulCalculatedNeeded();
        }
        setDirty();
        return soulLeft;
    }

    public int getSoulCalculatedNeeded(){
        return mc.getSoulCalculatedNeeded();
    }

    public boolean setupSpellBook(){
        if(mc.spellBookCrafted() == false){
            mc.setup();
            setDirty();
            return false;
        }
        return true;

    }

    public void loseMana(int mana){
        mc.setMana(mana);
        setDirty();
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
        //tag.putInt("souln",mc.getSoulNeeded());
        tag.putBoolean("hasbook",mc.spellBookCrafted());
        return tag;
    }

    public void tick(Level world) {
        counter--;

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
                            .orElse(0);
                    int soul = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getSoulGiven)
                            .orElse(0);
                    int soulN = sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getSoulCalculatedNeeded)
                            .orElse(0);


                    if(soulN <= 0){
                        soulN = (int)(Math.round(Math.pow(2, manaLvl) * 1.3));
                    }

//                    System.out.println("Mana: " + mana +
//                            "\nMana Max: " + manaMax +
//                            "\nMana Level: " + manaLvl +
//                            "\nSoul Given: " + soul +
//                            "\nSoul Need: " + soulN );
                    Messages.sendToPlayer(new PacketSyncManaToClient(mana,manaMax,manaLvl,soul,soulN),sPlayer);
                }
            });
        }


    }

    public void RegenTick(Level world){
        regenCounter--;
        if(regenCounter <= 0){
            regenCounter = 300;
            world.players().forEach(player -> {
                if(player instanceof ServerPlayer sPlayer) {
                    sPlayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                        playerMana.addMana();
                    } );
                }
            });
        }
    }
}
