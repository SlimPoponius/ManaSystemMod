package net.slimpopo.godsend.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class FlyingEffect extends MobEffect {

    protected FlyingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);

        if(this == ModEffects.FLYING.get()){
            if(pLivingEntity instanceof Player player)
                player.getAbilities().flying = true;
            pLivingEntity.setYRot(0);
        }

    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (this == ModEffects.FLYING.get()) {
            int k = 30 >> pAmplifier;
            if (k > 0) {
                return pDuration % k == 0;
            } else {
                return true;
            }
        }
        return super.isDurationEffectTick(pDuration, pAmplifier);
    }
}
