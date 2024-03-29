package net.slimpopo.godsend.item.custom.spell.necromancy;

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
import net.slimpopo.godsend.entity.mobs.IceWolfEntity;
import net.slimpopo.godsend.entity.mobs.ZombieSummonEntity;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class ZombieSummonSpell extends SpellItem {
    private static final Spell ZOMBIESUMMONSPELL = new Spell("Ice Wolf Spell",100,10,
            "Summon wolves that attacks all in it's way. Only appears for a set amount of time.");

    public ZombieSummonSpell(Properties pProperties){
        super(pProperties, ZOMBIESUMMONSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            BlockPos position = pPlayer.blockPosition().north(3);

            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= ZOMBIESUMMONSPELL.getManaCost()) {
                ZombieSummonEntity wolf1 = new ZombieSummonEntity(ModEntityType.ZOMBIESUMMON.get(), pLevel);
                ZombieSummonEntity wolf2 = new ZombieSummonEntity(ModEntityType.ZOMBIESUMMON.get(), pLevel);
                ZombieSummonEntity wolf3 = new ZombieSummonEntity(ModEntityType.ZOMBIESUMMON.get(), pLevel);

                wolf1.setPos(position.getX(), position.getY(), position.getZ());
                wolf2.setPos(position.getX(), position.getY(), position.getZ());
                wolf3.setPos(position.getX(), position.getY(), position.getZ());

                pLevel.addFreshEntity(wolf2);
                pLevel.addFreshEntity(wolf3);
                pLevel.addFreshEntity(wolf1);

                ManaManager.get(pPlayer.level).loseMana(mCur - ZOMBIESUMMONSPELL.getManaCost());
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
