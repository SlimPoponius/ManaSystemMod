package net.slimpopo.godsend.other;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.spell.SpellItem;

import java.util.*;

public class SpellList {

    public static Map<String,Item> AllSpells = new LinkedHashMap<>(){
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

            //WIND
            put("windimbue",ModItems.WINDSPELL_IMBUE.get());
            put("windweapon",ModItems.WINDSPELL_WEAPONIZE.get());
            put("windarmor",ModItems.WINDSPELL_ARMOR.get());
            put("windbats",ModItems.WINDSPELL_BATS.get());
            put("tornado",ModItems.WINDSPELL_TORNADO.get());

            //THUNDER
            put("thunderimbue",ModItems.THUNDERSPELL_IMBUE.get());
            put("thunderweapon",ModItems.THUNDERSPELL_WEAPONIZE.get());
            put("thunderarmor",ModItems.THUNDERSPELL_ARMOR.get());
            put("thunderorb",ModItems.THUNDERSPELL_ORB.get());
            put("thunderwall",ModItems.THUNDERSPELL_WALL.get());

            //APOCALYPSE
            put("apocalypseflame",ModItems.FLAMESPELL_APOCALYPSE.get());
            put("apocalypseice",ModItems.ICESPELL_APOCALYPSE.get());
            put("apocalypseearth",ModItems.EARTHSPELL_APOCALYPSE.get());
            put("apocalypsewind",ModItems.WINDSPELL_APOCALYPSE.get());
            put("apocalypsethunder",ModItems.THUNDERSPELL_APOCALYPSE.get());

            //SHADOW
            put("shadowimbue",ModItems.SHADOWSPELL_IMBUE.get());
            put("shadowaura",ModItems.SHADOWSPELL_AURA.get());
            put("shadowtrap",ModItems.SHADOWSPELL_CAPTURE.get());

            //LIGHT
            put("lightimbue",ModItems.LIGHTSPELL_IMBUE.get());
            put("lightaura",ModItems.LIGHTSPELL_AURA.get());
            put("lightweapon",ModItems.LIGHTSPELL_WEAPONIZE.get());

            //SAND
            put("sandtrap",ModItems.SANDSPELL_TRAP.get());
            put("sandweapon",ModItems.SANDSPELL_WEAPONIZE.get());

            //NECROMANCY
            put("zombiesummon",ModItems.NECROSPELL_ZOMBIE.get());
            put("skelesummon",ModItems.NECROSPELL_SKELETON.get());
            put("lifesteal",ModItems.NECROSPELL_LIFESTEAL.get());

            //Clone
            put("clonesummon",ModItems.CLONESUMMONSPELL.get());

        }
    };

    public static Map<String,Item> Spells = new LinkedHashMap<>(){
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

            //WIND
            put("windimbue",ModItems.WINDSPELL_IMBUE.get());
            put("windweapon",ModItems.WINDSPELL_WEAPONIZE.get());
            put("windarmor",ModItems.WINDSPELL_ARMOR.get());
            put("windbats",ModItems.WINDSPELL_BATS.get());
            put("tornado",ModItems.WINDSPELL_TORNADO.get());

            //THUNDER
            put("thunderimbue",ModItems.THUNDERSPELL_IMBUE.get());
            put("thunderweapon",ModItems.THUNDERSPELL_WEAPONIZE.get());
            put("thunderarmor",ModItems.THUNDERSPELL_ARMOR.get());
            put("thunderorb",ModItems.THUNDERSPELL_ORB.get());
            put("thunderwall",ModItems.THUNDERSPELL_WALL.get());

            //APOCALYPSE
            put("apocalypseflame",ModItems.FLAMESPELL_APOCALYPSE.get());
            put("apocalypseice",ModItems.ICESPELL_APOCALYPSE.get());
            put("apocalypseearth",ModItems.EARTHSPELL_APOCALYPSE.get());
            put("apocalypsewind",ModItems.WINDSPELL_APOCALYPSE.get());
            put("apocalypsethunder",ModItems.THUNDERSPELL_APOCALYPSE.get());

        }
    };

    public static Map<String,Item> ForbiddenSpells = new LinkedHashMap<>(){
        {
            //SHADOW
            put("shadowimbue",ModItems.SHADOWSPELL_IMBUE.get());
            put("shadowaura",ModItems.SHADOWSPELL_AURA.get());
            put("shadowtrap",ModItems.SHADOWSPELL_CAPTURE.get());

            //LIGHT
            put("lightimbue",ModItems.LIGHTSPELL_IMBUE.get());
            put("lightaura",ModItems.LIGHTSPELL_AURA.get());
            put("lightweapon",ModItems.LIGHTSPELL_WEAPONIZE.get());

            //SAND
            put("sandtrap",ModItems.SANDSPELL_TRAP.get());
            put("sandweapon",ModItems.SANDSPELL_WEAPONIZE.get());

            //NECROMANCY
            put("zombiesummon",ModItems.NECROSPELL_ZOMBIE.get());
            put("skelesummon",ModItems.NECROSPELL_SKELETON.get());
            put("lifesteal",ModItems.NECROSPELL_LIFESTEAL.get());

            //Clone
            put("clonesummon",ModItems.CLONESUMMONSPELL.get());
        }
    };

    public static Map<String,Item> SandSpells = new LinkedHashMap<>(){
        {
            //SAND
            put("sandtrap",ModItems.SANDSPELL_TRAP.get());
            put("sandweapon",ModItems.SANDSPELL_WEAPONIZE.get());
        }
    };


    public static Map<String,Item> BalanceSpells = new LinkedHashMap<>(){
        {
            //SHADOW
            put("shadowimbue",ModItems.SHADOWSPELL_IMBUE.get());
            put("shadowaura",ModItems.SHADOWSPELL_AURA.get());
            put("shadowtrap",ModItems.SHADOWSPELL_CAPTURE.get());

            //Clone
            put("clonesummon",ModItems.CLONESUMMONSPELL.get());

            //LIGHT
            put("lightimbue",ModItems.LIGHTSPELL_IMBUE.get());
            put("lightaura",ModItems.LIGHTSPELL_AURA.get());
            put("lightweapon",ModItems.LIGHTSPELL_WEAPONIZE.get());


        }
    };

    public static Map<String,Item> NecroSpells = new LinkedHashMap<>(){
        {

            //NECROMANCY
            put("zombiesummon",ModItems.NECROSPELL_ZOMBIE.get());
            put("skelesummon",ModItems.NECROSPELL_SKELETON.get());
            put("lifesteal",ModItems.NECROSPELL_LIFESTEAL.get());

        }
    };


    public static int getSpellLevelReq(int index, Map<String,Item> spellList){
        int counter = 0;
        for(Map.Entry<String, Item> item: spellList.entrySet()){
            if(counter == index){
                if(item.getValue() instanceof SpellItem si)
                    return si.spell.getManaLvlReq();
            }
            counter++;
        }
        return -1;
    }

    public static boolean isSpell(Item a, Map<String,Item> spellList){
        for(Map.Entry<String, Item> item: spellList.entrySet()){
            if(item.getValue().equals(a)){
                return true;
            }
        }
        return false;
    }
    //Spells.contains(a.getItem());

    public static ItemStack getStack(int index, Map<String,Item> spellList){
        int counter = 0;
        for(Map.Entry<String, Item> item: spellList.entrySet()){
            if(counter == index){
                return new ItemStack(item.getValue());
            }
            counter++;
        }
        return null;
    }

    public static String ItemKey(Item a,Map<String,Item> spellList){
        if(isSpell(a,spellList)){
            for(Map.Entry<String, Item> item: spellList.entrySet()){
                if(item.getValue().equals(a))
                    return item.getKey();
            }
        }
        return "";
    }

    public static Item getByItemStack(String spellRegystry,Map<String,Item> spellList){
        for(Map.Entry<String, Item> item: spellList.entrySet()){
            if(item.getKey().equals(spellRegystry)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static ItemStack getItemStack(String spellName,Map<String,Item> spellList){
        for(Map.Entry<String, Item> item: spellList.entrySet()){
            if(item.getKey().equals(spellName)){
                return new ItemStack(item.getValue());
            }
        }
        return ItemStack.EMPTY;
    }

}
