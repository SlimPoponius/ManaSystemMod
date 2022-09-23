package net.slimpopo.godsend.item.custom.spell.shadow;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class ShadowAuraSpell extends SpellItem {
    private static final Spell SHADOWAURASPELL = new Spell("Flame Armor Spell",25,5,
            "A basic armor spell that covers you in flame armor. Can be removed with either the spell or removing the helmet.");

    public ShadowAuraSpell(Properties pProperties) {
        super(pProperties, SHADOWAURASPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= SHADOWAURASPELL.getManaCost() ) {
                if (activatedAura(pPlayer)) {
                    pPlayer.addEffect(new MobEffectInstance(ModEffects.SHADOWAURA.get(),600,1));
                    pPlayer.sendMessage(new TextComponent("A dark aura has surrounded the player"), pPlayer.getUUID());
                    return super.use(pLevel, pPlayer, pUsedHand);
                } else {
                    //if they do, remove armor pieces and add to inventory
                    pPlayer.removeEffect(ModEffects.SHADOWAURA.get());
                    pPlayer.sendMessage(new TextComponent("Spell has been deactivated"), pPlayer.getUUID());
                }

                ManaManager.get(pPlayer.level).loseMana(mCur - SHADOWAURASPELL.getManaCost());

                Messages.sendToServer(new PacketManaManagePlayerHandler());
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

    private boolean activatedAura(Player player){
        return !player.hasEffect(ModEffects.SHADOWAURA.get());
    }
}
