package net.slimpopo.godsend.entity.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class FlameGolemEntity extends IronGolem {

    private static int flameTick;

    public FlameGolemEntity(EntityType<? extends FlameGolemEntity> entity, Level level) {
        super(entity, level);
        flameTick = 1200;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
        }));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        if(!super.canAttack((pTarget)))
            return false;
        else{
            if(pTarget instanceof Enemy){
                Random rand = new Random();
                float fireChance = rand.nextFloat();
                int fireTimer = rand.nextInt() * 15 + 1;

                if(fireChance > .80F)
                    pTarget.setSecondsOnFire(fireTimer);
            }
            return true;
        }
    }

    @Override
    public boolean isPlayerCreated() {
        return true;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pTarget) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    @Override
    public void tick() {
        super.tick();
        BlockPos bPos = this.blockPosition().relative(Direction.DOWN);
        BlockState bs = BaseFireBlock.getState(level, bPos);
        if(level.isRaining()){
            hurt(DamageSource.GENERIC,2.5F);
        }

        if(this.wasTouchingWater)
            hurt(DamageSource.DROWN,5F);
        else
            level.setBlock(bPos.above(), bs, 11);

        flameTick--;
        if(flameTick <= 0){
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.DISCARDED);
        }
    }
}
