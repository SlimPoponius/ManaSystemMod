package net.slimpopo.godsend.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.ModBlockEntity;

public class SpellLearnerEntity extends BaseBlockEntity{
    public static final Component title = new TranslatableComponent("container."+ GodSend.MOD_ID+".spell_learner");

    public SpellLearnerEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState, 29);
    }

}
