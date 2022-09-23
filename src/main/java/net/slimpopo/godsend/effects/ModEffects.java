package net.slimpopo.godsend.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOBEFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GodSend.MOD_ID);

    public static final RegistryObject<MobEffect> FREEZE =
            MOBEFFECTS.register("freeze",
                    () -> new FreezeEffect(MobEffectCategory.HARMFUL,0x99FFFF));

    public static final RegistryObject<MobEffect> FLYING =
            MOBEFFECTS.register("flight",
                    () -> new FreezeEffect(MobEffectCategory.NEUTRAL,0x54dcb4));

    public static final RegistryObject<MobEffect> LIGHTAURA =
            MOBEFFECTS.register("lightbff",
                    () -> new LightBuffEffect(MobEffectCategory.NEUTRAL,0xFFFFFF));

    public static final RegistryObject<MobEffect> SHADOWAURA =
            MOBEFFECTS.register("shadowbff",
                    () -> new ShadowBuffEffect(MobEffectCategory.NEUTRAL,0x000000));

    public static final RegistryObject<MobEffect> SHADOWPOISON =
            MOBEFFECTS.register("shadowpsn",
                    () -> new ShadowPoisonEffect(MobEffectCategory.HARMFUL,0x000000));

    public static void register(IEventBus eventBus){
        MOBEFFECTS.register(eventBus);
    }
}
