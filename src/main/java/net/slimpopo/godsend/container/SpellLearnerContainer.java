package net.slimpopo.godsend.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.entity.block.SpellLearnerEntity;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.other.SpellList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SpellLearnerContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess containerAccess;
    private int spellBookSlot;

    //Client Constructor
    public SpellLearnerContainer(int pContainerId, Inventory playerInv) {
        this(pContainerId,playerInv,new ItemStackHandler(29),BlockPos.ZERO);
        updateSlotContainers();

    }

    //Server Costructor
    public SpellLearnerContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos bpos) {
        super(ModContainerEntity.SPELLLEARN_CONTAINER.get(),id);
        this.containerAccess = ContainerLevelAccess.create(playerInv.player.level,bpos);

        final int slotSizePlus2 = 18,startX=18,startY=19,hotbarY=144, spellY = 42;


        //Top Part (SpellBook and Spell Slot holder)
        for(int col = 0; col < 3; col++){
            addSlot(new SlotItemHandler(slots,col+1,53+col*(slotSizePlus2+7),startY));
        }
        //Middle Section(Spell Inventory) -- slot index == 5
        for(int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if(row % 2 == 0) {
                    addSlot(new SlotItemHandler(slots, 4+ row * 5 + column, startX + column * slotSizePlus2,
                            spellY + row * (slotSizePlus2 + 2)));
                }
                else{
                    addSlot(new SlotItemHandler(slots,4+ row * 5 + column,68+column * slotSizePlus2,
                            spellY+row*(slotSizePlus2 + 2)));
                }

            }
        }
        //Player Bottom Inventory
        for(int column = 0; column < 9; column++){
            addSlot(new Slot(playerInv,column,8 + column * slotSizePlus2,hotbarY));
        }
        resetSlotContainers();
        updateSlotContainers();

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        //updateSlotContainers();
        return stillValid(this.containerAccess,pPlayer, ModBlocks.SPELLLEARNER.get());
    }

    public static MenuConstructor getServerContainer(SpellLearnerEntity spellLearner, BlockPos pos){
        return (id,playerInv,player) -> new SpellLearnerContainer(id,playerInv,spellLearner.inventory,pos);
    }

    @Override
    protected void clearContainer(Player p_150412_, Container p_150413_) {
        for(int i = 0; i < 4; i++){
            this.slots.get(i).set(ItemStack.EMPTY);
            this.slots.get(i).setChanged();
        }
        super.clearContainer(p_150412_, p_150413_);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(pIndex);
        if(slot.hasItem()){
            final ItemStack stack = slot.getItem();
            retStack = stack.copy();
            //Our Inventory(not player's)
            if( pIndex < 28){
                if (!moveItemStackTo(stack, 29, 37, false)) {
                    updateSlotContainers();
                    return ItemStack.EMPTY;
                }
            }
            //Player Inventory, not ours
            else if(pIndex >= 28){
                if (!moveItemStackTo(stack, 28, this.slots.size(), false))
                    return ItemStack.EMPTY;
            }

            if(stack.isEmpty()){
                slot.set(stack);

            }else {
                slot.setChanged();
            }

//            if(stack.getCount() == retStack.getCount())
//                return ItemStack.EMPTY;

            updateSlotContainers();
            slot.onTake(pPlayer,stack);
        }
        return retStack;
    }

    public void updateSlotContainers() {
        int spellListCounter = 0;
        for(int i = 0; i < 28;i++){
            if(i > 2) {
                if (spellListCounter < SpellList.Spells.size()) {
                    this.getSlot(i).set(SpellList.getStack(spellListCounter));
                    spellListCounter++;
                }
            }
            this.getSlot(i).setChanged();
            if(spellListCounter >= SpellList.Spells.size()){
                break;
            }
        }
        this.broadcastChanges();
    }

    public void resetSlotContainers() {
        for(int i = 0; i < 2;i++){
            this.getSlot(i).set(ItemStack.EMPTY);
        }
        updateSlotContainers();
        this.broadcastChanges();
    }

    @Override
    public void slotsChanged(Container pInventory) {
        updateSlotContainers();
        super.slotsChanged(pInventory);
    }

    public int findInstanceOfSpellBook(Player player){
        return player.getInventory().findSlotMatchingItem(new ItemStack(ModItems.SPELLBOOK.get()));
    }

//    @Override
//    public boolean canDragTo(Slot pSlot) {
//        int slot = pSlot.index;
//        return super.canDragTo(pSlot);
//    }
}
