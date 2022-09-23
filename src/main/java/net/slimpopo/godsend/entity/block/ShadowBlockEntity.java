package net.slimpopo.godsend.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.entity.ModBlockEntity;

public class ShadowBlockEntity extends BlockEntity {
    public ShadowBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntity.SHADOW_BLOCK.get(), pWorldPosition, pBlockState);
    }
}
