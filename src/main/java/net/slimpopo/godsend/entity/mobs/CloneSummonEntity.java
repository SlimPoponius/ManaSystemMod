package net.slimpopo.godsend.entity.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CloneSummonEntity extends TamableAnimal implements NeutralMob{

    private static Player playerCloned;
    private static final int lifeTick = 1200;
    private int timer = 0;

    public CloneSummonEntity(EntityType<? extends TamableAnimal> entity, Level level) {
        super(entity, level);
    }

    public CloneSummonEntity(EntityType<? extends TamableAnimal> entity, Level level, Player player) {
        super(entity, level);
        this.playerCloned = player;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.0D, 8.0F));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy;
        }));
    }

    public static AttributeSupplier.Builder createAttribute(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,8.0D)
                .add(Attributes.MOVEMENT_SPEED,0.25D)
                .add(Attributes.ATTACK_DAMAGE,0.75D)
                .add(Attributes.ARMOR)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public void tick() {
        super.tick();
        timer++;
        if(timer >= lifeTick){
            this.remove(RemovalReason.DISCARDED);
        }
    }

    public ResourceLocation getPlayerTexture(){
        if(playerCloned != null)
            return AbstractClientPlayer.getSkinLocation(playerCloned.getName().toString());
        return null;
    }

    public Player getPlayerCloned() {
        return playerCloned;
    }

    private boolean hasArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() || !chestplate.isEmpty()
                || !leggings.isEmpty() || !boots.isEmpty();
    }

    private boolean hasWeaponInOffhand(Player player){
        ItemStack weapon = player.getOffhandItem();

        if(weapon.isEmpty())
            return false;

        return weapon.getItem() instanceof SwordItem || weapon.getItem() instanceof BowItem
                || weapon.getItem() instanceof PickaxeItem || weapon.getItem() instanceof ShovelItem;
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
}
