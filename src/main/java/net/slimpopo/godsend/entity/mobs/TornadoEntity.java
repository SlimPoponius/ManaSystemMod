package net.slimpopo.godsend.entity.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.item.custom.item.explorb.Explorb;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;
import java.util.UUID;

public class TornadoEntity extends PathfinderMob implements IAnimatable,IAnimationTickable {
    private static final String CONTROLLER_NAME = "tornadocontroller";

    private static boolean movingObj;

    private static final int lifeTick = 1200;
    private int timer = 0;
    private static boolean isBig;

    private AnimationFactory factory = new AnimationFactory(this);

    public TornadoEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.noCulling = true;
    }

    public TornadoEntity(EntityType<? extends PathfinderMob> type, Level level, Boolean bigNado) {
        super(type, level);
        this.noCulling = true;
        isBig = bigNado;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.5D, 8.0F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.5D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> p_28879_ instanceof Enemy));
    }

    public static AttributeSupplier.Builder createAttribute(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,30.0D)
                .add(Attributes.MOVEMENT_SPEED,0.5D)
                .add(Attributes.ATTACK_DAMAGE,15.0D)
                .add(Attributes.ARMOR,5.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("spin", true));

        return PlayState.CONTINUE;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this,"controller",10,this::predicate));

    }

    private void caughtEntity(Entity living){
        living.setDeltaMovement(living.getDeltaMovement().add(0,this.getY() + 10, 0));
    }

    @Override
    public void aiStep() {
        super.aiStep();

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void tick() {
        super.tick();

        BlockPos.betweenClosedStream(this.getBoundingBox()).forEach(block ->{
            Random rand = new Random();
            float pickUpChance = rand.nextFloat();

            if(pickUpChance > 0.5f){
                this.level.destroyBlock(block,true);
            }
        });


        timer++;
        if(timer >= lifeTick){
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        this.level.broadcastEntityEvent(this, (byte)4);
        float f = 2.0f;
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = pEntity.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            if(isBig)
                pEntity.setDeltaMovement(pEntity.getDeltaMovement().add(0.0D, (double)8F, 0.0D));
            else
                pEntity.setDeltaMovement(pEntity.getDeltaMovement().add(0.0D, (double)1F, 0.0D));
            this.doEnchantDamageEffects(this, pEntity);
        }
        return flag;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    public void setIsBig(boolean isBig) {
        TornadoEntity.isBig = isBig;
    }
}
