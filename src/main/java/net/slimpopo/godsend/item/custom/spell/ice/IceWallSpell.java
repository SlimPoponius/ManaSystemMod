package net.slimpopo.godsend.item.custom.spell.ice;

import io.netty.handler.codec.http2.Http2FrameLogger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class IceWallSpell extends SpellItem {
    private static final Spell ICEWALLSPELL = new Spell("Ice Wall Spell", 20, 12,
            "Create a wall of ice that protects your path");

    public IceWallSpell(Properties pProperties) {
        super(pProperties, ICEWALLSPELL);
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

            if(mCur >= ICEWALLSPELL.getManaCost()) {
                createIceWall(pLevel, pPlayer);
                ManaManager.get(pPlayer.level).loseMana(mCur - ICEWALLSPELL.getManaCost());
            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private void createIceWall(Level level, Player player){
        BlockPos bPos = player.blockPosition().relative(player.getDirection(),2);
        BlockState ice = Blocks.ICE.defaultBlockState();

        for(int i = -2; i <= 2; i++){
            for(int j = 0; j < 5;j++){
                if(player.getDirection() == Direction.NORTH){
                    BlockPos mid = bPos.offset(i,j,0);
                    level.setBlock(mid,ice,11);
                }else if(player.getDirection() == Direction.SOUTH){
                    BlockPos mid = bPos.offset(i,j,0);
                    level.setBlock(mid,ice,11);
                }else if(player.getDirection() == Direction.EAST){
                    BlockPos mid = bPos.offset(0,j,i);
                    level.setBlock(mid,ice,11);
                }else if(player.getDirection() == Direction.WEST){
                    BlockPos mid = bPos.offset(0,j,i);
                    level.setBlock(mid,ice,11);
                }
            }
        }
    }
}
