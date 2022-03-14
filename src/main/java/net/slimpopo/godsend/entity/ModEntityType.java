package net.slimpopo.godsend.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.projectile.FireArrowEntity;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GodSend.MOD_ID);

    public static final RegistryObject<EntityType<FireArrowEntity>> FLAMEARROW = ENTITIES.register("firearrow",()-> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"firearrow").toString()));


    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
