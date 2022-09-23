package net.slimpopo.godsend.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.Random;

public class EnchantmentLifeSteal extends Enchantment {


    protected EnchantmentLifeSteal(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        super.doPostAttack(pAttacker, pTarget, pLevel);
        if(!pAttacker.level.isClientSide){
            if(pTarget instanceof LivingEntity living){
                Random rand = new Random();
                float random = rand.nextFloat();
                float chance = 0.75f;

                if (random > chance) {
                    pAttacker.heal(0.5F);

                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
