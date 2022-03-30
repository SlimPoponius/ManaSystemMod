package net.slimpopo.godsend.item.custom.spell.earth;

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
import net.slimpopo.godsend.entity.mobs.EarthWarriorEntity;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class EarthWarriorSpell extends SpellItem {
    private static final Spell EARTHWARRIORSPELL = new Spell("Earth Warrior Spell",400,15,
            "Summon an earth warrior that attacks all in it's way. Only appears for a set amount of time.");

    public EarthWarriorSpell(Properties pProperties){
        super(pProperties, EARTHWARRIORSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            BlockPos position = pPlayer.blockPosition().north(3);

            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= EARTHWARRIORSPELL.getManaCost()) {
                EarthWarriorEntity golem = new EarthWarriorEntity(ModEntityType.EARTHWARRIOR.get(), pLevel);
                golem.setPos(position.getX(), position.getY(), position.getZ());
                pLevel.addFreshEntity(golem);

                ManaManager.get(pPlayer.level).loseMana(mCur - EARTHWARRIORSPELL.getManaCost());
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
