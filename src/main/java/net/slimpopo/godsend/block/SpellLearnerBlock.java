package net.slimpopo.godsend.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.slimpopo.godsend.container.SpellLearnerContainer;
import net.slimpopo.godsend.entity.ModBlockEntity;
import net.slimpopo.godsend.entity.block.BaseBlockEntity;
import net.slimpopo.godsend.entity.block.SpellLearnerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SpellLearnerBlock extends Block implements EntityBlock {

    public SpellLearnerBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntity.SPELL_LEARNER.get().create(pPos,pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide &&pLevel.getBlockEntity(pPos) instanceof final SpellLearnerEntity spellL){
            MenuProvider container = new SimpleMenuProvider(SpellLearnerContainer.getServerContainer(spellL,pPos),SpellLearnerEntity.title);
            NetworkHooks.openGui((ServerPlayer) pPlayer,container,pPos);
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : ($0, $1,$2,blockEntity) -> ((BaseBlockEntity)blockEntity).tick();
    }

}
