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

    //APOCALYPSE
    public static final RegistryObject<Item> FLAMESPELL_APOCALYPSE= ITEMS.register("flame_spell_apocalypse",
            () -> new FlameSpellApocalypse(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));

    //WEAPONS
    public static final RegistryObject<Item> FLAME_GREATSWORD = ITEMS.register("flame_greatswd",
            () -> new FlameSwordItem(ModTiers.FLAME, 15,-3.25f,
                    new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLAME_BOW = ITEMS.register("firebow",
            () -> new FlameBowItem(new Item.Properties().tab(ModCreativeTab.MAGIC_TAB).stacksTo(1).durability(150)));


    //ARMORS
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

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
