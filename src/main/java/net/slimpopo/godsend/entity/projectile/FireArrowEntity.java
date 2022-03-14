package net.slimpopo.godsend.entity.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModItems;

import java.util.Random;

public class FireArrowEntity extends AbstractArrow {

    private final Item referenceItem;

    public FireArrowEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
        this.referenceItem = ModItems.FLAME_ARROW.get();
    }

    public FireArrowEntity(LivingEntity shooter, Level world, Item referenceItem) {
        super(ModEntityType.FLAMEARROW.get(), shooter, world);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }

    @Override
    protected void onHitBlock(BlockHitResult pBlockRes) {
        Level world = Minecraft.getInstance().level;
        if(!level.isClientSide){
            BlockPos bPos = pBlockRes.getBlockPos();
            BlockState blockstate1 = BaseFireBlock.getState(world, bPos);
            world.setBlock(bPos.above(), blockstate1, 11);
        }
        super.onHitBlock(pBlockRes);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Random rand = new Random();
        if(rand.nextFloat() > 0.25f) {
            System.out.println("Set this place ablaze");
            pResult.getEntity().setSecondsOnFire(5);
        }
        super.onHitEntity(pResult);
    }
}
