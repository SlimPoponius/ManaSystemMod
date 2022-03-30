package net.slimpopo.godsend.entity.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.item.custom.item.windslash.WindSlash;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class WindBatEntity extends Bat implements NeutralMob, RangedAttackMob {

    private static final int lifeTick = 1200;
    private int manaSpan;

    public WindBatEntity(EntityType<? extends WindBatEntity> entity, Level level) {
        super(entity, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this,1.0D,60,10.0f));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Mob.class,5, false,true, livingEntity -> livingEntity instanceof Enemy));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor){
        Vec3 vec3 = pTarget.getDeltaMovement();
        double d0 = pTarget.getX() + vec3.x - this.getX();
        double d1 = pTarget.getEyeY() - (double)1.1F - this.getY();
        double d2 = pTarget.getZ() + vec3.z - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        if(pTarget instanceof Monster) {
            WindSlash wind = new WindSlash(this, this.level);
            wind.setXRot(wind.getXRot() - -20.0F);
            wind.shoot(d0, d1 + d3 * 0.2D, d2, 5.5F, 0.5F);
            this.level.addFreshEntity(wind);
        }

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

//        if(this.getTarget() != null){
//            performRangedAttack(this.getTarget(), 3.0F);
//        }

        manaSpan++;
        //System.out.println(manaSpan);
        if(manaSpan >= this.lifeTick){
            this.remove(RemovalReason.DISCARDED);
        }
    }

    public static AttributeSupplier.Builder createAttribute(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,10.0D)
                .add(Attributes.MOVEMENT_SPEED,0.8D)
                .add(Attributes.ATTACK_DAMAGE,5.0D)
                .add(Attributes.ARMOR,2.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

}
