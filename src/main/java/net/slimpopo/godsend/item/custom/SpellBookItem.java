package net.slimpopo.godsend.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.item.ModItems;

public class SpellBookItem extends Item {

    public SpellBookItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(pPlayer.getItemInHand(pUsedHand).hasTag()){
            String spellSlot1 = pPlayer.getItemInHand(pUsedHand).getTag().getString("godsend.spellslotone");
            String spellSlot2 = pPlayer.getItemInHand(pUsedHand).getTag().getString("godsend.spellslottwo");
            String spellSlot3 = pPlayer.getItemInHand(pUsedHand).getTag().getString("godsend.spellslotthree");

            pPlayer.sendMessage(new TextComponent(spellSlot1 + "\n" + spellSlot2 + "\n" + spellSlot3),
                    pPlayer.getUUID());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void openItemGui(Player player ,ItemStack pStack, InteractionHand pHand) {
        Minecraft.getInstance().setScreen(new BookEditScreen(player, pStack, pHand));

    }

    public void addNbtDataToBook(Player player, ItemStack spellBook, ItemStack i1, ItemStack i2, ItemStack i3){
        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("godsend.spellslotone",i1.getDisplayName().getString());
        nbtData.putString("godsend.spellslottwo",i2.getDisplayName().getString());
        nbtData.putString("godsend.spellslotthree",i3.getDisplayName().getString());

        spellBook.setTag(nbtData);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }


}
