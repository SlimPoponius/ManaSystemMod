package net.slimpopo.godsend;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.container.ModContainerEntity;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.enchantment.ModEnchantments;
import net.slimpopo.godsend.entity.ModBlockEntity;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.other.SpellList;
import net.slimpopo.godsend.setup.ClientSetup;
import net.slimpopo.godsend.setup.Config;
import net.slimpopo.godsend.setup.ModSetup;
import net.slimpopo.godsend.util.ModItemProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GodSend.MOD_ID)
public class GodSend
{
    public static final String MOD_ID = "godsend";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public GodSend() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntityType.register(eventBus);
        ModBlockEntity.register(eventBus);
        ModContainerEntity.register(eventBus);
        ModEnchantments.register(eventBus);
        ModEffects.register(eventBus);

        ModSetup.setup();
        Config.register();


        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(ModSetup::init);
        eventBus.addListener(ClientSetup::init);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(ClientSetup::init));
        GeckoLib.initialize();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ModItemProperties.addCustomItemProperties();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


}
