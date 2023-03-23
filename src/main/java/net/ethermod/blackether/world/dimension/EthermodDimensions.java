package net.ethermod.blackether.world.dimension;

import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.registries.Registerable;
import net.ethermod.blackether.utils.Naming;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.concurrent.atomic.AtomicReference;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EthermodDimensions extends Registerable {
    private static final AtomicReference<EthermodDimensions> INSTANCE = new AtomicReference<>();

    private EthermodDimensions() {
    }

    public static EthermodDimensions getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new EthermodDimensions());
        }
        return INSTANCE.get();
    }

    public static final ResourceKey<Level> ONYX_DIM = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(MOD_ID, Naming.ONYX_DIMENSION));
    private static final ResourceKey<DimensionType> ONYX_DIM_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            ONYX_DIM.location());

    @Override
    public void register() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(BlockRegistry.getInstance().getBlock(Naming.CHISELED_ETHER))
                .lightWithItem(ItemRegistry.getInstance().getItem(Naming.ONYX_DUST))
                .destDimID(new ResourceLocation(MOD_ID, Naming.ONYX_DIMENSION))
                .tintColor(20, 20, 20)
                .registerPortal();
    }

}
