package net.ethermod.blackether.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static net.ethermod.blackether.BlackEtherMod.MODID;

public final class SoundRegistry extends BaseRegistry {

    private static SoundRegistry INSTANCE;

    private SoundRegistry() {
    }

    public static SoundRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SoundRegistry();
        }
        return INSTANCE;
    }

    @Override
    public SoundEvent getSoundEvent(final String name) {
        return super.getSoundEvent(name);
    }

    @Override
    public void register() {
        SoundEvent NEUTRON_EVENT = Registry.register(BuiltInRegistries.SOUND_EVENT, "neutron_ionizing",
                SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "neutron_ionizing")));
        putSoundEvent("neutron_ionizing", NEUTRON_EVENT);
    }

}
