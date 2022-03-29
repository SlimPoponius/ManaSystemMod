package net.slimpopo.godsend.entity.models;


import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.EarthWarriorEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EarthWarriorModel <Type extends EarthWarriorEntity> extends AnimatedGeoModel<Type> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(GodSend.MOD_ID, "earth_warrior"), "main");
    private static final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/earth_warrior.png");
    private static final ResourceLocation MODEL = new ResourceLocation(GodSend.MOD_ID,"geo/earth_warrior.geo.json");
    private static final ResourceLocation ANIMATION = new ResourceLocation(GodSend.MOD_ID,"animations/earth_warrior.json");

    //private final ModelPart earth_warrior;
//    private final ModelPart hitbox;

//    public EarthWarriorModel(ModelPart root) {
//        this.earth_warrior = root.getChild("earth_warrior");
////        this.hitbox = root.getChild("hitbox");
//    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition earth_warrior = partdefinition.addOrReplaceChild("earth_warrior", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 1.0F));

        PartDefinition head = earth_warrior.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, -5.0F, -3.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(35, 0).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 28).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(41, 3).addBox(2.0F, -3.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 11).addBox(2.0F, -6.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 27).addBox(2.0F, -8.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(40, 37).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-1.0F, -6.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 20).addBox(-1.0F, -8.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition body = earth_warrior.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(17, 12).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-3.0F, 8.0F, -2.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 27).addBox(0.0F, -2.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(12, 35).addBox(3.0F, -6.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(0.0F, 1.0F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 1.0F, 0.0F, -0.1309F, 0.0F));

        PartDefinition cube_r1 = left_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 11).addBox(4.0F, -6.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r2 = left_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 0).addBox(2.0F, -4.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2398F, 0.045F, 0.1506F));

        PartDefinition left_lower_arm = left_arm.addOrReplaceChild("left_lower_arm", CubeListBuilder.create().texOffs(40, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 24).addBox(-1.0F, 2.0F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(31, 40).addBox(-1.0F, 5.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.0F, 0.0F, -0.5267F, -0.2163F, 0.0289F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(18, 20).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(31, 33).addBox(-3.0F, 1.0F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 1.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition right_lower_arm = right_arm.addOrReplaceChild("right_lower_arm", CubeListBuilder.create().texOffs(17, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(31, 16).addBox(-2.0F, 2.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(30, 14).addBox(-2.0F, 6.0F, -2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(9, 40).addBox(-2.0F, 6.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(25, 37).addBox(0.0F, 6.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(-1.0F, 6.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 23).addBox(-3.0F, 5.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-4.0F, 3.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 36).addBox(-1.0F, 3.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, -0.5236F, 0.1745F, 0.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 6).addBox(-2.0F, -1.0F, -3.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(41, 17).addBox(-1.0F, 2.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(13, 18).addBox(-1.0F, 3.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(41, 14).addBox(-1.0F, 5.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 10.0F, 1.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 28).addBox(0.0F, 0.0F, -3.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(41, 7).addBox(1.0F, 3.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(1.0F, 4.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 3).addBox(1.0F, 6.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 9.0F, 1.0F, 0.0F, -0.1745F, 0.0F));

//        PartDefinition hitbox = partdefinition.addOrReplaceChild("hitbox", CubeListBuilder.create().texOffs(0, 11).addBox(-8.0F, -23.0F, -5.0F, 16.0F, 23.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64 , 64);
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
