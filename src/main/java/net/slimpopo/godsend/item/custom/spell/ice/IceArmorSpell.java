package net.slimpopo.godsend.item.custom.spell.ice;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.capability.mana.ManaCapability;
import net.slimpopo.godsend.capability.mana.ManaManager;
import net.slimpopo.godsend.capability.mana.PlayerManaProvider;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.spell.SpellItem;
import net.slimpopo.godsend.manasystem.network.PacketManaManagePlayerHandler;
import net.slimpopo.godsend.other.Spell;
import net.slimpopo.godsend.setup.Messages;

public class IceArmorSpell extends SpellItem {
    private static final Spell ICEARMORSPELL = new Spell("Ice Armor Spell",25,5,
            "A basic armor spell that covers you in ice armor. Can be removed with either the spell or removing the helmet.");

    public IceArmorSpell(Item.Properties pProperties) {
        super(pProperties, ICEARMORSPELL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack boots = new ItemStack(ModItems.ICE_BOOT.get());
        ItemStack legs = new ItemStack(ModItems.ICE_LEG.get());
        ItemStack chest = new ItemStack(ModItems.ICE_CHEST.get());
        ItemStack head = new ItemStack(ModItems.ICE_HELMET.get());

        if(!pLevel.isClientSide){
            int mCur = pPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .map(ManaCapability::getMana)
                    .orElse(0);

            if(mCur >= ICEARMORSPELL.getManaCost() || AlreadyHasArmorOn(pPlayer)) {
                //1. Check if player has armor currently on...
                if (hasArmorOn(pPlayer)) {
                    if (AlreadyHasArmorOn(pPlayer)) {
                        //remove Armor Pieces
                        pPlayer.getInventory().armor.set(0, ItemStack.EMPTY);
                        pPlayer.getInventory().armor.set(1, ItemStack.EMPTY);
                        pPlayer.getInventory().armor.set(2, ItemStack.EMPTY);
                        pPlayer.getInventory().armor.set(3, ItemStack.EMPTY);
                        pPlayer.removeEffect(MobEffects.FIRE_RESISTANCE);
                        pPlayer.sendMessage(new TextComponent("Spell has been deactivated"), pPlayer.getUUID());
                        return super.use(pLevel, pPlayer, pUsedHand);
                    } else {
                        //if they do, remove armor pieces and add to inventory
                        if (pPlayer.getInventory().getArmor(0) != ItemStack.EMPTY)
                            pPlayer.getInventory().add(pPlayer.getInventory().getArmor(0));
                        if (pPlayer.getInventory().getArmor(1) != ItemStack.EMPTY)
                            pPlayer.getInventory().add(pPlayer.getInventory().getArmor(1));
                        if (pPlayer.getInventory().getArmor(2) != ItemStack.EMPTY)
                            pPlayer.getInventory().add(pPlayer.getInventory().getArmor(2));
                        if (pPlayer.getInventory().getArmor(3) != ItemStack.EMPTY)
                            pPlayer.getInventory().add(pPlayer.getInventory().getArmor(3));
                    }
                }
                pPlayer.getInventory().armor.set(0, boots);
                pPlayer.getInventory().armor.set(1, legs);
                pPlayer.getInventory().armor.set(2, chest);
                pPlayer.getInventory().armor.set(3, head);

                ManaManager.get(pPlayer.level).loseMana(mCur - ICEARMORSPELL.getManaCost());

                Messages.sendToServer(new PacketManaManagePlayerHandler());
            }
            else{
                pPlayer.sendMessage(new TextComponent("Insufficient mana cost"),pPlayer.getUUID());
            }

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide) {
            Player sPlayer = pContext.getPlayer();
            Player player = Minecraft.getInstance().player;

            ItemStack boots = new ItemStack(ModItems.ICE_BOOT.get());
            ItemStack legs = new ItemStack(ModItems.ICE_LEG.get());
            ItemStack chest = new ItemStack(ModItems.ICE_CHEST.get());
            ItemStack head = new ItemStack(ModItems.ICE_HELMET.get());

            boots.enchant(Enchantments.FROST_WALKER, 10);

            if (hasArmorOn(sPlayer)) {
                if (AlreadyHasArmorOn(sPlayer)) {
                    //remove Armor Pieces
                    sPlayer.getInventory().armor.set(0, ItemStack.EMPTY);
                    sPlayer.getInventory().armor.set(1, ItemStack.EMPTY);
                    sPlayer.getInventory().armor.set(2, ItemStack.EMPTY);
                    sPlayer.getInventory().armor.set(3, ItemStack.EMPTY);
                    sPlayer.removeEffect(MobEffects.FIRE_RESISTANCE);
                    sPlayer.sendMessage(new TextComponent(player.getDisplayName() + "has used the " + ICEARMORSPELL.getSpellName() + " spell on you"), sPlayer.getUUID());
                } else {
                    //if they do, remove armor pieces and add to inventory
                    ItemStack headPc = sPlayer.getInventory().getArmor(3) != ItemStack.EMPTY ?
                            sPlayer.getInventory().getArmor(3).copy() : ItemStack.EMPTY;
                    ItemStack chestPc = sPlayer.getInventory().getArmor(2) != ItemStack.EMPTY ?
                            sPlayer.getInventory().getArmor(2).copy() : ItemStack.EMPTY;
                    ItemStack legsPc = sPlayer.getInventory().getArmor(1) != ItemStack.EMPTY ?
                            sPlayer.getInventory().getArmor(1).copy() : ItemStack.EMPTY;
                    ItemStack boostPc = sPlayer.getInventory().getArmor(0) != ItemStack.EMPTY ?
                            sPlayer.getInventory().getArmor(0).copy() : ItemStack.EMPTY;


                    if (sPlayer.getInventory().getArmor(0) != ItemStack.EMPTY)
                        sPlayer.getInventory().add(boostPc);
                    if (sPlayer.getInventory().getArmor(1) != ItemStack.EMPTY)
                        sPlayer.getInventory().add(legsPc);
                    if (sPlayer.getInventory().getArmor(2) != ItemStack.EMPTY)
                        sPlayer.getInventory().add(chestPc);
                    if (sPlayer.getInventory().getArmor(3) != ItemStack.EMPTY)
                        sPlayer.getInventory().add(headPc);
                }
            }

            sPlayer.getInventory().armor.set(0, boots);
            sPlayer.getInventory().armor.set(1, legs);
            sPlayer.getInventory().armor.set(2, chest);
            sPlayer.getInventory().armor.set(3, head);

            int mCur = ManaManager.get(player.level).getMana();

            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                playerMana.setMana(mCur - ICEARMORSPELL.getManaCost());
                player.sendMessage(new TextComponent("You activated the  " + ICEARMORSPELL.getSpellName() + " on " + sPlayer.getDisplayName() + "!"),
                        player.getUUID());
            });
        }
        return InteractionResult.SUCCESS;
    }


    private boolean hasArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() || !chestplate.isEmpty()
                || !leggings.isEmpty() || !boots.isEmpty();
    }

    private boolean AlreadyHasArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return boots.getItem() == ModItems.ICE_BOOT.get() && leggings.getItem() == ModItems.ICE_LEG.get()
                && chestplate.getItem() == ModItems.ICE_CHEST.get() && helmet.getItem() == ModItems.ICE_HELMET.get();
    }
}
