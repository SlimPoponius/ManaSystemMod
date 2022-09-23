package net.slimpopo.godsend.entity.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

import java.util.Random;

public class IceArrowEntity extends AbstractArrow {

    private final Item referenceItem;

    public IceArrowEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
        this.referenceItem = ModItems.ICE_ARROW.get();
    }

    public IceArrowEntity(LivingEntity shooter, Level world, Item referenceItem) {
        super(ModEntityType.ICEARROW.get(), shooter, world);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    public Item getReferenceItem() {
        return referenceItem;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Random rand = new Random();
        if(rand.nextFloat() > 0.25f) {
            if(pResult.getEntity() instanceof  LivingEntity le) {
                le.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), 100, 1));
                le.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2000));
            }
        }
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pBlockRes) {
        Level world = Minecraft.getInstance().level;
        if(!level.isClientSide){
            BlockPos bPos = pBlockRes.getBlockPos();
            BlockState blockstate1 = Blocks.ICE.defaultBlockState();
            world.setBlock(bPos.above(), blockstate1, 11);
        }
        super.onHitBlock(pBlockRes);
        this.discard();
    }
}
