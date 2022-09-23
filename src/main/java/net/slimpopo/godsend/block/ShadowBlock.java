package net.slimpopo.godsend.block;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.entity.ModBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ShadowBlock extends Block implements EntityBlock {
//    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    protected LivingEntity caster;
    protected BlockState prevState;
    private static int tick = 0;
    private final int MAX_TICK_TIME = 200;

    public ShadowBlock(Properties properties) {
        super(properties);
    }

    public ShadowBlock(Properties properties, LivingEntity caster) {
        super(properties);
        this.caster = caster;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {

        float chance = 0.35f;

        if(chance < pRandom.nextFloat()){
            pLevel.addParticle(ParticleTypes.SOUL,pPos.getX() + pRandom.nextDouble(), pPos.getY() + 0.5d,
                    pPos.getZ() + pRandom.nextDouble(),0d,0.05d,0d);
        }

//        if(prevState != null) {
//            if (tick > MAX_TICK_TIME) {
//                pLevel.setBlock(pPos, prevState, 11);
//            }
//        }
        super.animateTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        super.stepOn(pLevel, pPos, pState, pEntity);;
        if(!pLevel.isClientSide){
            if(pEntity instanceof LivingEntity pLivingEntity){
                if(pLivingEntity != caster) {
                    pLivingEntity.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), 300, 1));
                    pLivingEntity.hurt(DamageSource.MAGIC,3);
                    if(prevState != null)
                        pLevel.setBlock(pPos, prevState, 11);
                }
            }
        }
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntity.SHADOW_BLOCK.get().create(pPos,pState);
    }

    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return false;
    }


    public void setPrevState(BlockState prevState) {
        this.prevState = prevState;
    }

    public void setCaster(LivingEntity caster) {
        this.caster = caster;
    }
}
