package net.slimpopo.godsend.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier FLAME = new ForgeTier(3,300,1.0f,
            5f,0,BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.MONSTERSOUL.get()));

    public static final ForgeTier ICE = new ForgeTier(3,300,1.0f,
            5f,0,BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.MONSTERSOUL.get()));
}
