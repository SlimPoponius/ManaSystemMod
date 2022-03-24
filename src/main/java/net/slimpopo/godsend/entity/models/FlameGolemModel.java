package net.slimpopo.godsend.entity.models;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.IronGolem;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;

public class FlameGolemModel<T extends FlameGolemEntity> extends IronGolemModel<IronGolem> {

    public FlameGolemModel(ModelPart pRoot) {
        super(pRoot);
    }
}
