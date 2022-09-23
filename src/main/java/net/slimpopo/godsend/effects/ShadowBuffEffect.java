package net.slimpopo.godsend.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LightLayer;

public class ShadowBuffEffect extends MobEffect {
    protected ShadowBuffEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);

        if(!pLivingEntity.level.isClientSide()){
            if(pLivingEntity.level.getBrightness(LightLayer.SKY,pLivingEntity.blockPosition()) <= 7){
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,200,2));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,200,2));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200,2));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
