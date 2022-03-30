package net.slimpopo.godsend.item.custom.spell.earth;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.List;
import java.util.Random;

public class EarthSpellApocalypse extends SpellItem {
    private static final Spell EARTHSPELLAPOCALYPSE = new Spell("Earth Apocalypse: Wasteland",400,20,
            "A zone spell that sets everything to explosive sand.");

    public EarthSpellApocalypse(Properties pProperties) {
        super(pProperties, EARTHSPELLAPOCALYPSE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                                    .orElse(0);

            if(mCur >= EARTHSPELLAPOCALYPSE.getManaCost()) {
                BlockPos block = pPlayer.blockPosition().below();

                Random random = new Random();

                for (int i = -7; i <= 7; i++) {
                    for (int j = -7; j <= 7; j++) {
                        for(int k = -3; k <= 0; k++) {
                            BlockPos newSpot = block.offset(i, k, j);
                            BlockState fBlock = ModBlocks.SANDTRAP.get().defaultBlockState();
                            BlockState thisState = pLevel.getBlockState(newSpot);
                            if (!thisState.isAir()) {
                                float rand = random.nextFloat();

                                if (rand >= 0.5f)
                                    pLevel.setBlock(newSpot, fBlock, 11);

                            }

                        }
                    }
                }
                ManaManager.get(pPlayer.level).loseMana(mCur - EARTHSPELLAPOCALYPSE.getManaCost());
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
