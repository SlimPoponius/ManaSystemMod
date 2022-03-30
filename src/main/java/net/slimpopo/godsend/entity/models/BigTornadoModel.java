package net.slimpopo.godsend.entity.models;


import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.TornadoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BigTornadoModel<Type extends TornadoEntity> extends AnimatedGeoModel<Type> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(GodSend.MOD_ID, "tornado_big"), "main");
    private static final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/tornado.png");
    private static final ResourceLocation MODEL = new ResourceLocation(GodSend.MOD_ID,"geo/tornado.geo.json");
    private static final ResourceLocation ANIMATION = new ResourceLocation(GodSend.MOD_ID,"animations/tornado.json");

    //private final ModelPart earth_warrior;
//    private final ModelPart hitbox;

//    public EarthWarriorModel(ModelPart root) {
//        this.earth_warrior = root.getChild("earth_warrior");
////        this.hitbox = root.getChild("hitbox");
//    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition lower = root.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(39, 0).addBox(-3.0F, -5.0F, -2.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(-5.0F, -7.0F, -4.0F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Mid = root.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, -10.0F, -5.0F, 11.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition upper = root.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -13.0F, -6.0F, 13.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(27, 32).addBox(-5.0F, -14.0F, -4.0F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(33, 16).addBox(-4.0F, -15.0F, -3.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }



    @Override
    public ResourceLocation getModelLocation(Type object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(Type object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Type animatable) {
        return ANIMATION;
    }
}
