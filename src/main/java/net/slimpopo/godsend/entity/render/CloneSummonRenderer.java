package net.slimpopo.godsend.entity.render;

import com.google.common.collect.ImmutableMap;
import com.ibm.icu.impl.locale.XCldrStub;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.CloneSummonEntity;

public class CloneSummonRenderer extends HumanoidMobRenderer<CloneSummonEntity, PlayerModel<CloneSummonEntity>>{

    private EntityRendererProvider.Context renderManage;
    private Player playerModel;
    protected final ResourceLocation TEXTURE = new ResourceLocation(GodSend.MOD_ID,"textures/entity/zombie_summon.png");

//    public CloneSummonRenderer(EntityRendererProvider.Context renderManager) {
//       this(renderManager, ModelLayers.PLAYER,
//                ModelLayers.PLAYER_INNER_ARMOR,
//                ModelLayers.PLAYER_OUTER_ARMOR);
//        this.renderManage = renderManager;
//    }

    public CloneSummonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlayerModel<>(renderManager.bakeLayer(ModelLayers.PLAYER),false),0.5f);
        this.renderManage = renderManager;
        //this.playerModel = Minecraft.getInstance().player;

    }

    @Override
    public ResourceLocation getTextureLocation(CloneSummonEntity pEntity) {
        if(pEntity.getPlayerCloned() != null){
            return Minecraft.getInstance().player.getSkinTextureLocation();
        }
        return Minecraft.getInstance().player.getSkinTextureLocation();
    }

    @Override
    public PlayerModel<CloneSummonEntity> getModel() {

        if(playerModel != null && DefaultPlayerSkin.getSkinModelName(playerModel.getUUID()).equals("slim")){
            this.layers.clear();
            this.addLayer(new HumanoidArmorLayer<>(this,new PlayerModel<>(renderManage.bakeLayer(ModelLayers.PLAYER_SLIM_INNER_ARMOR),false),new PlayerModel<>(renderManage.bakeLayer(ModelLayers.PLAYER_SLIM_OUTER_ARMOR),false)));
            return new PlayerModel<>(renderManage.bakeLayer(ModelLayers.PLAYER_SLIM), true);
        }
        return new PlayerModel<>(renderManage.bakeLayer(ModelLayers.PLAYER), false);

    }

}
