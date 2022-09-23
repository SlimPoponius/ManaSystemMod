package net.slimpopo.godsend.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LightLayer;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.entity.ModBlockEntity;
import net.slimpopo.godsend.entity.block.ShadowBlockEntity;

public class ShadowPoisonEffect extends MobEffect {

    protected ShadowPoisonEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if(!pLivingEntity.level.isClientSide()){
          if(pLivingEntity.level.getBrightness(LightLayer.SKY,pLivingEntity.blockPosition()) < 10
                  || pLivingEntity.level.getBlockEntity(pLivingEntity.blockPosition()) instanceof ShadowBlockEntity){
              pLivingEntity.hurt(DamageSource.MAGIC, 0.75F);

          }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;

    }
}
