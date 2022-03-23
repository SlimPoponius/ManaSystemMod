package net.slimpopo.godsend.item.custom.item.fireball;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FireballMagicLongCharge extends ThrowableItemProjectile {


    public FireballMagicLongCharge(LivingEntity p_37439_, Level p_37440_) {
        super(ModEntityType.BIGFIREBALL.get(), p_37439_, p_37440_);
    }

    public FireballMagicLongCharge(EntityType<? extends FireballMagicLongCharge> fireballMagicLongChargeEntityType, Level level) {
        super(fireballMagicLongChargeEntityType,level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            if (!entity.fireImmune()) {
                Entity entity1 = this.getOwner();
                int i = entity.getRemainingFireTicks();
                entity.setSecondsOnFire(5);
                boolean flag = entity.hurt(DamageSource.thrown(this,entity1), 7.5F);
                if (!flag) {
                    entity.setRemainingFireTicks(i);
                } else if (entity1 instanceof LivingEntity) {
                    this.doEnchantDamageEffects((LivingEntity)entity1, entity);
                }
            }

        }
    }

    @Override
    protected void onHitBlock(BlockHitResult bHit) {
        super.onHitBlock(bHit);
        Level level = this.level;
        if(!level.isClientSide) {
            getBlockArea(bHit.getBlockPos().above(),5,5);

        }
    }


    private void  getBlockArea(BlockPos bPos, int width, int length){
        int sideWidth = width/2;
        int sideLength = length/2;

        BlockState flameState = BaseFireBlock.getState(level, bPos);

        for(int i = -sideLength ; i < sideLength; i++){
            for(int j = -sideWidth ; j< sideWidth;j++) {
                BlockPos pos = bPos.offset(i,j,-1);

                Random random = new Random();
                float rand = random.nextFloat();
                if (rand > 0.5f)
                    this.level.setBlock(pos, flameState, 11);

            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FIREBALLBIG.get();
    }
}
