package net.slimpopo.godsend.item.custom.spell.ice.weapon;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.entity.projectile.IceArrowEntity;
import net.slimpopo.godsend.item.ModItems;

import java.util.Random;

public class IceArrowItem extends ArrowItem {
    public float damage;
    public IceArrowItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        IceArrowEntity arrow = new IceArrowEntity( pShooter, pLevel,ModItems.ICE_ARROW.get());
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == IceArrowItem.class;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Random rand = new Random();
        if(rand.nextFloat() > 0.25f) {
            pTarget.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(),100,3));
            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,100,2000));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
