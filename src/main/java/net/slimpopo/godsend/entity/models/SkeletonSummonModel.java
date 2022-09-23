package net.slimpopo.godsend.entity.models;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.slimpopo.godsend.entity.mobs.SkeletonSummonEntity;
import net.slimpopo.godsend.entity.mobs.ZombieSummonEntity;

public class SkeletonSummonModel<T extends SkeletonSummonEntity> extends SkeletonModel<Skeleton> {
    public SkeletonSummonModel(ModelPart model) {
        super(model);
    }
}
