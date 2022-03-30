package net.slimpopo.godsend.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.manasystem.network.PacketManaPlayerHandler;
import net.slimpopo.godsend.setup.Messages;

public class MonsterSoulItem extends Item {

    public MonsterSoulItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pPlayer.level.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            pPlayer.getItemInHand(pUsedHand).shrink(1);
            ManaManager.get(pLevel).loseMana(mCur + 20);
            Messages.sendToServer(new PacketManaPlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }






}
