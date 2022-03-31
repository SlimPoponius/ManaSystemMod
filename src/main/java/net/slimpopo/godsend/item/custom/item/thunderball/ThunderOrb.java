package net.slimpopo.godsend.item.custom.item.thunderball;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

public class ThunderOrb extends ThrowableItemProjectile {


    public ThunderOrb(LivingEntity lEntity, Level level) {
        super(ModEntityType.THUNDERORB.get(), lEntity, level);
    }

    public ThunderOrb(EntityType<? extends ThunderOrb> fireballMagicSmallChargeEntityType, Level level) {
        super(fireballMagicSmallChargeEntityType,level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide && !(pResult.getEntity() instanceof Player)) {
            Player player = Minecraft.getInstance().player;
            ServerLevel sLevel = (ServerLevel) level;
            EntityType.LIGHTNING_BOLT.spawn(sLevel,null, null,pResult.getEntity().blockPosition(),
                    MobSpawnType.TRIGGERED,true,true);

        }
    }

    @Override
    protected void onHitBlock(BlockHitResult bHit) {
        super.onHitBlock(bHit);
        Player player = Minecraft.getInstance().player;
        Level level = this.level;
        strike(bHit.getBlockPos(),player);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THUNDERBALL.get();
    }

    public void strike(BlockPos bPos, Player player){
        if(!level.isClientSide) {
            ServerLevel sLevel = (ServerLevel) level;
            EntityType.LIGHTNING_BOLT.spawn(sLevel,null, null,bPos,
                    MobSpawnType.TRIGGERED,true,true);

        }
    }
}
