package net.slimpopo.godsend.render.enitity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.projectile.IceArrowEntity;

@OnlyIn(Dist.CLIENT)
public class IceArrowRenderer extends ArrowRenderer<IceArrowEntity> {
    public IceArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(IceArrowEntity pEntity) {
        Item referenceItem = pEntity.getReferenceItem();
        return new ResourceLocation(GodSend.MOD_ID,"textures/entity/projectiles/" + referenceItem.getRegistryName().getPath() +".png");
    }
}
