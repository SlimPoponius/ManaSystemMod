package net.slimpopo.godsend.item.custom.spell.fire;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.Random;

public class FlameSpellApocalypse extends SpellItem {
    private static final Spell FLAMEAPOCALYPSESPELL = new Spell("Flame Apocalypse",400,20,
            "A zone spell that sets everything burning in it's path.");

    public FlameSpellApocalypse(Properties pProperties) {
        super(pProperties, FLAMEAPOCALYPSESPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                                    .orElse(0);

            if(mCur >= FLAMEAPOCALYPSESPELL.getManaCost()) {
                pPlayer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800));
                BlockPos block = pPlayer.blockPosition().relative(Direction.DOWN);

                Random random = new Random();

                for (int i = -7; i <= 7; i++) {
                    for (int j = -7; j <= 7; j++) {
                        for(int k = -3; k <= 0; k++) {
                            BlockPos newSpot = block.offset(i, k, j);
                            BlockState fBlock = BaseFireBlock.getState(pLevel, newSpot);
                            BlockState thisState = pLevel.getBlockState(newSpot);
                            if (!thisState.isAir()) {
                                float rand = random.nextFloat();
                                if (rand >= 0.7F)
                                    pLevel.setBlock(newSpot.above(), fBlock, 11);
                            }


                        }
                    }
                }
                ManaManager.get(pPlayer.level).loseMana(mCur - FLAMEAPOCALYPSESPELL.getManaCost());
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
