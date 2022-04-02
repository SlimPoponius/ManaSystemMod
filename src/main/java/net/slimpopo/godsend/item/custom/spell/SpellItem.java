package net.slimpopo.godsend.item.custom.spell;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.slimpopo.godsend.other.Spell;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpellItem extends Item {
    public final Spell spell;

    public SpellItem(Properties pProperties,Spell spell) {
        super(pProperties);
        this.spell = spell;

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent("Mana Cost: "+ spell.getManaCost() + "\nDescription: "+spell.getDescription()));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


}
