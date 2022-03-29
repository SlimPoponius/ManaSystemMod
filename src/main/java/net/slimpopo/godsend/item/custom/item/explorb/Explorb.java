package net.slimpopo.godsend.item.custom.item.explorb;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

public class Explorb extends ThrowableItemProjectile {


    public Explorb(LivingEntity lEntity, Level level) {
        super(ModEntityType.SMALLFIREBALL.get(), lEntity, level);
    }

    public Explorb(EntityType<? extends Explorb> explorbType, Level level) {
        super(explorbType,level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            entity.hurt(DamageSource.playerAttack(Minecraft.getInstance().player), 8.0F);
            this.level.explode(this,this.getX(),this.getY(),this.getZ(),3.0F, Explosion.BlockInteraction.DESTROY);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult bHit) {
        super.onHitBlock(bHit);
        Level level = this.level;
        if(!level.isClientSide) {
            this.level.explode(this,this.getX(),this.getY(),this.getZ(),2.0F, Explosion.BlockInteraction.DESTROY);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FIREBALLSMALL.get();
    }
}
