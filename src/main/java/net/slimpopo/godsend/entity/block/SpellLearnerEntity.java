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
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.ModBlockEntity;
import net.slimpopo.godsend.manasystem.network.PacketSyncManaToClient;
import net.slimpopo.godsend.manasystem.network.SpellLearnerPacketClient;
import net.slimpopo.godsend.manasystem.network.SpellLearnerPacketServer;
import net.slimpopo.godsend.setup.Messages;
import org.jetbrains.annotations.Nullable;

public class SpellLearnerEntity extends BaseBlockEntity{
    public static final int SPELL_TIMER = 20;
    public int spell_tick = 0;
    public static final Component title = new TranslatableComponent("container."+ GodSend.MOD_ID+".spell_learner");

    public SpellLearnerEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState, 29);
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
        if(++this.spell_tick >= SPELL_TIMER){

        }
        super.tick();
    }
}
