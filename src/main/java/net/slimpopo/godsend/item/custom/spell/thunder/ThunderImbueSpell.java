package net.slimpopo.godsend.item.custom.spell.thunder;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.enchantment.ModEnchantments;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.MagicItemList;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class ThunderImbueSpell extends SpellItem {
    private static final Spell THUNDERIMBUESPELL = new Spell("Thunder Imbue Spell",5,1,
            "Imbue your weapon with the power of thunder.");

    public ThunderImbueSpell(Properties pProperties) {
        super(pProperties, THUNDERIMBUESPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= THUNDERIMBUESPELL.getManaCost()){
                if(pPlayer.getMainHandItem().getItem() == this){
                    ItemStack checkWeapon = pPlayer.getOffhandItem();
                    if(checkIfWeapon(checkWeapon)){
                        checkWeapon.enchant(ModEnchantments.THUNDERIMBUE.get(),1);
                        ManaManager.get(pPlayer.level).loseMana(mCur - THUNDERIMBUESPELL.getManaCost());
                    }
                }
                else if(pPlayer.getOffhandItem().getItem() == this){
                    ItemStack checkWeapon = pPlayer.getMainHandItem();
                    if(checkIfWeapon(checkWeapon)){
                        checkWeapon.enchant(ModEnchantments.THUNDERIMBUE.get(),1);
                        ManaManager.get(pPlayer.level).loseMana(mCur - THUNDERIMBUESPELL.getManaCost());
                    }
                }


            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());

        }
        return super.use(pLevel, pPlayer, pUsedHand);

    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    private boolean checkIfWeapon(ItemStack item){
        return (item.getItem() instanceof SwordItem || item.getItem() instanceof PickaxeItem ||
                item.getItem() instanceof ShovelItem || item.getItem() instanceof HoeItem) &&
                (item.getItem() != MagicItemList.Weapons);
    }
}
