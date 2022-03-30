package net.slimpopo.godsend.item.custom.spell.thunder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class ThunderWallSpell extends SpellItem {
    private static final Spell THUNDERWALLSPELL = new Spell("Thunder Wall Spell", 40, 12,
            "Create a wall of thunder.");

    public ThunderWallSpell(Properties pProperties) {
        super(pProperties, THUNDERWALLSPELL);
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

            if(mCur >= THUNDERWALLSPELL.getManaCost()) {
                if(pLevel.isRaining())
                    createRainThunderWall(pLevel,pPlayer);
                else
                    createThunderWall(pLevel, pPlayer);
                ManaManager.get(pPlayer.level).loseMana(mCur - THUNDERWALLSPELL.getManaCost());
            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private void createThunderWall(Level level, Player player){
        BlockPos bPos = player.blockPosition().relative(player.getDirection(),2);
        BlockPos left = bPos,right = bPos;
        if(level instanceof ServerLevel sLevel){
            if(player.getDirection() == Direction.NORTH || player.getDirection() == Direction.SOUTH){
                left.offset(1,0,0);
                right.offset(-1,0,0);
            }else if(player.getDirection() == Direction.EAST || player.getDirection() == Direction.WEST) {
                left.offset(0,0,1);
                right.offset(0,0,-1);
            }
            EntityType.LIGHTNING_BOLT.spawn(sLevel,null, player,bPos,
                    MobSpawnType.TRIGGERED,true,true);
            EntityType.LIGHTNING_BOLT.spawn(sLevel,null, player,left,
                    MobSpawnType.TRIGGERED,true,true);
            EntityType.LIGHTNING_BOLT.spawn(sLevel,null, player,right,
                    MobSpawnType.TRIGGERED,true,true);
        }
    }

    private void createRainThunderWall(Level level, Player player){
        if(level instanceof ServerLevel sLevel){
            for(int i = 0; i < 3; i++) {
                BlockPos bPos = player.blockPosition().relative(player.getDirection(),2 + i);
                BlockPos left = bPos,right = bPos;
                if (player.getDirection() == Direction.NORTH || player.getDirection() == Direction.SOUTH) {
                    left.offset(1, 0, 0);
                    right.offset(-1, 0, 0);
                } else if (player.getDirection() == Direction.EAST || player.getDirection() == Direction.WEST) {
                    left.offset(0, 0, 1);
                    right.offset(0, 0, -1);
                }
                EntityType.LIGHTNING_BOLT.spawn(sLevel, null, player, bPos,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(sLevel, null, player, left,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(sLevel, null, player, right,
                        MobSpawnType.TRIGGERED, true, true);
            }
        }
    }
}
