package net.slimpopo.godsend.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.Random;

public class EnchantmentThunderImbue extends Enchantment {


    protected EnchantmentThunderImbue(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        super.doPostAttack(pAttacker, pTarget, pLevel);
        if(!pAttacker.level.isClientSide){
            if(pTarget instanceof LivingEntity living){
                Random rand = new Random();
                float random = rand.nextFloat();
                float rainChance = pAttacker.level.isRaining() ? 0.5f : 0.75f;

                if (random > rainChance) {
                    ServerLevel sWorld = (ServerLevel) pAttacker.level;
                    EntityType.LIGHTNING_BOLT.spawn(sWorld, null, (Player) pAttacker, pTarget.blockPosition(),
                            MobSpawnType.TRIGGERED, true, true);

                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
