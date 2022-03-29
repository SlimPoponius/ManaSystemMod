package net.slimpopo.godsend.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SandTrapBlock extends Block implements EntityBlock {
    public SandTrapBlock(Properties p_49795_) {
        super(p_49795_);
    }

    //use this to blow up blocks
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide) {
            super.stepOn(pLevel, pPos, pState, pEntity);
            pLevel.explode(pEntity, pPos.getX(), pPos.getY(), pPos.getZ(), 5, Explosion.BlockInteraction.DESTROY);
            pLevel.destroyBlock(pPos,false);
        }
    }


    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        if(!level.isClientSide) {
            super.onBlockExploded(state, level, pos, explosion);
            level.destroyBlock(pos,false);
        }
    }

    @Override
    public boolean canDropFromExplosion(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return true;
    }
}
