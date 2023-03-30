package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherLootTableGenerator;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class OnyxFortLootTableGenerator extends EtherLootTableGenerator {

    public OnyxFortLootTableGenerator(final EthermodLootTableGenerator ltg) {
        super(ltg);
        //TODO add stuff to this.
        setBuilder(LootTable.lootTable());
    }

    @Override
    public void generate() {
        lootTableGenerator.generate();
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer) {
        resourceLocationBuilderBiConsumer.accept(new ResourceLocation(MOD_ID,
                Naming.CHEST_LOOT_TABLE_FOLDER + "onyx_loot_table"), builder);
    }
}
