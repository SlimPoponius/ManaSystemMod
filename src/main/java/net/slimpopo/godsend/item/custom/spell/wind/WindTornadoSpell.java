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
import net.slimpopo.godsend.entity.mobs.TornadoEntity;
import net.slimpopo.godsend.entity.mobs.WindBatEntity;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class WindTornadoSpell extends SpellItem {
    private static final Spell WINDTORNADOSPELL = new Spell("Wind Bat Spell",00,14,
            "Conjure a small twister that attacks anything.");

    public WindTornadoSpell(Properties pProperties){
        super(pProperties, WINDTORNADOSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            BlockPos position = pPlayer.blockPosition().north(3);

            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= WINDTORNADOSPELL.getManaCost()) {
                TornadoEntity tornado = new TornadoEntity(ModEntityType.SMALLTORNADO.get(), pLevel,false);
                tornado.setPos(position.getX(), position.getY() , position.getZ()+ 20);

                pLevel.addFreshEntity(tornado);

                ManaManager.get(pPlayer.level).loseMana(mCur - WINDTORNADOSPELL.getManaCost());
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
