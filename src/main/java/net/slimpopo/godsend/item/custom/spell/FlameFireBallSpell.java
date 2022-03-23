package net.slimpopo.godsend.item.custom.spell;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicLongCharge;
import net.slimpopo.godsend.item.custom.item.fireball.FireballMagicSmallCharge;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.manasystem.network.PacketManaPlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.Random;

public class FlameFireBallSpell extends SpellItem{
    public static final Spell FLAMEBALLSPELL = new Spell("Flame Ball Spell",30,7,
            "A spell that lets you launch a flame projectile at your foe for massive fire damage. Hold to charge for bigger effects");

    public FlameFireBallSpell(Properties pProperties) {
        super(pProperties, FLAMEBALLSPELL);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            int mCur = ManaManager.get(pPlayer.level).getMana();

            Random random = new Random();
            float charge = random.nextFloat();

            //small fBall
            if (charge < 0.5f) {
                FireballMagicSmallCharge smallfireball = new FireballMagicSmallCharge(pPlayer,pLevel);
                smallfireball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                pLevel.addFreshEntity(smallfireball);

                ManaManager.get(pPlayer.level).loseMana(mCur - FLAMEBALLSPELL.getManaCost());
            }
            //big fBall
            else {
                FireballMagicLongCharge bigfireball = new FireballMagicLongCharge(pPlayer,pLevel);
                bigfireball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                pLevel.addFreshEntity(bigfireball);

                pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                    ManaManager.get(pPlayer.level).loseMana(mCur - (int)(FLAMEBALLSPELL.getManaCost() * 1.67));
                });
            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());
        }
        return super.use(pLevel,pPlayer,pUsedHand);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 60;
    }
}
