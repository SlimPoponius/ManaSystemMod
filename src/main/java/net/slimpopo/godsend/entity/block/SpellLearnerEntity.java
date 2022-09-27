package net.slimpopo.godsend.entity.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.ModBlockEntity;
import net.slimpopo.godsend.manasystem.network.PacketSyncManaToClient;
import net.slimpopo.godsend.manasystem.network.SpellLearnerPacketClient;
import net.slimpopo.godsend.manasystem.network.SpellLearnerPacketServer;
import net.slimpopo.godsend.setup.Messages;
import org.jetbrains.annotations.Nullable;

public class SpellLearnerEntity extends BaseBlockEntity{
    private enum SPELL_TYPE{SAND, BALANCE,DEATH, BASIC}

    public static final int SPELL_TIMER = 20;
    public int spell_tick = 0;
    public static final Component title = new TranslatableComponent("container."+ GodSend.MOD_ID+".spell_learner");
    private SPELL_TYPE spellLearnType = SPELL_TYPE.BASIC;


    public SpellLearnerEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState, 33);
        if(Minecraft.getInstance().level.getBlockState(pWorldPosition.below()).getBlock() == Blocks.SAND){
            this.spellLearnType = SPELL_TYPE.SAND;
        }
        else if(Minecraft.getInstance().level.getBlockState(pWorldPosition.below()).getBlock() == Blocks.GLOWSTONE){
            this.spellLearnType = SPELL_TYPE.BALANCE;
        }
        else if(Minecraft.getInstance().level.getBlockState(pWorldPosition.below()).getBlock() == Blocks.SOUL_SAND){
            this.spellLearnType = SPELL_TYPE.DEATH;
        }
    }

    public SPELL_TYPE getSpellLearnType() {
        return spellLearnType;
    }

    @Override
    public CompoundTag getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return super.getUpdatePacket();
    }

    @Override
    public void tick() {
        super.tick();
    }
}
