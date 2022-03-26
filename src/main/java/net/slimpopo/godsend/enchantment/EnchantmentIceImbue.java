package net.slimpopo.godsend.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.slimpopo.godsend.effects.ModEffects;

import java.util.Random;

public class EnchantmentIceImbue extends Enchantment {


    protected EnchantmentIceImbue(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        super.doPostAttack(pAttacker, pTarget, pLevel);
        if(!pAttacker.level.isClientSide){
            if(pTarget instanceof LivingEntity living){
                Random rand = new Random();
                float random = rand.nextFloat();

                if(random > .50F) {
                    living.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), 60, 1));
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,60,2000));
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
