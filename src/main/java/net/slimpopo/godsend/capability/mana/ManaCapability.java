package net.slimpopo.godsend.capability.mana;

import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nonnull;
import java.util.Random;

public class ManaCapability implements IManaCapability{
    private final int MAXLEVEL = 30;

    protected int mana;
    protected int maxMana;
    protected int manaLevel;

    protected int monsterSoulNeeded;
    protected int monsterSoulGiven;

    protected  boolean hasSpellBook;

    public ManaCapability(){
        mana = 100;
        maxMana =100;
        manaLevel = 1;
        monsterSoulGiven = 0;
        monsterSoulNeeded = getSoulCalculatedNeeded();
    }

    public ManaCapability(int mana, int maxMana,int manaLevel,
                          int monsterSoulNeeded, int monsterSoulGiven, boolean hasSpellBook){
        this.mana = mana;
        this.maxMana = maxMana;
        this.manaLevel = manaLevel;
        this.monsterSoulNeeded = getSoulCalculatedNeeded();
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
    public int getSoulCalculatedNeeded() {
        return 25;
    }

    @Override
    public boolean spellBookCrafted() {
        return hasSpellBook;
    }

    @Override
    public void setMana(int mana) {
        if(mana <= 0) return;
        else if(mana > maxMana)
            this.mana = maxMana;
        else
            this.mana = mana;
    }

    @Override
    public void addMana(int mana) {
        if(this.mana + mana < 0){
            this.mana = 0;
        }
        else if(this.mana +mana >maxMana){
            this.mana = maxMana;
        }
        else{
            this.mana += mana;
        }
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
        else if(level > MAXLEVEL) manaLevel = MAXLEVEL;
        else manaLevel = level;
    }

    @Nonnull
    @Override
    public void addMana() {
        if(mana < maxMana)
            mana += (int)(Math.random()*(9)+1);
        else
            mana = maxMana;
    }

    @Override
    public void addManaLevel() {
        if(manaLevel < MAXLEVEL) {
            this.manaLevel += 1;
            this.maxMana = this.manaLevel * 100;
            this.mana += 25;
            this.monsterSoulGiven = 0;
            this.monsterSoulNeeded += (int) (Math.round(Math.pow(2, this.manaLevel) * 1.3));
        }
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
        manaLevel = 1;
        this.setMana(100);
        this.setMaxMana(100);
        monsterSoulNeeded = this.getSoulCalculatedNeeded();
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
        //compound.putInt("souln",monsterSoulNeeded);
        compound.putBoolean("hasbook",hasSpellBook);
    }

    public void loadNBTData(CompoundTag compound){
        mana = compound.getInt("mana");
        maxMana = compound.getInt("maxmana");
        manaLevel = compound.getInt("manalevel");
        monsterSoulGiven = compound.getInt("soulgiven");
        //monsterSoulNeeded = compound.getInt("souln");
        hasSpellBook = compound.getBoolean("hasbook");
    }

    public void copy(ManaCapability mc){
        this.mana = mc.mana;
        this.maxMana = mc.maxMana;
        this.manaLevel=mc.manaLevel;
        this.monsterSoulGiven=mc.monsterSoulGiven;
        this.monsterSoulNeeded=mc.getSoulCalculatedNeeded();
        this.hasSpellBook=mc.hasSpellBook;
    }
}
