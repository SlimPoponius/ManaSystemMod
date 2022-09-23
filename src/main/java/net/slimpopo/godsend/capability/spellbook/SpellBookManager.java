package net.slimpopo.godsend.capability.spellbook;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.slimpopo.godsend.manasystem.network.spellbook.PacketSpellBookSyncToClient;
import net.slimpopo.godsend.other.SpellList;
import net.slimpopo.godsend.setup.Messages;

import javax.annotation.Nonnull;

public class SpellBookManager extends SavedData {

    public SpellBookCapability sc = new SpellBookCapability();
    private int counter = 0;

    public SpellBookManager(){

    }

    public SpellBookManager(CompoundTag tag){
        sc = new SpellBookCapability(
                tag.getString("spellone"),
                tag.getString("spelltwo"),
                tag.getString("spellthree"));
    }

    public String getSpellOne(){
        return  sc.getSpellOne();
    }

    public String getSpellTwo(){
        return sc.getSpellTwo();
    }

    public String getSpellThree(){
        return   sc.getSpellThree();
    }

    public String getNextSpell(String spell){
        if(spell == sc.getSpellOne())
            return getSpellTwo();
        else if(spell == sc.getSpellTwo())
            return getSpellThree();
        else if(spell == sc.getSpellThree())
            return getSpellOne();
        else
            return getSpellOne();
    }

    public void setSpellsForInfo(ItemStack i1, ItemStack i2, ItemStack i3){
        String sp1Nm,sp2Nm,sp3Nm;
        if (i1 != ItemStack.EMPTY)
            sp1Nm = SpellList.ItemKey(i1.getItem());
        else
            sp1Nm = "";

        if (i2 != ItemStack.EMPTY)
            sp2Nm = SpellList.ItemKey(i2.getItem());
        else
            sp2Nm = "";

        if (i3 != ItemStack.EMPTY)
            sp3Nm = SpellList.ItemKey(i3.getItem());
        else
            sp3Nm = "";

        sc.setSpells(sp1Nm,sp2Nm,sp3Nm);
        setDirty();
    }

    public boolean setSpells(String item, String item2, String item3){
        sc.setSpells(item,item2,item3);
        setDirty();
        return true;
    }

    @Nonnull
    public static SpellBookManager get(Level level){
        if(level.isClientSide){
            throw new RuntimeException("Not accessible on client-side.");
        }
        DimensionDataStorage storage = ((ServerLevel)level).getDataStorage();
        return storage.computeIfAbsent(SpellBookManager::new,SpellBookManager::new,"spellbookmanager");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putString("spellone",sc.getSpellOne());
        tag.putString("spelltwo",sc.getSpellTwo());
        tag.putString("spellthree",sc.getSpellThree());
        return tag;
    }

    public void tick(Level world) {
        counter--;

        if(counter <= 0){
            counter = 10;
            world.players().forEach(player ->{
                if(player != null && player instanceof ServerPlayer sPlayer){
                    String i1 = sPlayer.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                            .map(SpellBookCapability::getSpellOne)
                            .orElse("");
                    String i2 = sPlayer.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                            .map(SpellBookCapability::getSpellTwo)
                            .orElse("");
                    String i3 = sPlayer.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                            .map(SpellBookCapability::getSpellThree)
                            .orElse("");

                    //System.out.println(i1 + ", " + i2 + ", " + i3);
                    Messages.sendToPlayer(new PacketSpellBookSyncToClient(i1,i2,i3),sPlayer);
                }
            });
        }


    }
}
