package net.slimpopo.godsend.item.custom.spell.shadow;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.block.ShadowBlock;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class ShadowCaptureSpell extends SpellItem {
    private static final Spell SHADOWCAPTURESPELL = new Spell("Shadow Capture Spell", 20, 12,
            "Create a wall of ice that protects your path");

    public ShadowCaptureSpell(Properties pProperties) {
        super(pProperties, SHADOWCAPTURESPELL);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= SHADOWCAPTURESPELL.getManaCost()) {
                //createTrap(pLevel,pPlayer);
                ManaManager.get(pPlayer.level).loseMana(mCur - SHADOWCAPTURESPELL.getManaCost());
            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockTouched = pContext.getClickedPos();
        Block shadowBlock = ModBlocks.SHADOWBLOCK.get();

        BlockState original = pContext.getLevel().getBlockState(blockTouched);
        ((ShadowBlock) shadowBlock).setPrevState(original);
        ((ShadowBlock) shadowBlock).setCaster(pContext.getPlayer());

        pContext.getLevel().setBlock(blockTouched,shadowBlock.defaultBlockState(),11);
        return super.useOn(pContext);
    }

//    private void createTrap(Level level, Player player){
//        BlockPos blockTouched = player.blockPosition().below();
//        Block shadowBlock = ModBlocks.SHADOWBLOCK.get();
//
//        BlockState original = level.getBlockState(blockTouched);
//        ((ShadowBlock) shadowBlock).setPrevState(original);
//        ((ShadowBlock) shadowBlock).setCaster(player);
//
//        level.setBlock(blockTouched,shadowBlock.defaultBlockState(),11);
//    }

//    private void setShadowTrap(Level level, Player player){
//        System.out.println(player.blockPosition());
//        BlockPos bPos = player.blockPosition().relative(player.getDirection(),1);
//        System.out.println(bPos);
//        Block shadowBlock = ModBlocks.SHADOWBLOCK.get();
//
//        for(int i = 0; i < 5 ; i++){
//            BlockPos loc = bPos.relative(player.getDirection(),i);
//            BlockState currentState = level.getBlockState(loc.below());
//            //check if obstacle
//            if(checkIfObstacle(level,loc)){
//                break;
//            }
//            else{
//                //check if incline
//                if(checkIfIncline(level,loc)){
//                    loc = loc.above();
//                }
//                //check for decline
//                else if( checkIfDecline(level,loc)){
//                    loc = loc.below();
//                }
//
//                System.out.println(currentState);
//                ((ShadowBlock) shadowBlock).setPrevState(currentState);
//                ((ShadowBlock) shadowBlock).setCaster(player);
//
//                level.setBlock(loc,shadowBlock.defaultBlockState(),11);
//
//            }
//        }
//    }
//
//    private boolean checkIfObstacle(Level level, BlockPos pos){
//        BlockState mystate = level.getBlockState(pos);
//        if(mystate.getBlock() == Blocks.WATER || mystate.getBlock() == Blocks.LAVA)
//            return true;
//        return false;
//    }
//
//    private boolean checkIfIncline(Level level, BlockPos pos){
//        BlockState mystate = level.getBlockState(pos);
//        if(mystate.isAir())
//            return false;
//        return true;
//    }
//
//    private boolean checkIfDecline(Level level,BlockPos pos){
//        BlockState mystate = level.getBlockState(pos);
//        if(mystate.isAir())
//            return true;
//        return false;
//    }
//
//    private BlockPos forward(Direction dir, BlockPos pos){
//        if(dir == Direction.NORTH)
//            return pos.north();
//        else if (dir == Direction.SOUTH)
//            return pos.south();
//        else if(dir == Direction.EAST)
//            return pos.east();
//        else if(dir == Direction.WEST)
//            return pos.west();
//        return null;
//    }


}
