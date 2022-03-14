package net.slimpopo.godsend.other;

public class Spell {
    private final String spellName;
    private final int manaCost;


    public Spell(String spellName, int manaCost){
        this.spellName = spellName;
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public String getSpellName() {
        return spellName;
    }
}
