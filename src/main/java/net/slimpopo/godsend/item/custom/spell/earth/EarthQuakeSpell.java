package net.slimpopo.godsend.item.custom.spell.earth;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.List;
import java.util.Random;

public class EarthQuakeSpell extends SpellItem {
    private static final Spell EARTHQUAKESPELL = new Spell("Earth Quake Spell",75,10,
            "A spell that causes anyone in the radius to get sent into the air.");

    public EarthQuakeSpell(Properties pProperties) {
        super(pProperties, EARTHQUAKESPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                                    .orElse(0);

            if(mCur >= EARTHQUAKESPELL.getManaCost()) {
                BlockPos block = pPlayer.blockPosition().below();

                Random random = new Random();

                for (int i = -3; i <= 3; i++) {
                    for (int j = -7; j <= 7; j++) {
                        BlockPos newSpot = block.offset(i, 0, j);
                        BlockState thisState = pLevel.getBlockState(newSpot);
                        if (!thisState.isAir()) {

                            AABB bounds = AABB.of(new BoundingBox(newSpot.above()));
                            List<Entity> entities = pLevel.getEntities(null, bounds);
                            if (!entities.isEmpty()) {
                                entities.forEach(le -> {
                                    if (le instanceof LivingEntity living) {
                                        if (living != pPlayer) {
                                            living.teleportTo(living.getX(),living.getY() + 10.0D,living.getZ());
                                            //pPlayer.sendMessage(new TextComponent(le.getName().toString()), pPlayer.getUUID());
                                        }
                                    }
                                });
                            }
                        }

                    }
                }
                ManaManager.get(pPlayer.level).loseMana(mCur - EARTHQUAKESPELL.getManaCost());
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
