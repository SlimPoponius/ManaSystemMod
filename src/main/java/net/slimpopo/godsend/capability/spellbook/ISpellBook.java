package net.slimpopo.godsend.capability.spellbook;

import net.minecraft.world.item.Item;

public interface ISpellBook {
    String getSpellOne();
    String getSpellTwo();
    String getSpellThree();
    boolean hasSpells();
    void setSp1(String spell);
    void setSp2(String spell);
    void setSp3(String spell);


    void setSpells(String spellOne, String spellTwo, String spellThree);


}
