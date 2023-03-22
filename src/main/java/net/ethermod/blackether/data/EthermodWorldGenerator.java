package net.ethermod.blackether.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EthermodWorldGenerator extends FabricDynamicRegistryProvider {
    public EthermodWorldGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        //TODO figure more out here.
        // Start with getting Generated structures working dynamically
        // and then add spawn.onyxforts properties back
        // to properties file using structure variants ect.
    }

    @Override
    public String getName() {
        return MOD_ID;
    }
}
