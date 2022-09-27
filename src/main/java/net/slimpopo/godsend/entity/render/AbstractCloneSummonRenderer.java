package net.slimpopo.godsend.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.entity.mobs.CloneSummonEntity;

public class AbstractCloneSummonRenderer<T extends CloneSummonEntity, M extends HumanoidModel<T>> extends HumanoidMobRenderer<T,M> {

    public AbstractCloneSummonRenderer(EntityRendererProvider.Context renderManager, M modelLayerA, M modelLayerB, M modelLayerC) {
        super(renderManager, modelLayerA, 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, modelLayerB, modelLayerC));
    }

    @Override
    public ResourceLocation getTextureLocation(CloneSummonEntity pEntity) {
        if(pEntity.getPlayerCloned() != null){
            return Minecraft.getInstance().player.getSkinTextureLocation();
        }
        return Minecraft.getInstance().player.getSkinTextureLocation();
    }

}
