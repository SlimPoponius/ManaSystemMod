package net.slimpopo.godsend.capability.mana;

public interface IManaCapability {
    int getMana();
    int getMaxMana();
    int getManaLevel();
    int getSoulNeeded();
    int getSoulGiven();
    int getSoulCalculatedNeeded();

    boolean spellBookCrafted();

    void setMana(int mana);
    void setMaxMana(int mana);
    void setSoulNeeded(int soul);
    void setLevel(int level);
    void addMana();
    void addManaLevel();
    void increaseMaxMana(int amount);
    void setSoulGiven(int soul);
    void addSoulGiven(int soul);
    void setup();

    void setSpellBookCrafted(boolean bookMade);

}
