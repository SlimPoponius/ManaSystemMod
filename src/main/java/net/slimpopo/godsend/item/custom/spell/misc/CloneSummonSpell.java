package net.slimpopo.godsend.item.custom.spell.misc;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.entity.mobs.CloneSummonEntity;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class CloneSummonSpell extends SpellItem {
    private static final Spell CLONESUMMONSPELL = new Spell("Ice Wolf Spell",100,10,
            "Summon wolves that attacks all in it's way. Only appears for a set amount of time.");

    public CloneSummonSpell(Properties pProperties){
        super(pProperties, CLONESUMMONSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            BlockPos position = pPlayer.blockPosition().north(3);

            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= CLONESUMMONSPELL.getManaCost()) {
                CloneSummonEntity wolf1 = new CloneSummonEntity(ModEntityType.CLONESUMMON.get(), pLevel,pPlayer);

                wolf1.setPos(position.getX(), position.getY(), position.getZ());
                wolf1.setOwnerUUID(pPlayer.getUUID());

//                if(hasArmorOn(pPlayer)) {
//                    wolf1.setItemSlot(EquipmentSlot.HEAD, pPlayer.getInventory().getArmor(3));
//                    wolf1.setItemSlot(EquipmentSlot.CHEST, pPlayer.getInventory().getArmor(2));
//                    wolf1.setItemSlot(EquipmentSlot.LEGS, pPlayer.getInventory().getArmor(1));
//                    wolf1.setItemSlot(EquipmentSlot.FEET, pPlayer.getInventory().getArmor(0));
//                }

//                if(hasWeaponInOffhand(pPlayer)){
//                    wolf1.setItemSlot(EquipmentSlot.MAINHAND,pPlayer.getOffhandItem());
//                }

//                if(pPlayer.getActiveEffects().size() > 0){
//                    pPlayer.getActiveEffects().forEach(effect -> {
//                        wolf1.addEffect(effect);
//                    });
//                }

                pLevel.addFreshEntity(wolf1);

                ManaManager.get(pPlayer.level).loseMana(mCur - CLONESUMMONSPELL.getManaCost());
                Messages.sendToServer(new PacketManaManagePlayerHandler());
                pPlayer.sendMessage(new TextComponent(AbstractClientPlayer.getSkinLocation(pPlayer.getName().toString()).toString()),pPlayer.getUUID());

            }
            else{
                pPlayer.sendMessage(new TextComponent("Insufficient mana cost"),pPlayer.getUUID());
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);

    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    private boolean hasArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() || !chestplate.isEmpty()
                || !leggings.isEmpty() || !boots.isEmpty();
    }

    private boolean hasWeaponInOffhand(Player player){
        ItemStack weapon = player.getOffhandItem();

        if(weapon.isEmpty())
            return false;

        return weapon.getItem() instanceof SwordItem || weapon.getItem() instanceof BowItem
                || weapon.getItem() instanceof PickaxeItem || weapon.getItem() instanceof ShovelItem;
    }

}
