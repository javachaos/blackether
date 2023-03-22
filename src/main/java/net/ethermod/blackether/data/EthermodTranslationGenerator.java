package net.ethermod.blackether.data;

import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class EthermodTranslationGenerator extends FabricLanguageProvider {
    public EthermodTranslationGenerator(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("biome.ethermod.onyx_biome", "Onyx Biome");
        translationBuilder.add("biome.ethermod.chiseled_ether", "Onyx Biome");
        BlockRegistry.getInstance().generateTranslation(translationBuilder);
        EntityRegistry.getInstance().generateTranslation(translationBuilder);
        ItemRegistry.getInstance().generateTranslation(translationBuilder);
        translationBuilder.add("itemGroup.ethermod.ethermod_group", "Ether Mod");
    }
}
