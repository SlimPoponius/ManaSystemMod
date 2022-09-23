package net.slimpopo.godsend.item.custom.spell.sand.weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.block.ModBlocks;

public class SandHammerItem extends PickaxeItem {
    public SandHammerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos block= pContext.getClickedPos();
        BlockState sandTrap = ModBlocks.SANDTRAP.get().defaultBlockState();

        Level level= pContext.getLevel();
        Direction dir = pContext.getClickedFace();
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j<=1;j++){
                BlockPos bp;
                if(dir == Direction.UP || dir == Direction.DOWN){
                    bp = block.offset(i,0,j);
                }
                else if(dir == Direction.NORTH || dir == Direction.SOUTH){
                    bp = block.offset(i,j,0);
                }
                else{
                    bp = block.offset(0,j,i);
                }
                level.setBlock(bp,sandTrap,11);
            }
        }
        return super.useOn(pContext);
    }

}
