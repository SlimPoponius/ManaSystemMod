package net.slimpopo.godsend.item.custom.spell.ice;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IceSpellApocalypse extends SpellItem {
    private static final Spell ICEAPOCALYPSESPELL = new Spell("Ice Apocalypse: Tundra",400,15,
            "A zone spell that sets everything to below zero.");

    public IceSpellApocalypse(Properties pProperties) {
        super(pProperties, ICEAPOCALYPSESPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                                    .orElse(0);

            if(mCur >= ICEAPOCALYPSESPELL.getManaCost()) {
                BlockPos block = pPlayer.blockPosition().below();

                Random random = new Random();

                for (int i = -7; i <= 7; i++) {
                    for (int j = -7; j <= 7; j++) {
                        for(int k = -3; k <= 0; k++) {
                            BlockPos newSpot = block.offset(i, k, j);
                            BlockState fBlock = Blocks.ICE.defaultBlockState();
                            BlockState sBlock = Blocks.SNOW_BLOCK.defaultBlockState();
                            BlockState thisState = pLevel.getBlockState(newSpot);
                            if (!thisState.isAir()) {
                                int rand = random.nextInt() % 3;
                                if (rand == 1)
                                    pLevel.setBlock(newSpot.above(), fBlock, 11);
                                else if (rand == 2)
                                    pLevel.setBlock(newSpot.above(), sBlock, 11);

                                AABB bounds = AABB.of(new BoundingBox(newSpot.above()));
                                List<Entity> entities = pLevel.getEntities(null, bounds);
                                if (!entities.isEmpty()) {
                                    entities.forEach(le -> {
                                        if (le instanceof LivingEntity living) {
                                            if (living != pPlayer) {
                                                living.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), 180));
                                                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 180, 2000));
                                                //pPlayer.sendMessage(new TextComponent(le.getName().toString()), pPlayer.getUUID());
                                            }
                                        }
                                    });
                                }
                            }
                            ManaManager.get(pPlayer.level).loseMana(mCur - ICEAPOCALYPSESPELL.getManaCost());
                            Messages.sendToServer(new PacketManaManagePlayerHandler());
                        }
                    }
                }
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }
}
