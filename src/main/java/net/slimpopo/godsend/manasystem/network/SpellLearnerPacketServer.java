package net.slimpopo.godsend.manasystem.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class SpellLearnerPacketServer{
    public final BlockPos spellLearnPos;

    public SpellLearnerPacketServer(BlockPos pos){
        this.spellLearnPos = pos;
    }

    public SpellLearnerPacketServer(FriendlyByteBuf buf){
        this(buf.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.spellLearnPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            final BlockEntity blockEntity = ctx.get().getSender().level.getBlockEntity(this.spellLearnPos);
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

}
