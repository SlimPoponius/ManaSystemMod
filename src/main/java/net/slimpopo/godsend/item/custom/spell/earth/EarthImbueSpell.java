package net.slimpopo.godsend.item.custom.spell.earth;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class EarthImbueSpell extends SpellItem {
    private static final Spell EARTHIMBUESPELL = new Spell("Earth Imbue Spell",10,3,
            "Imbue your body with the power of the earth.");

    public EarthImbueSpell(Properties pProperties) {
        super(pProperties, EARTHIMBUESPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= EARTHIMBUESPELL.getManaCost()){
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,200,3));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,200,3));

            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());

        }
        return super.use(pLevel, pPlayer, pUsedHand);

    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

}
