package net.slimpopo.godsend.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, GodSend.MOD_ID);

    public static final RegistryObject<Enchantment> FLAMEIMBUE =
            ENCHANTMENTS.register("flameimbue",() -> new EnchantmentFireImbue(Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> ICEIMBUE =
            ENCHANTMENTS.register("iceimbue",() -> new EnchantmentIceImbue(Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> THUNDERIMBUE =
            ENCHANTMENTS.register("thunderimbue",() -> new EnchantmentThunderImbue(Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> SHADOWIMBUE =
            ENCHANTMENTS.register("shadowimbue",() -> new EnchantmentShadowImbue(Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> LIGHTIMBUE =
            ENCHANTMENTS.register("lightimbue",() -> new EnchantmentLightImbue(Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> LIFESTEAL =
            ENCHANTMENTS.register("lifesteal",() -> new EnchantmentLifeSteal(Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus){
        ENCHANTMENTS.register(eventBus);
    }

}
