package net.slimpopo.godsend.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.enchantment.EnchantmentFireImbue;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOBEFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GodSend.MOD_ID);

    public static final RegistryObject<MobEffect> FREEZE =
            MOBEFFECTS.register("freeze",
                    () -> new FreezeEffect(MobEffectCategory.HARMFUL,0x99FFFF));

    public static final RegistryObject<MobEffect> FLYING =
            MOBEFFECTS.register("flight",
                    () -> new FreezeEffect(MobEffectCategory.NEUTRAL,0x54dcb4));

    public static void register(IEventBus eventBus){
        MOBEFFECTS.register(eventBus);
    }
}
