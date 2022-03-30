package net.slimpopo.godsend.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.TornadoEntity;
import net.slimpopo.godsend.entity.models.BigTornadoModel;
import net.slimpopo.godsend.entity.models.SmallTornadoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BigTornadoRenderer<Type extends TornadoEntity> extends GeoEntityRenderer<TornadoEntity> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(GodSend.MOD_ID,"textures/entity/tornado.png");

    public BigTornadoRenderer(EntityRendererProvider.Context context) {
        super(context, new BigTornadoModel<>());
    }

    @Override
    public void renderEarly(TornadoEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        animatable.setIsBig(true);
        stackIn.scale(8,8,8);
    }

    @Override
    public ResourceLocation getTextureLocation(TornadoEntity pEntity) {
        return TEXTURE;
    }
}
