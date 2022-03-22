package net.slimpopo.godsend.item.custom.spell;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.other.Spell;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class FlameWeaponSpell extends SpellItem {
    public static final Spell FLAMEWEAPONSPELL = new Spell("Flame Weapon Spell",15,1,
            "A spell that allows you to conjure weapons of the fire element. using the spell will conjure a different weapon each time.");
    public static int weaponMode = 0;

    public FlameWeaponSpell(Properties pProperties) {
        super(pProperties, FLAMEWEAPONSPELL);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack GREATSWD = new ItemStack(ModItems.FLAME_GREATSWORD.get());
        ItemStack BOW = new ItemStack(ModItems.FLAME_BOW.get());

        if(!pLevel.isClientSide) {
            int availableSlot = findNextAvailableSlot(pPlayer);
            if(availableSlot == -1){
                pPlayer.sendMessage(new TextComponent("Spell not working. Free up some space"),pPlayer.getUUID());
            }
            else {
                int weaponSlot = findWeaponType(pPlayer);
                int mCur = ManaManager.get(pPlayer.level).getMana();

                //No weapon of type found
                if (weaponSlot == -1) {
                    //Add Item to PInventory
                    setWeaponToSlot(pPlayer,GREATSWD);
                    weaponMode = 1;


                    pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                        playerMana.setMana(mCur - FLAMEWEAPONSPELL.getManaCost());
                        pPlayer.sendMessage(new TextComponent("You summoned a Greatsword using the " + FLAMEWEAPONSPELL.getSpellName()),
                                pPlayer.getUUID());

                    });
                }
                //WeaponType is found
                else {
                    weaponMode = checkWeaponMode(pPlayer);
                    if(weaponMode == 1){
                        replaceWeaponToSlot(pPlayer,BOW);
                        weaponMode = 2;
                        pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                            playerMana.setMana(-FLAMEWEAPONSPELL.getManaCost());
                            pPlayer.sendMessage(new TextComponent("You summoned a Bow using the " + FLAMEWEAPONSPELL.getSpellName()),
                                    pPlayer.getUUID());

                        });

                    }
                    else if(weaponMode == 2){
                        replaceWeaponToSlot(pPlayer,ItemStack.EMPTY);

                        weaponMode = 0;
                        pPlayer.sendMessage(new TextComponent("You returned your weapon to the mana void"),
                                pPlayer.getUUID());
                    }
                    else{
                        deleteItem(pPlayer);
                        pPlayer.sendMessage(new TextComponent("You returned your weapon to the mana void"),
                                pPlayer.getUUID());
                    }

                }
            }


            //If Item exists already change the Item that already exists

            //Else make Itemstack empty
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public int checkWeaponMode(Player player){
        for(ItemStack item: player.getInventory().items){
            if(item.getItem() == ModItems.FLAME_GREATSWORD.get()){
                return 1;
            }
            else if(item.getItem() == ModItems.FLAME_BOW.get()){
                return 2;
            }
        }
        return 0;
    }

    public int findSpellSlot(Player player){
        int slotNum = 0;
        for(ItemStack item: player.getInventory().items){
            if(item.getItem() == ModItems.FLAMESPELL_WEAPONIZE.get()){
                return slotNum;
            }
            slotNum++;
        }
        return -1;
    }

    public int findWeaponType(Player player){
        int slotNum = 0;
        for(ItemStack item: player.getInventory().items){
            if(item.getItem() == ModItems.FLAME_GREATSWORD.get() || item.getItem() == ModItems.FLAME_BOW.get()){
                return slotNum;
            }
            slotNum++;
        }
        return -1;
    }

    public int findNextAvailableSlot(Player player){
        int slotNum = 0;
        for(ItemStack item: player.getInventory().items){
            if(item == ItemStack.EMPTY){
                return slotNum;
            }
            slotNum++;
        }
        return -1;
    }


    public void setWeaponToSlot(Player player, ItemStack itemPlace){
        int slotNum = findSpellSlot(player);
        int slotNext = slotNum + 1;
        int nextEmpty = findNextAvailableSlot(player);
        ItemStack item = player.getInventory().getItem(slotNext);
        if( item != ItemStack.EMPTY){
            player.getInventory().add(nextEmpty,item);
            player.getInventory().getItem(slotNext).setCount(0);
            player.getInventory().add(slotNext,itemPlace);
        }
        else{
            player.getInventory().add(slotNext,itemPlace);
        }
    }

    public void deleteItem(Player player){
        for(ItemStack item: player.getInventory().items){
            if(item.getItem() == ModItems.FLAME_GREATSWORD.get() || item.getItem() == ModItems.FLAME_BOW.get()){
                item.setCount(0);
            }
        }
    }

    public void replaceWeaponToSlot(Player player, ItemStack itemPlace){
        ItemStack GREATSWD = new ItemStack(ModItems.FLAME_GREATSWORD.get());
        ItemStack BOW = new ItemStack(ModItems.FLAME_BOW.get());
        int slotNum = findWeaponType(player);
        if( slotNum == -1){
            return;
        }
        else{
            deleteItem(player);
            player.getInventory().removeItem(GREATSWD);
            player.getInventory().removeItem(BOW);
            player.getInventory().add(slotNum,itemPlace);
        }
    }
}
