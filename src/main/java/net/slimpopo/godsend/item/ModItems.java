package net.slimpopo.godsend.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.item.custom.FlameArmorItem;
import net.slimpopo.godsend.item.custom.FlameArrowItem;
import net.slimpopo.godsend.item.custom.MonsterSoulItem;
import net.slimpopo.godsend.item.custom.SpellBookItem;
import net.slimpopo.godsend.item.custom.spell.*;
import net.slimpopo.godsend.item.custom.spell.earth.*;
import net.slimpopo.godsend.item.custom.spell.earth.armor.EarthArmorItem;
import net.slimpopo.godsend.item.custom.spell.earth.weapon.EarthAxeItem;
import net.slimpopo.godsend.item.custom.spell.earth.weapon.EarthHammerItem;
import net.slimpopo.godsend.item.custom.spell.ice.*;
import net.slimpopo.godsend.item.custom.spell.ice.armor.IceArmorItem;
import net.slimpopo.godsend.item.custom.spell.ice.weapon.IceArrowItem;
import net.slimpopo.godsend.item.custom.spell.ice.weapon.IceBowItem;
import net.slimpopo.godsend.item.custom.spell.ice.weapon.IceSwordItem;
import net.slimpopo.godsend.item.custom.spell.wind.*;
import net.slimpopo.godsend.item.custom.spell.wind.armor.WindArmorItem;
import net.slimpopo.godsend.item.custom.spell.wind.weapon.WindDaggerItem;
import net.slimpopo.godsend.item.custom.spell.wind.weapon.WindScytheItem;
import net.slimpopo.godsend.item.custom.weapons.FlameBowItem;
import net.slimpopo.godsend.item.custom.weapons.FlameSwordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GodSend.MOD_ID);

    public static final RegistryObject<Item> MONSTERSOUL = ITEMS.register("monstersoul",
            () -> new MonsterSoulItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    public static final RegistryObject<Item> SOULPAPER = ITEMS.register("soulpaper",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    public static final RegistryObject<Item> SPELLBOOK = ITEMS.register("spellbook",
            () -> new SpellBookItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //PROJECTILES
    public static final RegistryObject<Item> FLAME_ARROW = ITEMS.register("firearrow",
            () -> new FlameArrowItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1),1.5f));
    public static final RegistryObject<Item> FIREBALLSMALL = ITEMS.register("smallfireball",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> FIREBALLBIG = ITEMS.register("bigfireball",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> WINDSLASH = ITEMS.register("wind_slash",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("icearrow",
            () -> new IceArrowItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1),1.5f));

    //SPELL
    //FIRE
    public static final RegistryObject<Item> FLAMESPELL_ARMOR = ITEMS.register("flame_spell_armor",
            () -> new FlameArmorSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLAMESPELL_WEAPONIZE = ITEMS.register("flame_spell_weaponize",
            () -> new FlameWeaponSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLAMESPELL_FIREBALL = ITEMS.register("flame_spell_fireball",
            () -> new FlameFireBallSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLAMESPELL_GOLEM = ITEMS.register("flame_spell_golem",
            () -> new FlameGolemSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLAMESPELL_IMBUE= ITEMS.register("flame_spell_imbue",
            () -> new FlameImbueSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //ICE
    public static final RegistryObject<Item> ICESPELL_ARMOR = ITEMS.register("ice_spell_armor",
            () -> new IceArmorSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICESPELL_WEAPONIZE = ITEMS.register("ice_spell_weaponize",
            () -> new IceWeaponSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICESPELL_IMBUE= ITEMS.register("ice_spell_imbue",
            () -> new IceImbueSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICESPELL_WOLF = ITEMS.register("ice_spell_wolves",
            () -> new IceWolfSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICESPELL_WALL = ITEMS.register("ice_spell_wall",
            () -> new IceWallSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //EARTH
    public static final RegistryObject<Item> EARTHSPELL_ARMOR = ITEMS.register("earth_spell_armor",
            () -> new EarthArmorSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> EARTHSPELL_WEAPONIZE = ITEMS.register("earth_spell_weaponize",
            () -> new EarthWeaponSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> EARTHSPELL_IMBUE= ITEMS.register("earth_spell_imbue",
            () -> new EarthImbueSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> EARTHSPELL_WARRIOR = ITEMS.register("earth_spell_warrior",
            () -> new EarthWarriorSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> EARTHSPELL_QUAKE = ITEMS.register("earth_spell_quake",
            () -> new EarthQuakeSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //WIND
    public static final RegistryObject<Item> WINDSPELL_ARMOR = ITEMS.register("wind_spell_armor",
            () -> new WindArmorSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> WINDSPELL_WEAPONIZE = ITEMS.register("wind_spell_weaponize",
            () -> new WindWeaponSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> WINDSPELL_IMBUE= ITEMS.register("wind_spell_imbue",
            () -> new WindImbueSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> WINDSPELL_BATS = ITEMS.register("wind_spell_bats",
            () -> new WindBatSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> WINDSPELL_TORNADO = ITEMS.register("wind_spell_tornado",
            () -> new WindTornadoSpell(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //APOCALYPSE
    public static final RegistryObject<Item> FLAMESPELL_APOCALYPSE= ITEMS.register("flame_spell_apocalypse",
            () -> new FlameSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICESPELL_APOCALYPSE= ITEMS.register("ice_spell_apocalypse",
            () -> new IceSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> EARTHSPELL_APOCALYPSE= ITEMS.register("earth_spell_apocalypse",
            () -> new EarthSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> WINDSPELL_APOCALYPSE= ITEMS.register("wind_spell_apocalypse",
            () -> new WindSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //WEAPONS
    //FLAME
    public static final RegistryObject<Item> FLAME_GREATSWORD = ITEMS.register("flame_greatswd",
            () -> new FlameSwordItem(ModTiers.FLAME, 15,-3.25f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLAME_BOW = ITEMS.register("firebow",
            () -> new FlameBowItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1).durability(150)));

    //ICE
    public static final RegistryObject<Item> ICE_LONGSWORD = ITEMS.register("ice_longswd",
            () -> new IceSwordItem(ModTiers.ICE, 9,5.25f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICE_BOW = ITEMS.register("icebow",
            () -> new IceBowItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1).durability(150)));

    //EARTH
    public static final RegistryObject<Item> EARTH_AXE = ITEMS.register("earth_axe",
            () -> new EarthAxeItem(ModTiers.EARTH, 17,1.25f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> EARTH_HAMMER = ITEMS.register("earth_hammer",
            () -> new EarthHammerItem(ModTiers.EARTH, 17,1.25f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //WIND
    public static final RegistryObject<Item> WIND_SCYTHE = ITEMS.register("wind_scythe",
            () -> new WindScytheItem(ModTiers.WIND, 13,3f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> WIND_DAGGER = ITEMS.register("wind_dagger",
            () -> new WindDaggerItem(ModTiers.WIND, 7,8f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));


    //ARMORS
    //FLAME
    public static final RegistryObject<Item> FLAME_HELMET = ITEMS.register("flame_helmet",
            () -> new ArmorItem(ModArmorMaterials.FLAME, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> FLAME_CHEST = ITEMS.register("flame_chest",
            () -> new FlameArmorItem(ModArmorMaterials.FLAME, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> FLAME_LEG= ITEMS.register("flame_legs",
            () -> new ArmorItem(ModArmorMaterials.FLAME, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> FLAME_BOOT = ITEMS.register("flame_boots",
            () -> new ArmorItem(ModArmorMaterials.FLAME, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    //ICE
    public static final RegistryObject<Item> ICE_HELMET = ITEMS.register("ice_helmet",
            () -> new ArmorItem(ModArmorMaterials.ICE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> ICE_CHEST = ITEMS.register("ice_chest",
            () -> new IceArmorItem(ModArmorMaterials.ICE, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> ICE_LEG= ITEMS.register("ice_legs",
            () -> new ArmorItem(ModArmorMaterials.ICE, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> ICE_BOOT = ITEMS.register("ice_boots",
            () -> new ArmorItem(ModArmorMaterials.ICE, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    //EARTH
    public static final RegistryObject<Item> EARTH_HELMET = ITEMS.register("earth_helmet",
            () -> new ArmorItem(ModArmorMaterials.EARTH, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> EARTH_CHEST = ITEMS.register("earth_chest",
            () -> new EarthArmorItem(ModArmorMaterials.EARTH, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> EARTH_LEG= ITEMS.register("earth_legs",
            () -> new ArmorItem(ModArmorMaterials.EARTH, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> EARTH_BOOT = ITEMS.register("earth_boots",
            () -> new ArmorItem(ModArmorMaterials.EARTH, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    //WIND
    public static final RegistryObject<Item> WIND_HELMET = ITEMS.register("wind_helmet",
            () -> new ArmorItem(ModArmorMaterials.WIND, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> WIND_CHEST = ITEMS.register("wind_chest",
            () -> new WindArmorItem(ModArmorMaterials.WIND, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> WIND_LEG= ITEMS.register("wind_legs",
            () -> new ArmorItem(ModArmorMaterials.WIND, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> WIND_BOOT = ITEMS.register("wind_boots",
            () -> new ArmorItem(ModArmorMaterials.WIND, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    //THUNDER
    public static final RegistryObject<Item> THUNDER_HELMET = ITEMS.register("thunder_helmet",
            () -> new ArmorItem(ModArmorMaterials.THUNDER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> THUNDER_CHEST = ITEMS.register("thunder_chest",
            () -> new EarthArmorItem(ModArmorMaterials.THUNDER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> THUNDER_LEG= ITEMS.register("thunder_legs",
            () -> new ArmorItem(ModArmorMaterials.THUNDER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));
    public static final RegistryObject<Item> THUNDER_BOOT = ITEMS.register("thunder_boots",
            () -> new ArmorItem(ModArmorMaterials.THUNDER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
