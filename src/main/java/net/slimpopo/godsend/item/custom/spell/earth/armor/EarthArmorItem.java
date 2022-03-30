package net.slimpopo.godsend.item.custom.spell.earth.armor;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.slimpopo.godsend.effects.ModEffects;
import net.slimpopo.godsend.item.ModArmorMaterials;
import net.slimpopo.godsend.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class EarthArmorItem extends ArmorItem {

    private static int armorCounter = 1800;
    private static int jumpTime = 0;
    private static double prevY = 0;

    Logger Log = LogManager.getLogger();

//    private static final Map<ArmorMaterial, MobEffectInstance> MAT_TO_EFFECT_MAP =
//            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
//                    .put(ModArmorMaterials.EARTH,
//                            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, armorCounter,1))
//                    .put(ModArmorMaterials.EARTH,
//                            new MobEffectInstance(MobEffects.REGENERATION, armorCounter/3,1)).build();

    public EarthArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
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

                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, armorCounter,1));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, armorCounter/3,1));

            }
            else{
                DestroyExcessPieces(player);
            }

            if(prevY != player.getY()){
                if(prevY == 0){
                    prevY = player.getY();
                }
                //player.sendMessage(new TextComponent("Previous Y: "+prevY + "current Y: " + player.getY()),player.getUUID());
                if(player.getY() < prevY){
                    jumpTime++;
                }
                prevY = player.getY();
            }

            if(jumpTime > 0){
                quake(player,jumpTime);
                jumpTime = 0;
                prevY = 0;
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

        }
    }

    private void DestroyExcessPieces(Player player){
        for(ItemStack item : player.getInventory().items){
            if(item.getItem() == ModItems.EARTH_BOOT.get() || item.getItem() == ModItems.EARTH_CHEST.get() ||
                    item.getItem() == ModItems.EARTH_HELMET.get() || item.getItem() == ModItems.EARTH_LEG.get() ){
                item.setCount(0);
            }
        }
        if(player.getMainHandItem().getItem() == ModItems.EARTH_BOOT.get() || player.getMainHandItem().getItem() == ModItems.EARTH_CHEST.get() ||
                player.getMainHandItem().getItem() == ModItems.EARTH_HELMET.get() || player.getMainHandItem().getItem() == ModItems.EARTH_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }
        if(player.getOffhandItem().getItem() == ModItems.EARTH_BOOT.get() || player.getOffhandItem().getItem() == ModItems.EARTH_CHEST.get() ||
                player.getOffhandItem().getItem() == ModItems.EARTH_HELMET.get() ||player.getOffhandItem().getItem() == ModItems.EARTH_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }

        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        player.removeEffect(MobEffects.REGENERATION);
    }


    private boolean hasFullSuitOFArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);
        boots.enchant(Enchantments.FALL_PROTECTION,10);

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
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        DestroyExcessPieces(player);
        return super.onDroppedByPlayer(item, player);
    }

    private void quake(Player player, int power){
        BlockPos block = player.blockPosition().below();
        Level level = player.level;

        Random random = new Random();

        for (int i = -3; i <= 3; i++) {
            for (int j = -7; j <= 7; j++) {
                BlockPos newSpot = block.offset(i, 0, j);
                BlockState thisState = level.getBlockState(newSpot);
                if (!thisState.isAir()) {

                    AABB bounds = AABB.of(new BoundingBox(newSpot.above()));
                    List<Entity> entities = level.getEntities(null, bounds);
                    if (!entities.isEmpty()) {
                        entities.forEach(le -> {
                            if (le instanceof LivingEntity living) {
                                if (living != player) {
                                    living.setDeltaMovement(0, (5 * power),0);
                                    //pPlayer.sendMessage(new TextComponent(le.getName().toString()), pPlayer.getUUID());
                                }
                            }
                        });
                    }
                }

            }
        }
    }

}
