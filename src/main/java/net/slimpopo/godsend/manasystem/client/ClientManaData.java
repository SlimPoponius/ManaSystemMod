package net.slimpopo.godsend.manasystem.client;

public class ClientManaData {
    private static int mana;
    private static int manaMax;
    private static int manaLvl;
    private static int soulGiven;
    private static int soulNeed;
    private static boolean hasBook;

    public static void set(int mana, int manaMax,int manaLvl,int soulGiven,int soulNeed,boolean hasBook){
        ClientManaData.mana = mana;
        ClientManaData.manaMax = manaMax;
        ClientManaData.manaLvl = manaLvl;
        ClientManaData.soulGiven = soulGiven;
        ClientManaData.soulNeed = soulNeed;
        ClientManaData.hasBook = hasBook;
    }

    public static int getMana(){return mana;}
    public static int getManaMax(){return manaMax;}
    public static int getManaLvl(){return manaLvl;}
    public static int getSoulGiven(){return soulGiven;}
    public static int getSoulNeeded(){return soulNeed;}
    public static boolean getBook(){return hasBook;}

}
