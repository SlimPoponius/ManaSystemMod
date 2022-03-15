package net.slimpopo.godsend.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slimpopo.godsend.GodSend;
import net.slimpopo.godsend.block.ModBlocks;
import net.slimpopo.godsend.entity.block.BaseBlockEntity;
import net.slimpopo.godsend.entity.block.SpellLearnerEntity;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, GodSend.MOD_ID);

    public static final RegistryObject<BlockEntityType<SpellLearnerEntity>> SPELL_LEARNER =
            BLOCK_ENTITIES.register("spell_learner",
                    ()-> BlockEntityType.Builder.of(SpellLearnerEntity::new, ModBlocks.SPELLLEARNER.get())
                            .build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
