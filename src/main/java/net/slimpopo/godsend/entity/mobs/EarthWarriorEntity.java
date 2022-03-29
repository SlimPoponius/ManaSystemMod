package net.slimpopo.godsend.entity.mobs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
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

import java.util.UUID;

public class EarthWarriorEntity extends PathfinderMob implements IAnimatable,IAnimationTickable,NeutralMob {
    private static final String CONTROLLER_NAME = "earthwarriorcontroller";

    private static boolean movingObj;

    private static final int ANIM_IDLE = 0;
    private static final int ANIM_WALK = 1;
    private static final int ANIM_ATTACK_LEFT = 2;
    private static final int ANIM_ATTACK_RIGHT = 3;
    private static final int ANIM_ATTACK_STOMP = 4;

    private static final int lifeTick = 1200;
    private int timer = 0;

    private AnimationFactory factory = new AnimationFactory(this);

    public EarthWarriorEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.noCulling = true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.0D, 8.0F));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy;
        }));
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
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("walk", true));
        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("idle", true));
        }

        if(this.attackAnim > 0){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("attack_stomp", false));
            earthquake(this);
            System.out.println("Should be stomping");
            return PlayState.STOP;

        }

        return PlayState.CONTINUE;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this,"controller",10,this::predicate));

    }

    private static void shootExplorb(EarthWarriorEntity entity){
        if(!entity.level.isClientSide) {
            Explorb explorb = new Explorb(entity, entity.level);
            explorb.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 1.5F, 1.0F);
            entity.level.addFreshEntity(explorb);
        }

    }

    private void earthquake(EarthWarriorEntity entity){
        if(!entity.level.isClientSide){
            entity.level.explode(entity,entity.getX(),entity.getY(),entity.getZ(), 3.0F, Explosion.BlockInteraction.BREAK);

        }
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

    private static boolean isMoving(EarthWarriorEntity entity){
        return entity.goalSelector.getRunningGoals().anyMatch(goal -> goal.getGoal().getClass() == MoveTowardsTargetGoal.class || goal.getGoal().getClass() == RandomStrollGoal.class);
    }

    @Override
    public void tick() {
        super.tick();
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
}
