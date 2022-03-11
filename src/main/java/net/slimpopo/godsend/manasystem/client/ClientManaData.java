package net.slimpopo.godsend.manasystem.client;

public class ClientManaData {
    private static int mana;
    private static int manaMax;
    private static int manaLvl;
    private static int soulGiven;
    private static int soulNeed;


    public static void set(int mana, int manaMax,int manaLvl,int soulGiven,int soulNeed){
        ClientManaData.mana = mana;
        ClientManaData.manaMax = manaMax;
        ClientManaData.manaLvl = manaLvl;
        ClientManaData.soulGiven = soulGiven;
        ClientManaData.soulNeed = soulNeed;
    }

    public static int getMana(){return mana;}
    public static int getManaMax(){return manaMax;}
    public static int getManaLvl(){return manaLvl;}
    public static int getSoulGiven(){return soulGiven;}
    public static int getSoulNeeded(){return soulNeed;}

}
