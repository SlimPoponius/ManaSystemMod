package net.slimpopo.godsend.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.slimpopo.godsend.item.ModArmorMaterials;
import net.slimpopo.godsend.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class FlameArmorItem  extends ArmorItem {

    private static int armorCounter = 1800;
    Logger Log = LogManager.getLogger();

    private static final Map<ArmorMaterial, MobEffectInstance> MAT_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.FLAME,
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, armorCounter,1)).build();

    public FlameArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        BlockPos blockPos = player.blockPosition().below();
        BlockState blockState = world.getBlockState(blockPos);
        Block below = blockState.getBlock();

        if(!world.isClientSide){
            if(hasFullSuitOFArmorOn(player)){
                evaluateArmorEffects(player);
                armorCounter--;
                if(armorCounter <= 0){
                    destroyArmor(player);
                    player.removeEffect(MobEffects.FIRE_RESISTANCE);

                }

                //System.out.println(BaseFireBlock.getState(world, blockPos));
                BlockState blockstate1 = BaseFireBlock.getState(world, blockPos);
                world.setBlock(blockPos.above().south(), blockstate1, 11);

                //world.getBlockState(blockPos).is(Blocks.FIRE);
            }
            else{
                DestroyExcessPieces(player);
            }

        }
    }

    private void destroyArmor(Player player) {
        player.getInventory().armor.clear();
    }

    private void evaluateArmorEffects(Player player) {
        for(Map.Entry<ArmorMaterial,MobEffectInstance> entry : MAT_TO_EFFECT_MAP.entrySet()){
            ArmorMaterial mapArmorMaterials = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorON(mapArmorMaterials, player)){
                addStatusEffectForMaterial(player,mapArmorMaterials,mapStatusEffect);
            }
        }
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
            if(item.getItem() == ModItems.FLAME_BOOT.get() || item.getItem() == ModItems.FLAME_CHEST.get() ||
                    item.getItem() == ModItems.FLAME_HELMET.get() || item.getItem() == ModItems.FLAME_LEG.get() ){
                item.setCount(0);
            }
        }
        if(player.getMainHandItem().getItem() == ModItems.FLAME_BOOT.get() || player.getMainHandItem().getItem() == ModItems.FLAME_CHEST.get() ||
                player.getMainHandItem().getItem() == ModItems.FLAME_HELMET.get() || player.getMainHandItem().getItem() == ModItems.FLAME_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }
        if(player.getOffhandItem().getItem() == ModItems.FLAME_BOOT.get() || player.getOffhandItem().getItem() == ModItems.FLAME_CHEST.get() ||
                player.getOffhandItem().getItem() == ModItems.FLAME_HELMET.get() ||player.getOffhandItem().getItem() == ModItems.FLAME_LEG.get() ){
            player.getMainHandItem().setCount(0);
        }
        player.removeEffect(MobEffects.FIRE_RESISTANCE);
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

        if(pAttacker != player)
            pAttacker.setSecondsOnFire(5);

        if(pTarget != player)
            pTarget.setSecondsOnFire(5);


        return super.hurtEnemy(pStack,pTarget,pAttacker);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item = ItemStack.EMPTY;
        DestroyExcessPieces(player);
        return super.onDroppedByPlayer(item, player);
    }


}
