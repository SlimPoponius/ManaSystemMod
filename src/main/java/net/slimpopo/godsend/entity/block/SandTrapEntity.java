package net.slimpopo.godsend.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.entity.ModBlockEntity;
import net.slimpopo.godsend.entity.ModEntityType;

public class SandTrapEntity extends BlockEntity {

    public SandTrapEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntity.SAND_TRAP.get(), pWorldPosition, pBlockState);
    }

}
