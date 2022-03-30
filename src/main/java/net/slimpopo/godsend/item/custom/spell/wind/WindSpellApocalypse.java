package net.slimpopo.godsend.item.custom.spell.wind;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.entity.ModEntityType;
import net.slimpopo.godsend.entity.mobs.TornadoEntity;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.Random;

public class WindSpellApocalypse extends SpellItem {
    private static final Spell WINDAPOCALYPSESPELL = new Spell("Wind Apocalypse: Typhoon",00,20,
            "A zone spell that surround enemies in a twister.");

    public WindSpellApocalypse(Properties pProperties) {
        super(pProperties, WINDAPOCALYPSESPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                                    .orElse(0);

            if(mCur >= WINDAPOCALYPSESPELL.getManaCost()) {

                TornadoEntity tornado = new TornadoEntity(ModEntityType.BIGTORNADO.get(), pLevel,true);
                tornado.setPos(pPlayer.getX() + 10, pPlayer.getY() , pPlayer.getZ()+ 20);

                TornadoEntity tornado2 = new TornadoEntity(ModEntityType.BIGTORNADO.get(), pLevel,true);
                tornado2.setPos(pPlayer.getX() - 10, pPlayer.getY() , pPlayer.getZ()+ 20);

                TornadoEntity tornado3 = new TornadoEntity(ModEntityType.BIGTORNADO.get(), pLevel,true);
                tornado3.setPos(pPlayer.getX() + 10, pPlayer.getY() , pPlayer.getZ()- 20);

                TornadoEntity tornado4 = new TornadoEntity(ModEntityType.BIGTORNADO.get(), pLevel,true);
                tornado4.setPos(pPlayer.getX() - 10, pPlayer.getY(), pPlayer.getZ() - 20);

                pLevel.addFreshEntity(tornado);
                pLevel.addFreshEntity(tornado2);
                pLevel.addFreshEntity(tornado3);
                pLevel.addFreshEntity(tornado4);

                ManaManager.get(pPlayer.level).loseMana(mCur - WINDAPOCALYPSESPELL.getManaCost());
                Messages.sendToServer(new PacketManaManagePlayerHandler());
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }
}
