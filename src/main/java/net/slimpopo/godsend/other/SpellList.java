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


            //APOCALYPSE
            //put("apocalypseflame",null);

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
