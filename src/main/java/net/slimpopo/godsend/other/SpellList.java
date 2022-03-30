package net.slimpopo.godsend.other;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.spell.SpellItem;

import java.util.*;

public class SpellList {
    public static Map<String,Item> Spells = new LinkedHashMap<String,Item>(){
        {
            //FLAME
            put("flameimbue",ModItems.FLAMESPELL_IMBUE.get());
            put("flameweapon",ModItems.FLAMESPELL_WEAPONIZE.get());
            put("flamearmor",ModItems.FLAMESPELL_ARMOR.get());
            put("flamefireball",ModItems.FLAMESPELL_FIREBALL.get());
            put("flamegolem",ModItems.FLAMESPELL_GOLEM.get());

            //ICE
            put("iceimbue",ModItems.ICESPELL_IMBUE.get());
            put("iceweapon",ModItems.ICESPELL_WEAPONIZE.get());
            put("icearmor",ModItems.ICESPELL_ARMOR.get());
            put("icewolves",ModItems.ICESPELL_WOLF.get());
            put("icewall",ModItems.ICESPELL_WALL.get());

            //EARTH
            put("earthimbue",ModItems.EARTHSPELL_IMBUE.get());
            put("earthweapon",ModItems.EARTHSPELL_WEAPONIZE.get());
            put("eartharmor",ModItems.EARTHSPELL_ARMOR.get());
            put("earthwarrior",ModItems.EARTHSPELL_WARRIOR.get());
            put("earthquake",ModItems.EARTHSPELL_QUAKE.get());

            //EARTH
            put("windimbue",ModItems.WINDSPELL_IMBUE.get());
            put("windweapon",ModItems.WINDSPELL_WEAPONIZE.get());
            put("windarmor",ModItems.WINDSPELL_ARMOR.get());
            put("windbats",ModItems.WINDSPELL_BATS.get());
            put("tornado",ModItems.WINDSPELL_TORNADO.get());

            //APOCALYPSE
            //put("apocalypseflame",ModItems.FLAMESPELL_APOCALYPSE.get());
            //put("apocalypseice",ModItems.ICESPELL_APOCALYPSE.get());
            //put("apocalypseice",ModItems.EARTHSPELL_APOCALYPSE.get());

        }
    };

    public static int getSpellLevelReq(int index){
        int counter = 0;
        for(Map.Entry<String, Item> item: Spells.entrySet()){
            if(counter == index){
                if(item.getValue() instanceof SpellItem si)
                    return si.spell.getManaLvlReq();
            }
            counter++;
        }
        return -1;
    };

    public static boolean isSpell(Item a){
        for(Map.Entry<String, Item> item: Spells.entrySet()){
            if(item.getValue() == a){
                return true;
            }
        }
        return false;
    }
    //Spells.contains(a.getItem());

    public static ItemStack getStack(int index){
        int counter = 0;
        for(Map.Entry<String, Item> item: Spells.entrySet()){
            if(counter == index){
                return new ItemStack(item.getValue());
            }
            counter++;
        }
        return null;
    }

    public static String ItemKey(Item a){
        if(isSpell(a)){
            for(Map.Entry<String, Item> item: Spells.entrySet()){
                if(item.getValue() == a)
                    return item.getKey();
            }
        }
        return "";
    }

    public static Item getByItemStack(String spellRegystry){
        for(Map.Entry<String, Item> item: Spells.entrySet()){
            if(item.getKey() == spellRegystry) {
                return item.getValue();
            }
        }
        return null;
    }

    public static ItemStack getItemStack(String spellName){
        for(Map.Entry<String, Item> item: Spells.entrySet()){
            if(spellName == item.getKey()){
                return new ItemStack(item.getValue());
            }
        }
        return ItemStack.EMPTY;
    }

}
