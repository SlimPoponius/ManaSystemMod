package net.slimpopo.godsend.entity.render;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.mobs.IceWolfEntity;
import net.slimpopo.godsend.entity.models.IceWolfModel;

public class IceWolfRenderer extends MobRenderer<IceWolfEntity, WolfModel<IceWolfEntity>> {

    protected final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/ice_wolf.png");

    public IceWolfRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WolfModel<>(renderManager.bakeLayer(ModelLayers.WOLF)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(IceWolfEntity pEntity) {
        return TEXTURE;
    }
}
