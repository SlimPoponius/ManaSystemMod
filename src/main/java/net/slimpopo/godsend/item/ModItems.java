package net.slimpopo.godsend.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.item.custom.MonsterSoulItem;
import net.slimpopo.godsend.item.custom.SpellBookItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GodSend.MOD_ID);

    public static final RegistryObject<Item> MONSTERSOUL = ITEMS.register("monstersoul",
            () -> new MonsterSoulItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOULPAPER = ITEMS.register("soulpaper",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SPELLBOOK = ITEMS.register("spellbook",
            () -> new SpellBookItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
