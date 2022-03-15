package net.slimpopo.godsend.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.slimpopo.godsend.entity.ModBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BaseBlockEntity extends BlockEntity{
    public final int size;
    protected int timer;
    protected boolean requiresUpdate;

    public final ItemStackHandler inventory;
    protected LazyOptional<ItemStackHandler> handler;

    public BaseBlockEntity(BlockPos pWorldPosition, BlockState pBlockState, int size) {
        super(ModBlockEntity.SPELL_LEARNER.get(), pWorldPosition, pBlockState);
        if(size <= 0){
            size = 1;
        }
        this.size = size;
        this.inventory = createInventory();
        this.handler = LazyOptional.of(() -> this.inventory);
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ?
                this.handler.cast() : super.getCapability(cap,side);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("Inventory",this.inventory.serializeNBT());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.inventory.deserializeNBT(pTag.getCompound("Inventory"));
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.handler.invalidate();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    private ItemStackHandler createInventory() {
        return new ItemStackHandler(this.size){
            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                BaseBlockEntity.this.update();
                return super.extractItem(slot, amount, simulate);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                BaseBlockEntity.this.update();
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ItemStack extractItem(int slot){
        final int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.extractItem(slot,count,false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack insertItem(int slot, ItemStack stack){
        ItemStack copy = stack.copy();
        stack.shrink(copy.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.insertItem(slot,copy,false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack getItemInSlot(int slot){
        return this.handler.map(inv -> inv.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    public void update(){
        requestModelDataUpdate();
        setChanged();
        if(this.level != null){
            this.level.setBlockAndUpdate(this.worldPosition,getBlockState());
        }
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public void tick() {
        this.timer++;
        if(this.requiresUpdate && this.level != null){
            update();
            this.requiresUpdate = false;
        }

    }
}
