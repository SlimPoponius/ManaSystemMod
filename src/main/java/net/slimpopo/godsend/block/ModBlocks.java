package net.slimpopo.godsend.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.item.ModCreativeTab;
import net.slimpopo.godsend.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GodSend.MOD_ID);

    public static final RegistryObject<Block> SPELLLEARNER = registerBlock("spell_learner",
            () -> new SpellLearnerBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.MAGIC_TAB);

    public static final RegistryObject<Block> SANDTRAP = registerBlock("sand_trap",
            () -> new SandTrapBlock(BlockBehaviour.Properties.of(Material.SAND)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.MAGIC_TAB);

    public static final RegistryObject<Block> SHADOWBLOCK = registerBlock("shadow_block",
            () -> new ShadowBlock(BlockBehaviour.Properties.of(Material.POWDER_SNOW)
                    .strength(9f).destroyTime(100f)), ModCreativeTab.MAGIC_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                          CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
