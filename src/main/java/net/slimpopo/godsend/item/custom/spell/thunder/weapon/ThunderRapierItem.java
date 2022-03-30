package net.slimpopo.godsend.item.custom.spell.thunder.weapon;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.effects.ModEffects;

import java.util.Random;

public class ThunderRapierItem extends SwordItem {
    public ThunderRapierItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Random rand = new Random();
        float rainChance = pAttacker.level.isRaining() ? 0.5f : 0.75f;

        if(rand.nextFloat() > rainChance) {
            ServerLevel sWorld = (ServerLevel)pAttacker.level;
            EntityType.LIGHTNING_BOLT.spawn(sWorld,null, (Player)pAttacker,pTarget.blockPosition(),
                    MobSpawnType.TRIGGERED,true,true);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
