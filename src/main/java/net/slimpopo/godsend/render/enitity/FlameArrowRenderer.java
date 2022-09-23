package net.slimpopo.godsend.render.enitity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.projectile.FireArrowEntity;

@OnlyIn(Dist.CLIENT)
public class FlameArrowRenderer extends ArrowRenderer<FireArrowEntity> {
    public FlameArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(FireArrowEntity pEntity) {
        Item referenceItem = pEntity.getReferenceItem();
        return new ResourceLocation(GodSend.MOD_ID,"textures/entity/projectiles/" + referenceItem.getRegistryName().getPath() +".png");
    }
}
