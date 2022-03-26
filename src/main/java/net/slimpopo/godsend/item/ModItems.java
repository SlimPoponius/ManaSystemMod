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
import net.slimpopo.godsend.item.custom.spell.ice.*;
import net.slimpopo.godsend.item.custom.spell.ice.armor.IceArmorItem;
import net.slimpopo.godsend.item.custom.spell.ice.weapon.IceArrowItem;
import net.slimpopo.godsend.item.custom.spell.ice.weapon.IceBowItem;
import net.slimpopo.godsend.item.custom.spell.ice.weapon.IceSwordItem;
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

    //APOCALYPSE
    public static final RegistryObject<Item> FLAMESPELL_APOCALYPSE= ITEMS.register("flame_spell_apocalypse",
            () -> new FlameSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICESPELL_APOCALYPSE= ITEMS.register("ice_spell_apocalypse",
            () -> new IceSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

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

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
