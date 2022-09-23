package net.slimpopo.godsend.entity.render;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.SkeletonSummonEntity;
import net.slimpopo.godsend.entity.mobs.ZombieSummonEntity;

public class SkeletonSummonRenderer extends MobRenderer<SkeletonSummonEntity, SkeletonModel<SkeletonSummonEntity>> {
    protected final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/skeletonsummon.png");

    public SkeletonSummonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SkeletonModel<>(renderManager.bakeLayer(ModelLayers.SKELETON)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonSummonEntity pEntity) {
        return TEXTURE;
    }
}
