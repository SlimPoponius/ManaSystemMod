package net.slimpopo.godsend.item.custom.spell.thunder.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
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
import net.slimpopo.godsend.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ThunderArmorItem extends ArmorItem {

    private static int armorCounter = 1800;
    private static int lightningChance = 60;
    Logger Log = LogManager.getLogger();

    public ThunderArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        BlockPos blockPos = player.blockPosition();
        BlockState blockState = world.getBlockState(blockPos);
        Block below = blockState.getBlock();
        Random rand = new Random();
        float chance = rand.nextFloat();

        if(!world.isClientSide){
            if(hasFullSuitOFArmorOn(player)){
                evaluateArmorEffects(player);
                armorCounter--;
                lightningChance--;
                if(armorCounter <= 0){
                    destroyArmor(player);

                }

            }
            else{
                DestroyExcessPieces(player);
            }

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, armorCounter,6));
            if(world.isRaining())
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, armorCounter,6));



            if(lightningChance <0) {
                if (world.isRaining()) {


                    if (chance > 0.75) {
                        ServerLevel sWorld = (ServerLevel) world;
                        EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.north(2),
                                MobSpawnType.TRIGGERED, true, true);
                        EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.south(2),
                                MobSpawnType.TRIGGERED, true, true);
                        EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.east(2),
                                MobSpawnType.TRIGGERED, true, true);
                        EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.west(2),
                                MobSpawnType.TRIGGERED, true, true);
                    }
                } else {
                    if (chance > 0.75) {
                        int posChoice = rand.nextInt() % 4 + 1;
                        ServerLevel sWorld = (ServerLevel) world;

                        if (posChoice == 1)
                            EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.north(2),
                                    MobSpawnType.TRIGGERED, true, true);
                        else if (posChoice == 2)
                            EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.south(2),
                                    MobSpawnType.TRIGGERED, true, true);
                        else if (posChoice == 3)
                            EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.east(2),
                                    MobSpawnType.TRIGGERED, true, true);
                        else
                            EntityType.LIGHTNING_BOLT.spawn(sWorld, null, player, blockPos.west(2),
                                    MobSpawnType.TRIGGERED, true, true);
                    }
                }
                lightningChance = 60;
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
            if(item.getItem() == ModItems.THUNDER_BOOT.get() || item.getItem() == ModItems.THUNDER_CHEST.get() ||
                    item.getItem() == ModItems.THUNDER_HELMET.get() || item.getItem() == ModItems.THUNDER_LEG.get() ){
                item.setCount(0);
            }
        }
        if(player.getMainHandItem().getItem() == ModItems.THUNDER_BOOT.get() || player.getMainHandItem().getItem() == ModItems.ICE_CHEST.get() ||
                player.getMainHandItem().getItem() == ModItems.THUNDER_HELMET.get() || player.getMainHandItem().getItem() == ModItems.ICE_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }
        if(player.getOffhandItem().getItem() == ModItems.THUNDER_BOOT.get() || player.getOffhandItem().getItem() == ModItems.THUNDER_CHEST.get() ||
                player.getOffhandItem().getItem() == ModItems.THUNDER_HELMET.get() ||player.getOffhandItem().getItem() == ModItems.THUNDER_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }

        player.removeEffect(MobEffects.MOVEMENT_SPEED);
        player.removeEffect(MobEffects.INVISIBILITY);
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
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        DestroyExcessPieces(player);
        return super.onDroppedByPlayer(item, player);
    }


}
