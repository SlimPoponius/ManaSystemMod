package net.slimpopo.godsend.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.server.command.ConfigCommand;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.events.loot.MonsterSoulAdditionModifier;
import net.slimpopo.godsend.events.loot.MonsterSoulStructureAdditionModifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;


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
                        (new ResourceLocation(GodSend.MOD_ID,"monster_soul_in_mobs"))
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