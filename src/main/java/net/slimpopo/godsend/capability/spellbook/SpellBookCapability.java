package net.slimpopo.godsend.capability.spellbook;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.other.SpellList;

public class SpellBookCapability implements ISpellBook {
    protected String spellOne = "",spellTwo = "",spellThree = "";

    public SpellBookCapability(){
        this.spellOne = "";
        this.spellTwo = "";
        this.spellThree = "";
    }

    public SpellBookCapability(String spellOne, String spellTwo, String spellThree){
        this.spellOne = spellOne;
        this.spellTwo = spellTwo;
        this.spellThree = spellThree;
    }

    @Override
    public String getSpellOne() {
        if(spellOne != null)
            return spellOne;
        return "";
    }

    @Override
    public String getSpellTwo() {
        if(spellTwo != null)
            return spellTwo;
        return "";
    }

    @Override
    public String getSpellThree() {
        if(spellThree != null)
            return spellThree;
        return "";
    }

    @Override
    public boolean hasSpells() {
        return spellOne!="" ||spellTwo !=""||spellThree!="";
    }

    @Override
    public void setSp1(String spell) {
        spellOne = spell;
    }

    @Override
    public void setSp2(String spell) {
        spellTwo = spell;
    }

    @Override
    public void setSp3(String spell) {
        spellThree = spell;
    }

    @Override
    public void setSpells(String spellOne, String spellTwo, String spellThree) {
        this.spellOne = spellOne;
        this.spellTwo = spellTwo;
        this.spellThree = spellThree;
    }

    public void saveNBTData(CompoundTag compound){
        compound.putString("spellone",spellOne);
        compound.putString("spelltwo",spellTwo);
        compound.putString("spellthree",spellThree);
    }

    public void loadNBTData(CompoundTag compound){
        spellOne = compound.getString("spellone");
        spellTwo= compound.getString("spelltwo");
        spellThree = compound.getString("spellthree");

    }

    public void copy(SpellBookCapability sc){
       this.spellOne = sc.spellOne;
       this.spellTwo = sc.spellTwo;
       this.spellThree = sc.spellThree;
    }


}
