package net.slimpopo.godsend.item.custom.spell;

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
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicSmallCharge;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class FlameGolemSpell extends SpellItem{
    private static final Spell FLAMEGOLEMSPELL = new Spell("Flame Golem Spell",100,10,
            "Summon a golem that attacks all in it's way. Only appears for a set amount of time.");

    public FlameGolemSpell(Properties pProperties){
        super(pProperties, FLAMEGOLEMSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            BlockPos position = pPlayer.blockPosition().north(3);

            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= FLAMEGOLEMSPELL.getManaCost()) {
                FlameGolemEntity golem = new FlameGolemEntity(ModEntityType.FLAMEGOLEM.get(), pLevel);
                golem.setPos(position.getX(), position.getY(), position.getZ());
                pLevel.addFreshEntity(golem);

                pPlayer.sendMessage(new TextComponent("Sending golem your way at position: " + golem.position()), pPlayer.getUUID());
                ManaManager.get(pPlayer.level).loseMana(mCur - FLAMEGOLEMSPELL.getManaCost());
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
