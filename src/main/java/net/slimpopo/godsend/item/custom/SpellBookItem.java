package net.slimpopo.godsend.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.manasystem.network.PacketSpellBookPlayerHandler;
import net.slimpopo.godsend.setup.Messages;

public class SpellBookItem extends Item {
    public SpellBookItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pPlayer.level.isClientSide)
            Messages.sendToServer(new PacketSpellBookPlayerHandler());
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
