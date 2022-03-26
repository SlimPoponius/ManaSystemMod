package net.slimpopo.godsend.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.mobs.IceWolfEntity;

@Mod.EventBusSubscriber(modid = GodSend.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntityType.FLAMEGOLEM.get(), FlameGolemEntity.createAttributes().build());
        event.put(ModEntityType.ICEWOLF.get(), IceWolfEntity.createAttributes().build());

    }
}
