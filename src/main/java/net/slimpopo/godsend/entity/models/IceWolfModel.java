package net.slimpopo.godsend.entity.models;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Wolf;
import net.slimpopo.godsend.entity.mobs.FlameGolemEntity;
import net.slimpopo.godsend.entity.mobs.IceWolfEntity;

public class IceWolfModel<T extends IceWolfEntity> extends WolfModel<Wolf> {

    public IceWolfModel(ModelPart pRoot) {
        super(pRoot);
    }
}
