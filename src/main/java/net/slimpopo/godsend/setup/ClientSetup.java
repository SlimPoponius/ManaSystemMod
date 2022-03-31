package net.slimpopo.godsend.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.entity.mobs.EarthWarriorEntity;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.mobs.TornadoEntity;
import net.slimpopo.godsend.entity.mobs.WindBatEntity;
import net.slimpopo.godsend.entity.models.EarthWarriorModel;
import net.slimpopo.godsend.entity.models.FlameGolemModel;
import net.slimpopo.godsend.entity.models.WindBatModel;
import net.slimpopo.godsend.entity.render.*;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.MonsterSoulItem;
import net.slimpopo.godsend.manasystem.client.ManaOverlay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

@Mod.EventBusSubscriber(modid = GodSend.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {


    private static final Logger LGGER = LogManager.getLogger();

    public static void init(FMLClientSetupEvent event){
        event.enqueueWork(() -> {

        });
        OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT,"name", ManaOverlay.HUD_MANA);
        LGGER.warn("setting up overlay");
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntityType.EARTHWARRIOR.get(), EarthWarriorEntity.createAttribute().build());
        event.put(ModEntityType.BIGTORNADO.get(), TornadoEntity.createAttribute().build());
        event.put(ModEntityType.SMALLTORNADO.get(), TornadoEntity.createAttribute().build());

        event.put(ModEntityType.WINDBAT.get(), WindBatEntity.createAttribute().build());

    }

    @SubscribeEvent
    public static void setupModels(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(WindBatModel.LAYER_LOCATION,WindBatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void setupRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityType.FLAMEGOLEM.get(),FlameGolemRenderer::new);
        event.registerEntityRenderer(ModEntityType.ICEWOLF.get(), IceWolfRenderer::new);
        event.registerEntityRenderer(ModEntityType.EARTHWARRIOR.get(), EarthWarriorRenderer::new);
        event.registerEntityRenderer(ModEntityType.BIGTORNADO.get(), BigTornadoRenderer::new);
        event.registerEntityRenderer(ModEntityType.SMALLTORNADO.get(), SmallTornadoRenderer::new);
        event.registerEntityRenderer(ModEntityType.WINDBAT.get(), WindBatRenderer::new);
    }

}
