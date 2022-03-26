package net.slimpopo.godsend.item.custom.item.fireball;

import net.minecraft.client.Minecraft;
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
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FireballMagicSmallCharge extends ThrowableItemProjectile {


    public FireballMagicSmallCharge(LivingEntity lEntity, Level level) {
        super(ModEntityType.SMALLFIREBALL.get(), lEntity, level);
    }

    public FireballMagicSmallCharge(EntityType<? extends FireballMagicSmallCharge> fireballMagicSmallChargeEntityType, Level level) {
        super(fireballMagicSmallChargeEntityType,level);
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
                boolean flag = entity.hurt(DamageSource.playerAttack(Minecraft.getInstance().player), 5.0F);
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
            getBlockArea(bHit.getBlockPos().above(),3,3);

        }
    }


    private void  getBlockArea(BlockPos bPos, int width, int length){
        int sideWidth = width/2;
        int sideLength = length/2;


        for(int i = -sideLength ; i < sideLength; i++){
            for(int j = -sideWidth ; j< sideWidth;j++) {
                BlockPos pos = bPos.relative(Direction.DOWN);
                pos.offset(i,0,j);
                BlockState flameState = BaseFireBlock.getState(level, pos);

                level.setBlock(pos.above(), flameState, 11);

            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FIREBALLSMALL.get();
    }
}
