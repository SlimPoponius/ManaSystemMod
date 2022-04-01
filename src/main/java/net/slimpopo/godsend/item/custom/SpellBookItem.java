package net.slimpopo.godsend.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.spellbook.SpellBookCapability;
import net.slimpopo.godsend.capability.spellbook.SpellBookManager;
import net.slimpopo.godsend.capability.spellbook.SpellBookProvider;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.spellbook.PacketSpellBookPlayerHandler;
import net.slimpopo.godsend.manasystem.network.spellbook.PacketSpellBookSyncToClient;
import net.slimpopo.godsend.manasystem.network.spellbook.PacketSpellBookSyncToServer;
import net.slimpopo.godsend.other.SpellList;
import net.slimpopo.godsend.setup.Messages;
import net.slimpopo.godsend.util.InventoryUtil;

import java.util.ArrayList;
import java.util.List;

public class SpellBookItem extends Item {
    ItemStack spellOne, spellTwo, spellThree;
    private String sp1Nm, sp2Nm, sp3Nm, currentSpell, nextSpell;

    private boolean spellSet = false;
    private List<ItemStack> mySpells = new ArrayList<ItemStack>();
    private int spellIndex = -1;
    public SpellBookItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(!pLevel.isClientSide) {
            boolean hasSpells =pPlayer.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                    .map(SpellBookCapability::hasSpells)
                    .orElse(false);

            if(!hasSpells){
                //Check if spNm(1,2,3) has any string in it
                if(sp1Nm !="" ||sp2Nm != "" || sp3Nm != ""){
//                    SpellBookManager.get(pPlayer.level).setSpellsForInfo(sp1Nm,sp2Nm,sp3Nm);
                }
                else{
                    //no spells set to player
                    pPlayer.sendMessage(new TextComponent("no spells found. Load some spells into the book."),pPlayer.getUUID());
                }
            }
            else{
                if(spellSet == false)
                    initSpells(pPlayer);

                if(findIndexOfSpell(pPlayer) == -1) {
                    currentSpell = SpellBookManager.get(pPlayer.level).getSpellOne();
                    nextSpell = SpellBookManager.get(pPlayer.level).getNextSpell(currentSpell);
                    pPlayer.getInventory().add(SpellList.getItemStack(currentSpell));
                }
                else{
                    currentSpell = nextSpell;
                    nextSpell = SpellBookManager.get(pPlayer.level).getNextSpell(currentSpell);

                    replaceSpell(pPlayer,findIndexOfSpell(pPlayer),SpellList.getItemStack(currentSpell));

                }
            }
            SpellBookManager.get(pPlayer.level).setSpells(sp1Nm, sp2Nm, sp3Nm);
            Messages.sendToServer(new PacketSpellBookPlayerHandler());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void openItemGui(Player player ,ItemStack pStack, InteractionHand pHand) {
        Minecraft.getInstance().setScreen(new BookEditScreen(player, pStack, pHand));

    }

    public void initSpells(Player player){
        if(mySpells.size() > 0)
            mySpells.clear();

        sp1Nm = player.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                .map(SpellBookCapability::getSpellOne)
                .orElse("");

        sp2Nm = player.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                .map(SpellBookCapability::getSpellTwo)
                .orElse("");

        sp3Nm = player.getCapability(SpellBookProvider.SPELLBOOK_CAP)
                .map(SpellBookCapability::getSpellThree)
                .orElse("");

        Item spell1 = SpellList.getByItemStack(sp1Nm);
        Item spell2 = SpellList.getByItemStack(sp2Nm);
        Item spell3 = SpellList.getByItemStack(sp3Nm);

        //Get Spell From List
        if (spell1 != null) {
            spellOne = new ItemStack(spell1);
            mySpells.add(spellOne);
        }
        if (spell2 != null) {
            spellTwo = new ItemStack(spell2);
            mySpells.add(spellTwo);
        }
        if (spell3 != null) {
            spellThree = new ItemStack(spell3);
            mySpells.add(spellThree);
        }

        spellIndex = 0;
        spellSet = true;
    }

    private void nextSpell(Player player){
        //find spell index if any exists
        int slotIndexOfSpell = findIndexOfSpell(player);

        //check if any spellIndex is found
        if(!mySpells.isEmpty()){
            if(slotIndexOfSpell == -1){
                player.getInventory().add(mySpells.get(spellIndex));
            }
            else{
                spellIndex += 1;
                if(spellIndex >= mySpells.size()){
                    spellIndex = 0;
                }

                replaceSpell(player,slotIndexOfSpell,mySpells.get(spellIndex));
                player.sendMessage(new TextComponent(""+ spellIndex),
                    player.getUUID());
            }
        }
        else{
            spellIndex = -1;
            player.sendMessage(new TextComponent("No spells equipped."),
                    player.getUUID());
        }


//        if(!mySpells.isEmpty()){
//            if(slotIndexOfSpell == -1){
//                player.getInventory().add(mySpells.get(spellIndex));
//            }
//            else {
//                if (spellIndex + 1 >= mySpells.size()) {
//                    spellIndex = 0;
//                }
//                else{
//                    spellIndex++;
//                }
//
//                replaceSpell(player, slotIndexOfSpell, mySpells.get(spellIndex));
//            }
//            player.sendMessage(new TextComponent(""+ spellIndex),
//                    player.getUUID());
//        }
//        else{
//            removeSpells(player);
//            spellIndex = -1;
//            player.sendMessage(new TextComponent("No spells equipped."),
//                    player.getUUID());
//            spellSet = false;
//        }

    }

    private void replaceSpell(Player player,int slot, ItemStack stack){
        player.getInventory().setItem(slot,stack);
    }

    private void removeSpells(Player player){
        for(ItemStack item: mySpells){
            player.getInventory().removeItem(item);
        }
        spellIndex = -1;
    }

    private int findIndexOfSpell(Player player){
        int index = 0;
        for(ItemStack item: player.getInventory().items){
            if(item.getItem() instanceof SpellItem){
                return index;
            }
            index++;
        }
        return -1;
    }

    public void UpdateSpellList(Player player,ItemStack i1, ItemStack i2, ItemStack i3){
        if (i1 != ItemStack.EMPTY)
            sp1Nm = SpellList.ItemKey(i1.getItem());
        else
            sp1Nm = "";

        if (i2 != ItemStack.EMPTY)
            sp2Nm = SpellList.ItemKey(i2.getItem());
        else
            sp2Nm = "";

        if (i3 != ItemStack.EMPTY)
            sp3Nm = SpellList.ItemKey(i3.getItem());
        else
            sp3Nm = "";

//        SpellBookManager.get(player.level).setSpells(sp1Nm,sp2Nm,sp3Nm);
//        Messages.sendToServer(new PacketSpellBookPlayerHandler());
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        if(pEntity instanceof  Player player){

        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
}
