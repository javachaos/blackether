package net.ethermod.blackether.registries;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.concurrent.atomic.AtomicReference;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class ItemRegistry extends BaseRegistry {
    private static final AtomicReference<ItemRegistry> INSTANCE = new AtomicReference<>();

    private ItemRegistry() {
    }

    public static ItemRegistry getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new ItemRegistry());
        }
        return INSTANCE.get();
    }

    //public final

    public <I extends Item> void registerItem(String name, I item) {
        putItem(name, item);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, name), item);
    }

    @Override
    public Item getItem(final String name) {
        return super.getItem(name);
    }

    @Override
    public void register() {
        ArmorMaterial onyxArmorMaterial = new CustomArmorMaterial();
        registerItem(Naming.ONYX_SNAKE_EGG,
                new SpawnEggItem(EntityRegistry.getInstance()
                        .getEntityType(Naming.ONYX_SNAKE, OnyxSnakeEntity.class), 0x1F1F1F, 0x0D0D0D,
                        new Item.Properties()));
        registerItem(Naming.ONYX_FROG_EGG,
                new SpawnEggItem(EntityRegistry.getInstance()
                        .getEntityType(Naming.ONYX_FROG, OnyxFrogEntity.class), 0x1E1E1E, 0x0C0C0C,
                        new Item.Properties()));
        registerItem(Naming.ONYX_APPLE, new OnyxApple());
        registerItem(Naming.ONYX_ORE, new Item(new FabricItemSettings()));
        registerItem(Naming.NEUTRONIUM, new Item(new FabricItemSettings()));
        registerItem(Naming.ONYX_HELMET,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.HELMET, (new Item.Properties())));
        registerItem(Naming.ONYX_CHESTPLATE,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
        registerItem(Naming.ONYX_LEGGINGS,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
        registerItem(Naming.ONYX_BOOTS,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.BOOTS, (new Item.Properties())));
        registerItem(Naming.ETHER_ORE, new Item(new FabricItemSettings()));
        registerItem(Naming.ETHER_INGOT, new EtherIngot(new FabricItemSettings()));
        registerItem(Naming.ONYX_DUST, new Item(new FabricItemSettings()));
        registerItem(Naming.ONYX_PICKAXE, new OnyxPickaxe());
        registerItem(Naming.ONYX_SHOVEL, new OnyxShovel());
        registerItem(Naming.ONYX_AXE, new OnyxAxe());
        registerItem(Naming.ONYX_HOE, new OnyxHoe());
        registerItem(Naming.ONYX_SWORD, new OnyxSword());
        FabricItemGroup.builder(
                        new ResourceLocation(BlackEtherMod.MOD_ID, Naming.ETHERMOD_ITEMGROUP))
                .icon(() -> new ItemStack(ItemRegistry.getInstance().getItem(Naming.ONYX_APPLE)))
                .displayItems((displayParameters, x) -> {
                    BlockRegistry.getInstance().getBlocks().forEach((b, v) -> x.accept(v));
                    ItemRegistry.getInstance().getItems().forEach((i, v) -> x.accept(v));
                })
                .build();
    }

    public void generateTranslation(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_APPLE), "Onyx Apple");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_HELMET), "Onyx Helmet");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_CHESTPLATE), "Onyx Chestplate");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_LEGGINGS), "Onyx Leggings");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_BOOTS), "Onyx Boots");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_DUST), "Onyx Dust");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_PICKAXE), "Onyx Pickaxe");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_SHOVEL), "Onyx Shovel");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_AXE), "Onyx Axe");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_HOE), "Onyx Hoe");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_SWORD), "Onyx Sword");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.NEUTRONIUM), "Neutronium");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_SNAKE_EGG), "Onyx Snake Egg");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_FROG_EGG), "Onyx Frog Egg");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ONYX_ORE), "Onyx Ore");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ETHER_ORE), "Ether Ore");
        translationBuilder.add(ItemRegistry.getInstance().getItem(Naming.ETHER_INGOT), "Ether Ingot");
    }
}
