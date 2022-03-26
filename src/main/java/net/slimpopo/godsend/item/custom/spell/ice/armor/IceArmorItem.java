package net.slimpopo.godsend.item.custom.spell.ice.armor;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.item.ModArmorMaterials;
import net.slimpopo.godsend.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class IceArmorItem extends ArmorItem {

    private static int armorCounter = 1800;
    Logger Log = LogManager.getLogger();

    public IceArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        BlockPos blockPos = player.blockPosition();
        BlockState blockState = world.getBlockState(blockPos);
        Block below = blockState.getBlock();

        if(!world.isClientSide){
            if(hasFullSuitOFArmorOn(player)){
                evaluateArmorEffects(player);
                armorCounter--;
                if(armorCounter <= 0){
                    destroyArmor(player);

                }

                //System.out.println(BaseFireBlock.getState(world, blockPos));
                BlockState blockstate1 = BaseFireBlock.getState(world, blockPos);
                world.setBlock(blockPos.above().south(), blockstate1, 11);

                //world.getBlockState(blockPos).is(Blocks.FIRE);
            }
            else{
                DestroyExcessPieces(player);
            }

            if(player.isInWater()){
                world.setBlock(blockPos, Blocks.ICE.defaultBlockState(),4);
            }

        }
    }

    private void destroyArmor(Player player) {
        player.getInventory().armor.clear();
    }

    private void evaluateArmorEffects(Player player) {

    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterials, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect((mapStatusEffect.getEffect()));
        if(hasCorrectArmorON(mapArmorMaterials,player) && !hasPlayerEffect){
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(),mapStatusEffect.getAmplifier()));
        }
    }

    private void DestroyExcessPieces(Player player){
        for(ItemStack item : player.getInventory().items){
            if(item.getItem() == ModItems.ICE_BOOT.get() || item.getItem() == ModItems.ICE_CHEST.get() ||
                    item.getItem() == ModItems.ICE_HELMET.get() || item.getItem() == ModItems.ICE_LEG.get() ){
                item.setCount(0);
            }
        }
        if(player.getMainHandItem().getItem() == ModItems.ICE_BOOT.get() || player.getMainHandItem().getItem() == ModItems.ICE_CHEST.get() ||
                player.getMainHandItem().getItem() == ModItems.ICE_HELMET.get() || player.getMainHandItem().getItem() == ModItems.ICE_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }
        if(player.getOffhandItem().getItem() == ModItems.ICE_BOOT.get() || player.getOffhandItem().getItem() == ModItems.ICE_CHEST.get() ||
                player.getOffhandItem().getItem() == ModItems.ICE_HELMET.get() ||player.getOffhandItem().getItem() == ModItems.ICE_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }
    }


    private boolean hasFullSuitOFArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorON(ArmorMaterial material, Player player){
        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && chestplate.getMaterial() == material
                && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Player player = Minecraft.getInstance().player;

        pTarget.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(),100,3));


        return super.hurtEnemy(pStack,pTarget,pAttacker);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        DestroyExcessPieces(player);
        return super.onDroppedByPlayer(item, player);
    }


}
