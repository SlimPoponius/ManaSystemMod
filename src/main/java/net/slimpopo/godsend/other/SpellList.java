package net.slimpopo.godsend.other;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.slimpopo.godsend.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class SpellList {
    public static List<ItemStack> Spells = new ArrayList<ItemStack>(){
        {
            add(new ItemStack(ModItems.FLAMESPELL_ARMOR.get()));
            add(new ItemStack(ModItems.FLAMESPELL_WEAPONIZE.get()));
        }
    };

    public static boolean isSpell(ItemStack a){
        return Spells.contains(a.getItem());
    }
    public static ItemStack getItemStack(String spellName){
        for(ItemStack i : Spells){
            if(spellName == i.getDisplayName().getString()){
                return i;
            }
        }
        return ItemStack.EMPTY;
    }

}
