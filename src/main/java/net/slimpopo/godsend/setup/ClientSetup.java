package net.slimpopo.godsend.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.slimpopo.godsend.GodSend;
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
}
