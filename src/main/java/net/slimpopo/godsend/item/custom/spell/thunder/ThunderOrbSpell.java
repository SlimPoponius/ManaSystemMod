package net.slimpopo.godsend.item.custom.spell.thunder;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.item.thunderball.ThunderOrb;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.Random;

public class ThunderOrbSpell extends SpellItem {
    public static final Spell THUNDERORB = new Spell("Thunder Orb Spell",30,11,
            "A spell that lets you launch a thunder projectile at your foe for thunder damage.");

    public ThunderOrbSpell(Properties pProperties) {
        super(pProperties, THUNDERORB);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(pPlayer.getItemInHand(pUsedHand).getItem() == this){
                pPlayer.isInvulnerableTo(DamageSource.LIGHTNING_BOLT);
            }

            if(mCur >= THUNDERORB.getManaCost()) {
                ThunderOrb thunderOrb = new ThunderOrb(pPlayer, pLevel);
                thunderOrb.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 2.0F, 1.5F, 0.2F);
                pLevel.addFreshEntity(thunderOrb);

                ManaManager.get(pPlayer.level).loseMana(mCur - THUNDERORB.getManaCost());
                Messages.sendToServer(new PacketManaManagePlayerHandler());
            }
            else{
                pPlayer.sendMessage(new TextComponent("Insufficient mana cost"),pPlayer.getUUID());
            }
        }
        return super.use(pLevel,pPlayer,pUsedHand);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 60;
    }
}
