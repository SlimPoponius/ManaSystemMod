package net.slimpopo.godsend.entity.models;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.player.Player;
import net.slimpopo.godsend.entity.mobs.CloneSummonEntity;

public class CloneSummonModel<T extends CloneSummonEntity> extends PlayerModel<Player> {
    public CloneSummonModel(ModelPart pRoot, boolean pSlim) {
        super(pRoot, pSlim);
    }
}
