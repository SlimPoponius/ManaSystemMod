package net.slimpopo.godsend.entity.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.item.ModCreativeTab;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.spell.light.weapons.LightArrowItem;

import java.util.Random;

public class LightArrowEntity extends AbstractArrow {

    private final Item referenceItem;
    private LivingEntity shooter;

    public LightArrowEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
        this.referenceItem = ModItems.LIGHT_ARROW.get();
    }

    public LightArrowEntity(LivingEntity shooter, Level world, Item referenceItem) {
        super(ModEntityType.LIGHTARROW.get(), shooter, world);
        this.referenceItem = referenceItem;
    }

    public void setShooter(LivingEntity shooter) {
        this.shooter = shooter;
    }

    @Override
    public ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    public Item getReferenceItem() {
        return referenceItem;
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Random rand = new Random();
        int arrows = rand.nextInt(50);
        if(pResult.getEntity() instanceof  LivingEntity living) {
            for (int i = 0; i < arrows; i++) {
                float rotY = rand.nextFloat(45);
                float rotZ = rand.nextFloat(45);


                LightArrowEntity lArrow = new LightArrowEntity(living, this.level, this.getReferenceItem());
                lArrow.shootFromRotation(living, -90F, rotY, rotZ, 2.5F, 1.0F);
                this.level.addFreshEntity(lArrow);


            }
        }
        super.onHitEntity(pResult);
    }
}
