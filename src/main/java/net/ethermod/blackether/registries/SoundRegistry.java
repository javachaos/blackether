package net.ethermod.blackether.registries;

import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

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
        SoundEvent NEUTRON_EVENT = Registry.register(BuiltInRegistries.SOUND_EVENT, Naming.NEUTRON_IONIZING,
                SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, Naming.NEUTRON_IONIZING)));
        putSoundEvent(Naming.NEUTRON_IONIZING, NEUTRON_EVENT);
    }

}
