package net.slimpopo.godsend.item.custom.spell.wind;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.entity.mobs.WindBatEntity;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class WindBatSpell extends SpellItem {
    private static final Spell WINDBATSPELL = new Spell("Wind Bat Spell",125,11,
            "Summon bats that attacks all in it's way. Only appears for a set amount of time.");

    public WindBatSpell(Properties pProperties){
        super(pProperties, WINDBATSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {

            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= WINDBATSPELL.getManaCost()) {
                WindBatEntity bat = new WindBatEntity(ModEntityType.WINDBAT.get(), pLevel);
                bat.setPos(pPlayer.getX(), pPlayer.getY() + 20, pPlayer.getZ());

                WindBatEntity bat2 = new WindBatEntity(ModEntityType.WINDBAT.get(), pLevel);
                bat.setPos(pPlayer.getX(), pPlayer.getY() + 20, pPlayer.getZ());

                WindBatEntity bat3 = new WindBatEntity(ModEntityType.WINDBAT.get(), pLevel);
                bat.setPos(pPlayer.getX(), pPlayer.getY() + 20, pPlayer.getZ());

                WindBatEntity bat4 = new WindBatEntity(ModEntityType.WINDBAT.get(), pLevel);
                bat.setPos(pPlayer.getX(), pPlayer.getY() + 20, pPlayer.getZ());

                WindBatEntity bat5 = new WindBatEntity(ModEntityType.WINDBAT.get(), pLevel);
                bat.setPos(pPlayer.getX(), pPlayer.getY() + 20, pPlayer.getZ());

                pLevel.addFreshEntity(bat);
                pLevel.addFreshEntity(bat2);
                pLevel.addFreshEntity(bat3);
                pLevel.addFreshEntity(bat4);
                pLevel.addFreshEntity(bat5);

                ManaManager.get(pPlayer.level).loseMana(mCur - WINDBATSPELL.getManaCost());
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
}
