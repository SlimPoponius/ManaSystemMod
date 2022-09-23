package net.slimpopo.godsend.item.custom.spell.ice.weapon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.entity.projectile.FireArrowEntity;
import net.slimpopo.godsend.entity.projectile.IceArrowEntity;
import net.slimpopo.godsend.item.ModItems;

public class IceBowItem extends BowItem {
    private IceArrowEntity myArrow;

    public IceBowItem(Properties p_40660_) {
        super(p_40660_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack iceArrow = new ItemStack(ModItems.ICE_ARROW.get());
        if(pPlayer.getInventory().findSlotMatchingItem(iceArrow) == -1)
            pPlayer.getInventory().add(iceArrow);
        return super.use(pLevel,pPlayer,pHand);
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow) {
        return super.customArrow(myArrow);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {

        if(!pLevel.isClientSide){
            if(pEntityLiving instanceof  Player player){
                myArrow = new IceArrowEntity(player,pLevel,ModItems.ICE_ARROW.get());

                int mCur = ManaManager.get(player.level).getMana();

                ManaManager.get(player.level).loseMana(mCur - 10);

                //player.sendMessage(new TextComponent("shoot bow"),player.getUUID());
            }
            super.releaseUsing(pStack, pLevel, pEntityLiving, pTimeLeft);

        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        return super.onDroppedByPlayer(item, player);
    }
}
