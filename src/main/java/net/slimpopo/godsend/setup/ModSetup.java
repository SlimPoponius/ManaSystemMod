package net.slimpopo.godsend.setup;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.capability.mana.ManaEvents;
import net.slimpopo.godsend.capability.spellbook.SpellBookEvents;


@Mod.EventBusSubscriber(modid = GodSend.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
        bus.addGenericListener(Entity.class, SpellBookEvents::onAttachCapabilitiesPlayer);

        bus.addListener(ManaEvents::onPlayerCloned);
        bus.addListener(ManaEvents::onRegisterCapabilities);
        bus.addListener(ManaEvents::onWorldTick);

        bus.addListener(SpellBookEvents::onPlayerCloned);
        bus.addListener(SpellBookEvents::onRegisterCapabilities);
        bus.addListener(SpellBookEvents::onWorldTick);
    }

    public static void init(FMLCommonSetupEvent event){
        Messages.register();
    }
}
