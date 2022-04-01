package net.slimpopo.godsend.capability.mana;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.setup.Messages;

public class ManaEvents {

    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()){
                event.addCapability(new ResourceLocation(GodSend.MOD_ID,"mana"),new PlayerManaProvider());
            }

        }
    }

    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()){
            event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                event.getPlayer().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                    newStore.copy(oldStore);
                });
            });
            Messages.sendToServer(new PacketManaManagePlayerHandler());
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){event.register(ManaCapability.class);}

    public static void onWorldTick(TickEvent.WorldTickEvent event){
        if(event.world.isClientSide){
            return;
        }
        if(event.phase == TickEvent.Phase.START){
            return;
        }
        ManaManager manager = ManaManager.get(event.world);
        manager.tick(event.world);
        manager.RegenTick(event.world);

    }
}
