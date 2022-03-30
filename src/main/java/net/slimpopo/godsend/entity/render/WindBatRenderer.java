package net.slimpopo.godsend.entity.render;

import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.WindBatEntity;
import net.slimpopo.godsend.entity.models.WindBatModel;

public class WindBatRenderer extends MobRenderer<WindBatEntity, WindBatModel> {

    protected final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/wind_bat.png");

    public WindBatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindBatModel(renderManager.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(WindBatEntity pEntity) {
        return TEXTURE;
    }
}
