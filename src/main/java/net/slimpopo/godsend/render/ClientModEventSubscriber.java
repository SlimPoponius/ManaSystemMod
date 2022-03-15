package net.slimpopo.godsend.render;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.container.ModContainerEntity;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.render.enitity.FlameArrowRenderer;
import net.slimpopo.godsend.screen.SpellLearnerScreen;

@Mod.EventBusSubscriber(modid = GodSend.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityType.FLAMEARROW.get(), FlameArrowRenderer::new);
        MenuScreens.register(ModContainerEntity.SPELLLEARN_CONTAINER.get(), SpellLearnerScreen::new);
    }

}
