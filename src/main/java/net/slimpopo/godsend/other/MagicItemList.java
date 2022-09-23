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
            add(ModItems.FLAME_GREATSWORD.get());
            add(ModItems.ICE_LONGSWORD.get());
            add(ModItems.EARTH_AXE.get());
            add(ModItems.EARTH_HAMMER.get());
            add(ModItems.FLAME_BOW.get());
            add(ModItems.ICE_BOW.get());
            add(ModItems.WIND_DAGGER.get());
            add(ModItems.WIND_SCYTHE.get());
            add(ModItems.THUNDER_RAPIER.get());
            add(ModItems.THUNDER_STAFF.get());
            add(ModItems.SAND_HAMMER.get());
            add(ModItems.LIGHT_BOW.get());

        }
    };
}
