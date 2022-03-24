package net.slimpopo.godsend.entity.render;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.IronGolem;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.models.FlameGolemModel;

public class FlameGolemRenderer extends MobRenderer<FlameGolemEntity, IronGolemModel<FlameGolemEntity>> {

    protected final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/flame_golem.png");

    public FlameGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IronGolemModel<>(renderManager.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(FlameGolemEntity pEntity) {
        return TEXTURE;
    }
}
