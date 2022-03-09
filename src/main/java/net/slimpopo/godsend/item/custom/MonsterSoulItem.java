package net.slimpopo.godsend.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.manasystem.network.PacketManaPlayerHandler;
import net.slimpopo.godsend.setup.Messages;

public class MonsterSoulItem extends Item {

    public MonsterSoulItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pPlayer.isCreative() && !pPlayer.level.isClientSide){
            pPlayer.getItemInHand(pUsedHand).shrink(1);
            Messages.sendToServer(new PacketManaPlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }






}
