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
import net.slimpopo.godsend.entity.mobs.*;
import net.slimpopo.godsend.entity.projectile.FireArrowEntity;
import net.slimpopo.godsend.entity.projectile.IceArrowEntity;
import net.slimpopo.godsend.entity.projectile.LightArrowEntity;
import net.slimpopo.godsend.item.custom.item.explorb.Explorb;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicLongCharge;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicSmallCharge;
import net.slimpopo.godsend.item.custom.item.thunderball.ThunderOrb;
import net.slimpopo.godsend.item.custom.item.windslash.WindSlash;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GodSend.MOD_ID);

    public static final RegistryObject<EntityType<FireArrowEntity>> FLAMEARROW = ENTITIES.register("firearrow",
            ()-> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"firearrow").toString()));

    public static final RegistryObject<EntityType<LightArrowEntity>> LIGHTARROW = ENTITIES.register("lightarrow",
            ()-> EntityType.Builder.<LightArrowEntity>of(LightArrowEntity::new,
                    MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"lightarrow").toString()));


    public static final RegistryObject<EntityType<IceArrowEntity>> ICEARROW = ENTITIES.register("icearrow",
            ()-> EntityType.Builder.<IceArrowEntity>of(IceArrowEntity::new,
                    MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"icearrow").toString()));

    public static final RegistryObject<EntityType<FireballMagicSmallCharge>> SMALLFIREBALL = ENTITIES.register("smallfireball",
            ()-> EntityType.Builder.<FireballMagicSmallCharge>of(FireballMagicSmallCharge::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"smallfireball").toString()));

    public static final RegistryObject<EntityType<FireballMagicLongCharge>> BIGFIREBALL = ENTITIES.register("bigfireball",
            ()-> EntityType.Builder.<FireballMagicLongCharge>of(FireballMagicLongCharge::new,
            MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"bigfireball").toString()));

    public static final RegistryObject<EntityType<ThunderOrb>> THUNDERORB = ENTITIES.register("thunderorb",
            ()-> EntityType.Builder.<ThunderOrb>of(ThunderOrb::new,
                    MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"thunderorb").toString()));

    public static final RegistryObject<EntityType<WindSlash>> WINDSLASH = ENTITIES.register("wind_slash",
            ()-> EntityType.Builder.<WindSlash>of(WindSlash::new,
                    MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"wind_slash").toString()));

    public static final RegistryObject<EntityType<Explorb>> EXPLORB = ENTITIES.register("explorb",
            ()-> EntityType.Builder.<Explorb>of(Explorb::new,
                    MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(GodSend.MOD_ID,"explorb").toString()));

    public static final RegistryObject<EntityType<FlameGolemEntity>> FLAMEGOLEM =
            ENTITIES.register("flamegolem",
                    () -> EntityType.Builder.<FlameGolemEntity>of(FlameGolemEntity::new, MobCategory.MISC).sized(1.4F, 2.7F)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"flamegolem").toString()));

    public static final RegistryObject<EntityType<CloneSummonEntity>> CLONESUMMON =
            ENTITIES.register("clonesummon",
                    () -> EntityType.Builder.<CloneSummonEntity>of(CloneSummonEntity::new, MobCategory.MISC).sized(0.6F, 1.95F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"clonesummon").toString()));

    public static final RegistryObject<EntityType<ZombieSummonEntity>> ZOMBIESUMMON =
            ENTITIES.register("zombiesummon",
                    () -> EntityType.Builder.<ZombieSummonEntity>of(ZombieSummonEntity::new, MobCategory.MISC)
                            .sized(0.6F, 1.95F)
                            .clientTrackingRange(8)
                            .build(new ResourceLocation(GodSend.MOD_ID,"zombiesummon").toString()));
    public static final RegistryObject<EntityType<SkeletonSummonEntity>> SKELETONSUMMON =
            ENTITIES.register("skeletonsummon",
                    () -> EntityType.Builder.<SkeletonSummonEntity>of(SkeletonSummonEntity::new, MobCategory.MISC)
                            .sized(0.6F, 1.99F)
                            .clientTrackingRange(8)
                            .build(new ResourceLocation(GodSend.MOD_ID,"skeletonsummon").toString()));
    public static final RegistryObject<EntityType<IceWolfEntity>> ICEWOLF =
            ENTITIES.register("icewolf",
                    () -> EntityType.Builder.<IceWolfEntity>of(IceWolfEntity::new, MobCategory.MISC).sized(0.6F, 0.85F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"icewolf").toString()));

    public static final RegistryObject<EntityType<WindBatEntity>> WINDBAT =
            ENTITIES.register("windbat",
                    () -> EntityType.Builder.<WindBatEntity>of(WindBatEntity::new, MobCategory.MISC).sized(0.5F, 0.9F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"windbat").toString()));

    public static final RegistryObject<EntityType<EarthWarriorEntity>> EARTHWARRIOR=
            ENTITIES.register("earth_warrior",
                    () -> EntityType.Builder.<EarthWarriorEntity>of(EarthWarriorEntity::new, MobCategory.MISC).sized(2F, 3F)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"earth_warrior").toString()));

    public static final RegistryObject<EntityType<TornadoEntity>> BIGTORNADO=
            ENTITIES.register("big_tornado",
                    () -> EntityType.Builder.<TornadoEntity>of(TornadoEntity::new, MobCategory.MISC).sized(2.4F, 8F)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"big_tornado").toString()));

    public static final RegistryObject<EntityType<TornadoEntity>> SMALLTORNADO=
            ENTITIES.register("small_tornado",
                    () -> EntityType.Builder.<TornadoEntity>of(TornadoEntity::new, MobCategory.MISC).sized(0.3F, 1F)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(GodSend.MOD_ID,"small_tornado").toString()));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
