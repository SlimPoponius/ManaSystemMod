package net.slimpopo.godsend.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.entity.projectile.FireArrowEntity;
import net.slimpopo.godsend.item.ModItems;

import java.util.Random;

public class FlameArrowItem extends ArrowItem {
    public float damage;
    public FlameArrowItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        FireArrowEntity arrow = new FireArrowEntity( pShooter, pLevel,ModItems.FLAME_ARROW.get());
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == FlameArrowItem.class;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Random rand = new Random();
        if(rand.nextFloat() > 0.25f) {
            System.out.println("Set this place ablaze");
            pTarget.setSecondsOnFire(5);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
