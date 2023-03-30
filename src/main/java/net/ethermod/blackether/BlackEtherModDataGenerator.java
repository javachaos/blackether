package net.ethermod.blackether;


import net.ethermod.blackether.data.*;
import net.ethermod.blackether.data.world.EthermodBiomes;
import net.ethermod.blackether.data.world.EthermodCarvers;
import net.ethermod.blackether.data.world.EthermodConfiguredFeatures;
import net.ethermod.blackether.data.world.EthermodPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class BlackEtherModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EthermodRecipeGenerator::new);
        pack.addProvider(EthermodModelProvider::new);
        pack.addProvider(EthermodLootTableGenerator::new);
        pack.addProvider(EthermodWorldGenerator::new);
        pack.addProvider(EthermodTranslationGenerator::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_CARVER, EthermodCarvers::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, EthermodConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, EthermodPlacedFeatures::bootstrap);
        registryBuilder.add(Registries.BIOME, EthermodBiomes::bootstrap);
    }
}
