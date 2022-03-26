package net.slimpopo.godsend.entity.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.effects.ModEffects;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class IceWolfEntity extends Wolf {

    private static final int lifeTick = 1200;
    private int manaSpan;

    public IceWolfEntity(EntityType<? extends IceWolfEntity> entity, Level level) {
        super(entity, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public float getTailAngle() {
        return 1.5393804F;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        if(!super.canAttack((pTarget)))
            return false;
        else{
            if(pTarget instanceof Enemy){
                Random rand = new Random();
                float freezeChance = rand.nextFloat();

                if(freezeChance > .80F) {
                    pTarget.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(),100,1));
                    pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,100,2000));
                }
            }
            return true;
        }
    }

    @Override
    public boolean isOwnedBy(LivingEntity pEntity) {
        return super.isOwnedBy(pEntity);
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
    public boolean isOrderedToSit() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos bPos = this.blockPosition().relative(Direction.DOWN);


        if(this.isOnFire())
            hurt(DamageSource.ON_FIRE,5F);
        if(this.isInWater())
            level.setBlock(bPos, Blocks.ICE.defaultBlockState(),4);


        manaSpan++;
        //System.out.println(manaSpan);
        if(manaSpan >= this.lifeTick){
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public boolean canMate(Animal pOtherAnimal) {
        return false;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }
}
