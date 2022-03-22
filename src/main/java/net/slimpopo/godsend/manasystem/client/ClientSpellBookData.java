package net.slimpopo.godsend.manasystem.client;

import net.minecraft.world.item.Item;

public class ClientSpellBookData {
    private static String iOne, iTwo, iThree;

    public static void set(String i1, String i2, String i3){
        ClientSpellBookData.iOne = i1;
        ClientSpellBookData.iTwo = i2;
        ClientSpellBookData.iThree = i3;
    }

    public static String getItemOne(){return iOne;}
    public static String getItemTwo(){return iTwo;}
    public static String getItemThree(){return iThree;}

}
