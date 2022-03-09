package net.slimpopo.godsend.capability.mana;

import net.minecraft.nbt.CompoundTag;

public class ManaCapability implements IManaCapability{
    protected int mana;
    protected int maxMana;
    protected int manaLevel;

    protected int monsterSoulNeeded;
    protected int monsterSoulGiven;

    protected  boolean hasSpellBook;

    public ManaCapability(){

    }

    public ManaCapability(int mana, int maxMana,int manaLevel,
                          int monsterSoulNeeded, int monsterSoulGiven, boolean hasSpellBook){
        this.mana = mana;
        this.maxMana = maxMana;
        this.manaLevel = manaLevel;
        this.monsterSoulNeeded = monsterSoulNeeded;
        this.monsterSoulGiven = monsterSoulGiven;
        this.hasSpellBook = hasSpellBook;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public int getManaLevel() {
        return manaLevel;
    }

    @Override
    public int getSoulNeeded() {
        return monsterSoulNeeded;
    }

    @Override
    public int getSoulGiven() {
        return monsterSoulGiven;
    }

    @Override
    public boolean spellBookCrafted() {
        return hasSpellBook;
    }

    @Override
    public void setMana(int mana) {
        if(mana <= 0) return;
        this.mana = mana;
    }

    @Override
    public void setMaxMana(int mana) {
        if(mana <= 0) return;
        maxMana = mana;
    }


    @Override
    public void setSoulNeeded(int soul) {
        if(soul <= 0) return;
        monsterSoulNeeded =soul;
    }

    @Override
    public void setLevel(int level) {
        if(level <= 0) return;
        manaLevel = level;
    }

    @Override
    public void addMana(int mana) {
        if(this.mana + mana >= maxMana) this.mana = maxMana;
        else this.mana += mana;
    }

    @Override
    public void addManaLevel() {
        this.manaLevel++;
        this.maxMana += 100;
        this.mana += 25;
        this.monsterSoulGiven = 0;
        this.monsterSoulNeeded += (int) (Math.round(Math.pow(2, this.manaLevel) * 1.3));
    }

    @Override
    public void increaseMaxMana(int amount) {
        if(amount <= 0) return;
        this.maxMana += amount;
    }

    @Override
    public void setSoulGiven(int soul) {
        if(soul <= 0) return;
        monsterSoulGiven = soul;
    }

    @Override
    public void addSoulGiven(int soul) {
        monsterSoulGiven += soul;
    }


    @Override
    public void setup() {
        maxMana = 100;
        mana = 100;
        manaLevel = 1;
        monsterSoulNeeded = 4;
        hasSpellBook = true;
    }

    @Override
    public void setSpellBookCrafted(boolean bookMade) {
        hasSpellBook = bookMade;
    }

    public void saveNBTData(CompoundTag compound){
        compound.putInt("mana",mana);
        compound.putInt("maxmana",maxMana);
        compound.putInt("manalevel",manaLevel);
        compound.putInt("soulgiven",monsterSoulGiven);
        compound.putInt("soulneeded",monsterSoulNeeded);
        compound.putBoolean("hasbook",hasSpellBook);
    }

    public void loadNBTData(CompoundTag compound){
        mana = compound.getInt("mana");
        maxMana = compound.getInt("manamax");
        manaLevel = compound.getInt("manalevel");
        monsterSoulGiven = compound.getInt("soulgiven");
        monsterSoulNeeded = compound.getInt("soulneeded");
        hasSpellBook = compound.getBoolean("hasbook");
    }

    public void copy(ManaCapability mc){
        mana = mc.mana;
        maxMana = mc.maxMana;
        manaLevel=mc.manaLevel;
        monsterSoulGiven=mc.monsterSoulGiven;
        monsterSoulNeeded=mc.monsterSoulNeeded;
        hasSpellBook=mc.hasSpellBook;
    }
}
