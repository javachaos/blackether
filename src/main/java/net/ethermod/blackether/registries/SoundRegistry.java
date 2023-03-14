package net.ethermod.blackether.registries;

import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.concurrent.atomic.AtomicReference;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public final class SoundRegistry extends BaseRegistry {

    private static final AtomicReference<SoundRegistry> INSTANCE = new AtomicReference<>();

    private SoundRegistry() {
    }

    public static SoundRegistry getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new SoundRegistry());
        }
        return INSTANCE.get();
    }

    @Override
    public SoundEvent getSoundEvent(final String name) {
        return super.getSoundEvent(name);
    }

    @Override
    public void register() {
        SoundEvent neutronEvent = Registry.register(BuiltInRegistries.SOUND_EVENT, Naming.NEUTRON_IONIZING,
                SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, Naming.NEUTRON_IONIZING)));
        putSoundEvent(Naming.NEUTRON_IONIZING, neutronEvent);
    }

}
