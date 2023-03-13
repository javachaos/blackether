package net.ethermod.blackether.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static net.ethermod.blackether.BlackEtherMod.MODID;

public final class SoundRegistry {

    public static final SoundEvent NEUTRON_EVENT = Registry.register(BuiltInRegistries.SOUND_EVENT, "neutron_ionizing",
            SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "neutron_ionizing")));

}
