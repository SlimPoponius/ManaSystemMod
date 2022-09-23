package net.slimpopo.godsend.entity.render;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.ZombieSummonEntity;
import net.slimpopo.godsend.entity.models.ZombieSummonModel;

public class ZombieSummonRenderer extends MobRenderer<ZombieSummonEntity, ZombieModel<ZombieSummonEntity>> {
    protected final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/zombie_summon.png");

    public ZombieSummonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ZombieModel<>(renderManager.bakeLayer(ModelLayers.ZOMBIE)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieSummonEntity pEntity) {
        return TEXTURE;
    }
}
