package net.slimpopo.godsend.capability.spellbook;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpellBookProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<SpellBookCapability> SPELLBOOK_CAP = CapabilityManager.get(new CapabilityToken<>(){});

    private SpellBookCapability spellBook = null;
    private final LazyOptional<SpellBookCapability> opt = LazyOptional.of(this::createPlayerSpellBook);

    @NotNull
    private SpellBookCapability createPlayerSpellBook() {
        if(spellBook == null){
            spellBook = new SpellBookCapability();
        }
        return spellBook;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == SPELLBOOK_CAP){
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSpellBook().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSpellBook().loadNBTData(nbt);
    }
}
