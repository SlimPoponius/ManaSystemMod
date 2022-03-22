package net.slimpopo.godsend.other;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.slimpopo.godsend.item.ModItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellList {
    public static Map<String,Item> Spells = new HashMap<String,Item>(){
        {
            put("flamearmor",ModItems.FLAMESPELL_ARMOR.get());
            put("flameweapon",ModItems.FLAMESPELL_WEAPONIZE.get());
        }
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
