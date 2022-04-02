package net.slimpopo.godsend.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.server.command.ConfigCommand;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.events.loot.*;
import net.slimpopo.godsend.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;


@Mod.EventBusSubscriber(modid = GodSend.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    private static final Logger myLogger = LogManager.getLogger();
//    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, GodSend.MOD_ID);
//    private static final RegistryObject<MonsterSoulStructureAdditionModifier.Serializer> MONSTERSOULADDER = GLM.register("monstersoulstructure", MonsterSoulStructureAdditionModifier.Serializer::new);

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {

        myLogger.warn("attempting to set up.");

        event.getRegistry().registerAll(
                new MonsterSoulStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(GodSend.MOD_ID,"monster_soul_in_mobs")),
                new MonsterSoulStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(GodSend.MOD_ID,"purified_monster_soul_in_mobs")),
                new MonsterSoulStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(GodSend.MOD_ID,"monster_crystal_in_mobs")),
                new MonsterSoulStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(GodSend.MOD_ID,"purified_monster_crystal_in_mobs"))
        );
        myLogger.warn("attempt 2 works.");
    }



    public static void onCommandsRegister(RegisterCommandsEvent event){
//        new CheckManaCommand(event.getDispatcher());
//        new SetManaCommand(event.getDispatcher());
//        new SetManaAmountCommand(event.getDispatcher());
//        ConfigCommand.register(event.getDispatcher());
    }


}