package net.slimpopo.godsend.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.slimpopo.godsend.effects.ModEffects;

import java.util.Random;

public class EnchantmentShadowImbue extends Enchantment {


    protected EnchantmentShadowImbue(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        super.doPostAttack(pAttacker, pTarget, pLevel);
        if(!pAttacker.level.isClientSide){
            if(pTarget instanceof LivingEntity living){
                Random rand = new Random();
                float random = rand.nextFloat();

                if (random > 0.75) {
                    living.addEffect(new MobEffectInstance(ModEffects.SHADOWPOISON.get(),60,1));
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
