package net.ethermod.blackether;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.EthermodModelProvider;
import net.ethermod.blackether.data.EthermodRecipeGenerator;
import net.ethermod.blackether.data.EthermodWorldGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BlackEtherModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EthermodRecipeGenerator::new);
        pack.addProvider(EthermodModelProvider::new);
        pack.addProvider(EthermodLootTableGenerator::new);
        pack.addProvider(EthermodWorldGenerator::new);
    }
}
