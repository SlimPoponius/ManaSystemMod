package net.slimpopo.godsend.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;

public class ModContainerEntity {
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
            GodSend.MOD_ID);

    public static final RegistryObject<MenuType<SpellLearnerContainer>> SPELLLEARN_CONTAINER = CONTAINERS.register("spell_learner",
            () -> new MenuType<>(SpellLearnerContainer::new));

    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
