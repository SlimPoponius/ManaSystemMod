package net.slimpopo.godsend.entity.models;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;
import net.slimpopo.godsend.entity.mobs.ZombieSummonEntity;

public class ZombieSummonModel <T extends ZombieSummonEntity> extends ZombieModel<Zombie> {
    public ZombieSummonModel(ModelPart model) {
        super(model);
    }
}
