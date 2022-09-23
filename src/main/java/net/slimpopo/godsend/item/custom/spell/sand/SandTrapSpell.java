package net.slimpopo.godsend.item.custom.spell.sand;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class SandTrapSpell extends SpellItem {
    private static final Spell SANDTRAPSPELL = new Spell("Sand Trap Spell", 20, 12,
            "Create a wall of ice that protects your path");

    public SandTrapSpell(Properties pProperties) {
        super(pProperties, SANDTRAPSPELL);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= SANDTRAPSPELL.getManaCost()) {
                ManaManager.get(pPlayer.level).loseMana(mCur - SANDTRAPSPELL.getManaCost());
            }
            Messages.sendToServer(new PacketManaManagePlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockTouched = pContext.getClickedPos();
        BlockState sandTrap = ModBlocks.SANDTRAP.get().defaultBlockState();

        pContext.getLevel().setBlock(blockTouched,sandTrap,11);
        return super.useOn(pContext);
    }
}
