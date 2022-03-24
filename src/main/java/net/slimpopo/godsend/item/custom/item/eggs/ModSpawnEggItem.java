package net.slimpopo.godsend.item.custom.item.eggs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class ModSpawnEggItem extends SpawnEggItem {

    public ModSpawnEggItem(EntityType<? extends Mob> pDefaultType, int pBackgroundColor, int pHighlightColor, Properties pProperties) {
        super(pDefaultType, pBackgroundColor, pHighlightColor, pProperties);
    }
}
