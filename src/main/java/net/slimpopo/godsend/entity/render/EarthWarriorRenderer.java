package net.slimpopo.godsend.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.EarthWarriorEntity;
import net.slimpopo.godsend.entity.models.EarthWarriorModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EarthWarriorRenderer<Type extends EarthWarriorEntity> extends GeoEntityRenderer<EarthWarriorEntity> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(GodSend.MOD_ID,"textures/entity/earth_warrior.png");

    public EarthWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new EarthWarriorModel());
    }

    @Override
    public void renderEarly(EarthWarriorEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        stackIn.scale(2,2,2);
    }

    @Override
    public ResourceLocation getTextureLocation(EarthWarriorEntity pEntity) {
        return TEXTURE;
    }
}
