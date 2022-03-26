package net.slimpopo.godsend.other;

import net.minecraft.world.item.Item;
import net.slimpopo.godsend.item.ModItems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MagicItemList {
    public static List<Item> Weapons = new ArrayList<Item>()
    {
        {
            //FLAME
            add(ModItems.FLAME_GREATSWORD.get());
            add(ModItems.ICE_LONGSWORD.get());
            add(ModItems.FLAME_BOW.get());
            add(ModItems.ICE_BOW.get());




            //APOCALYPSE
            //put("apocalypseflame",null);

        }
    };
}
