package net.slimpopo.godsend.item.custom.spell.thunder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

import java.util.Random;

public class ThunderSpellApocalypse extends SpellItem {
    private static final Spell THUNDERSPELLAPOCALYPSE = new Spell("Thunder Apocalypse: Storm",400,20,
            "A zone spell that sets everything burning in it's path.");

    public ThunderSpellApocalypse(Properties pProperties) {
        super(pProperties, THUNDERSPELLAPOCALYPSE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            ServerLevel sLevel = (ServerLevel) pLevel;
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(ManaCapability::getMana)
                                    .orElse(0);

            if(pPlayer.getItemInHand(pUsedHand).getItem() == this){
                pPlayer.isInvulnerableTo(DamageSource.LIGHTNING_BOLT);
            }

            if(mCur >= THUNDERSPELLAPOCALYPSE.getManaCost()) {
                BlockPos block = pPlayer.blockPosition();

                Random random = new Random();

                for (int i = -7; i <= 7; i++) {
                    for (int j = -7; j <= 7; j++) {
                        BlockPos newSpot = block.offset(i, 0, j);
                        float rand = random.nextFloat();
                        float rainChance= pLevel.isRaining()?0.75f:0.25f;
                        if (rand >= rainChance)
                            if(i != 0 && j!= 0)
                                EntityType.LIGHTNING_BOLT.spawn(sLevel,null, pPlayer,newSpot,
                                        MobSpawnType.TRIGGERED,true,true);

                    }
                }
                ManaManager.get(pPlayer.level).loseMana(mCur - THUNDERSPELLAPOCALYPSE.getManaCost());
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
