package net.slimpopo.godsend.render;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.item.ItemStack;

public class ItemScaleRender extends ItemRenderer {
    public ItemScaleRender(TextureManager p_174225_, ModelManager p_174226_, ItemColors p_174227_, BlockEntityWithoutLevelRenderer p_174228_) {
        super(p_174225_, p_174226_, p_174227_, p_174228_);
    }

    @Override
    public void renderGuiItem(ItemStack pStack, int pX, int pY) {
        super.renderGuiItem(pStack, pX, pY);
    }
}
