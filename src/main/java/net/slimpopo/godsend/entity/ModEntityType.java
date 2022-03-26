package net.slimpopo.godsend.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.mobs.IceWolfEntity;
import net.slimpopo.godsend.entity.projectile.FireArrowEntity;
import net.slimpopo.godsend.entity.projectile.IceArrowEntity;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicLongCharge;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicSmallCharge;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GodSend.MOD_ID);

    public static final RegistryObject<EntityType<FireArrowEntity>> FLAMEARROW = ENTITIES.register("firearrow",
            ()-> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"firearrow").toString()));

    public static final RegistryObject<EntityType<IceArrowEntity>> ICEARROW = ENTITIES.register("icearrow",
            ()-> EntityType.Builder.<IceArrowEntity>of(IceArrowEntity::new,
                    MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"icearrow").toString()));

    public static final RegistryObject<EntityType<FireballMagicSmallCharge>> SMALLFIREBALL = ENTITIES.register("smallfireball",
            ()-> EntityType.Builder.<FireballMagicSmallCharge>of(FireballMagicSmallCharge::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"smallfireball").toString()));

    public static final RegistryObject<EntityType<FireballMagicLongCharge>> BIGFIREBALL = ENTITIES.register("bigfireball",
            ()-> EntityType.Builder.<FireballMagicLongCharge>of(FireballMagicLongCharge::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"bigfireball").toString()));

    public static final RegistryObject<EntityType<FlameGolemEntity>> FLAMEGOLEM =
            ENTITIES.register("flamegolem",
                    () -> EntityType.Builder.<FlameGolemEntity>of(FlameGolemEntity::new, MobCategory.MISC).sized(1.4F, 2.7F)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"flamegolem").toString()));

    public static final RegistryObject<EntityType<IceWolfEntity>> ICEWOLF =
            ENTITIES.register("icewolf",
                    () -> EntityType.Builder.<IceWolfEntity>of(IceWolfEntity::new, MobCategory.MISC).sized(0.6F, 0.85F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"icewolf").toString()));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
