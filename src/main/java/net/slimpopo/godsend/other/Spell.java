package net.slimpopo.godsend.other;

public class Spell {
    private final String spellName;
    private final String description;
    private final int manaCost;
    private final int manaLvlReq;


    public Spell(String spellName, int manaCost, int manaLvlReq,String description){
        this.spellName = spellName;
        this.manaCost = manaCost;
        this.manaLvlReq = manaLvlReq;
        this.description = description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public String getSpellName() {
        return spellName;
    }

    public String getDescription() {
        return description;
    }

    public int getManaLvlReq() {
        return manaLvlReq;
    }
}
