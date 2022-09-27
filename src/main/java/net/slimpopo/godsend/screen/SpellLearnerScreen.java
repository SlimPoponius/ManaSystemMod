package net.slimpopo.godsend.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.capability.spellbook.SpellBookManager;
import net.slimpopo.godsend.container.SpellLearnerContainer;
import net.slimpopo.godsend.entity.block.SpellLearnerEntity;
import net.slimpopo.godsend.item.ModItems;
import net.slimpopo.godsend.item.custom.SpellBookItem;
import net.slimpopo.godsend.util.InventoryUtil;

public class SpellLearnerScreen extends AbstractContainerScreen<SpellLearnerContainer> {

    private static final ResourceLocation  TEXTURE = new ResourceLocation(GodSend.MOD_ID,
            "textures/gui/spell_learner_gui.png");
    private static final TextComponent fireLabel = new TextComponent("FIRE");
    private static final TextComponent iceLabel = new TextComponent("ICE");
    private static final TextComponent earthLabel = new TextComponent("EARTH");
    private static final TextComponent windLabel = new TextComponent("WIND");
    private static final TextComponent thunderLabel = new TextComponent("THUNDER");


    public SpellLearnerScreen(SpellLearnerContainer pMenu, Inventory pPlayerInventory, Component title) {
        super(pMenu, pPlayerInventory, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 168;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new ExtendedButton(leftPos+130,topPos+20,40,16,new TextComponent("Load"),
                btn -> {
            Player player = Minecraft.getInstance().player;
            ItemStack slot1 = this.menu.slots.get(0).getItem() != ItemStack.EMPTY ? this.menu.slots.get(0).getItem() : ItemStack.EMPTY;
            ItemStack slot2 = this.menu.slots.get(1).getItem() != ItemStack.EMPTY ? this.menu.slots.get(1).getItem() : ItemStack.EMPTY;
            ItemStack slot3 = this.menu.slots.get(2).getItem() != ItemStack.EMPTY ? this.menu.slots.get(2).getItem() : ItemStack.EMPTY;

            int spellBookSlot = InventoryUtil.getFirstInventoryIndex(player, ModItems.SPELLBOOK.get());


            if(spellBookSlot > -1){
                if(slot1 != ItemStack.EMPTY || slot2 != ItemStack.EMPTY ||
                        slot3 != ItemStack.EMPTY){
                    ((SpellBookItem)player.getInventory().getItem(spellBookSlot).getItem())
                            .UpdateSpellList(player,slot1,slot2,slot3);
                    player.sendMessage(new TextComponent("You have spells loaded"),player.getUUID());
                    this.menu.updateSlotContainers();
                    this.menu.resetSlotContainers();

                }
                else{
                    player.sendMessage(new TextComponent("You don't have any spells loaded"),player.getUUID());
                }
            }
            else{
                player.sendMessage(new TextComponent("You have to have a spellBook in your inventory"),player.getUUID());
            }
            this.menu.broadcastChanges();
            //whatever you want
        }));
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        blit(pPoseStack,this.leftPos,this.topPos,0,0,this.imageWidth,this.imageHeight);

    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.font.draw(pPoseStack,title,this.leftPos + 93 ,this.topPos + 5,0x404040);
//        this.font.draw(pPoseStack,fireLabel,this.leftPos + 137,this.topPos + 47,0xff0000);
//        this.font.draw(pPoseStack,iceLabel,this.leftPos + 17 ,this.topPos + 67,0x87ceeb);
//        this.font.draw(pPoseStack,earthLabel,this.leftPos + 127,this.topPos + 87,0x55ff55);
//        this.font.draw(pPoseStack,windLabel,this.leftPos + 17 ,this.topPos + 107,0x90ee90);
//        this.font.draw(pPoseStack,thunderLabel,this.leftPos + 117,this.topPos + 127,0xffff00);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        //drawString(pPoseStack,font,playerInventoryTitle,this.leftPos + 8,this.topPos + 3,0x404040);
    }


}
