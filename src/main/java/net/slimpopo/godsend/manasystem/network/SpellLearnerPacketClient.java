package net.slimpopo.godsend.manasystem.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.slimpopo.godsend.entity.block.SpellLearnerEntity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class SpellLearnerPacketClient {
    public final BlockPos spellLearnPos;

    public SpellLearnerPacketClient(BlockPos pos) {
        this.spellLearnPos = pos;
    }

    public SpellLearnerPacketClient(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.spellLearnPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                    () -> () -> success.set(updateSpellLearner(this.spellLearnPos)));
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

    public static boolean updateSpellLearner(BlockPos pos) {
        final BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(pos);
        if(blockEntity instanceof  final SpellLearnerEntity spell){
            return true;
        }
        return false;
    }
}
