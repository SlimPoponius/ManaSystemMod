package net.slimpopo.godsend.item.custom.item.windslash;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

public class WindSlash extends ThrowableItemProjectile {


    public WindSlash(LivingEntity p_37439_, Level p_37440_) {
        super(ModEntityType.WINDSLASH.get(), p_37439_, p_37440_);
    }

    public WindSlash(EntityType<? extends WindSlash> windSlashEntityType, Level level) {
        super(windSlashEntityType,level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            boolean flag = entity.hurt(DamageSource.playerAttack(Minecraft.getInstance().player), 7.5F);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.WINDSLASH.get();
    }
}
