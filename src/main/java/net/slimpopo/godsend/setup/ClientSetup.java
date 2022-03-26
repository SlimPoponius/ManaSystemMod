package net.slimpopo.godsend.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.models.FlameGolemModel;
import net.slimpopo.godsend.entity.render.FlameGolemRenderer;
import net.slimpopo.godsend.entity.render.IceWolfRenderer;
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
    public static void setupRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityType.FLAMEGOLEM.get(),FlameGolemRenderer::new);
        event.registerEntityRenderer(ModEntityType.ICEWOLF.get(), IceWolfRenderer::new);


    }

}
