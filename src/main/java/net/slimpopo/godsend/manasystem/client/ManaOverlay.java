package net.slimpopo.godsend.manasystem.client;

import net.minecraftforge.client.gui.IIngameOverlay;
import net.slimpopo.godsend.capability.mana.ManaConfig;

public class ManaOverlay {

    public static final IIngameOverlay HUD_MANA = (gui, poseStack, partialTicks, width, height) -> {
        String toDisplay = "Level: "+ClientManaData.getManaLvl() ;
        String manaDisplay = ClientManaData.getMana() + " / " + ClientManaData.getManaMax();
        gui.getFont().draw(poseStack, toDisplay, 10, 10, ManaConfig.MANA_HUD_COLOR.get());
        gui.getFont().draw(poseStack, manaDisplay, 10, 20, ManaConfig.MANA_HUD_COLOR.get());


    };
}
