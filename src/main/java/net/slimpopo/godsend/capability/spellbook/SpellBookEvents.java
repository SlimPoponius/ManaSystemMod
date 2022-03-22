package net.slimpopo.godsend.capability.spellbook;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.slimpopo.godsend.GodSend;

public class SpellBookEvents {
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(SpellBookProvider.SPELLBOOK_CAP).isPresent()){
                event.addCapability(new ResourceLocation(GodSend.MOD_ID,"spellbook"),new SpellBookProvider());
            }

        }
    }

    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()){
            event.getOriginal().getCapability(SpellBookProvider.SPELLBOOK_CAP).ifPresent(oldStore -> {
                event.getPlayer().getCapability(SpellBookProvider.SPELLBOOK_CAP).ifPresent(newStore ->{
                    newStore.copy(oldStore);
                });
            });
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){event.register(SpellBookCapability.class);}

    public static void onWorldTick(TickEvent.WorldTickEvent event){
        if(event.world.isClientSide){
            return;
        }
        if(event.phase == TickEvent.Phase.START){
            return;
        }
        SpellBookManager manager = SpellBookManager.get(event.world);
        manager.tick(event.world);

    }
}
