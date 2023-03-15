package net.ethermod.blackether.registries;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.entity.living.OnyxSnakeEntity;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
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
        registerItem(Naming.ONYX_APPLE, new OnyxApple());
        registerItem(Naming.ONYX_ORE, new Item(new FabricItemSettings()));
        registerItem(Naming.NEUTRONIUM, new Item(new FabricItemSettings()));
        registerItem(Naming.ONYX_HELMET,
                new ArmorItem(onyxArmorMaterial, EquipmentSlot.HEAD, (new Item.Properties())));
        registerItem(Naming.ONYX_CHESTPLATE,
                new ArmorItem(onyxArmorMaterial, EquipmentSlot.CHEST, (new Item.Properties())));
       registerItem(Naming.ONYX_LEGGINGS,
                new ArmorItem(onyxArmorMaterial, EquipmentSlot.LEGS, (new Item.Properties())));
        registerItem(Naming.ONYX_BOOTS,
                new ArmorItem(onyxArmorMaterial, EquipmentSlot.FEET, (new Item.Properties())));
        registerItem(Naming.ETHER_ORE, new Item(new FabricItemSettings()));
        registerItem(Naming.ONYX_DUST, new Item(new FabricItemSettings()));
        registerItem(Naming.ONYX_PICKAXE, new OnyxPickaxe());
        registerItem(Naming.ONYX_SHOVEL, new OnyxShovel());
        registerItem(Naming.ONYX_AXE, new OnyxAxe());
        registerItem(Naming.ONYX_HOE, new OnyxHoe());
        registerItem(Naming.ONYX_SWORD, new OnyxSword());
        FabricItemGroup.builder(
                        new ResourceLocation(BlackEtherMod.MOD_ID, Naming.ETHERMOD_ITEMGROUP))
                .icon(() -> new ItemStack(ItemRegistry.getInstance().getItem(Naming.ONYX_APPLE)))
                .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                    entries.accept(BlockRegistry.getInstance().getBlock(Naming.BLOCK_OF_ETHER));
                    entries.accept(BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK));
                    entries.accept(BlockRegistry.getInstance().getBlock(Naming.DARK_GRASS));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_HELMET));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_CHESTPLATE));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_LEGGINGS));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_BOOTS));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_DUST));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_PICKAXE));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_SHOVEL));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_AXE));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_HOE));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_SWORD));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.NEUTRONIUM));
                    entries.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_SNAKE_EGG));
                    entries.accept(BlockRegistry.getInstance().getBlock(Naming.NEUTRON_BOMB));
                })
                .build();
    }
}
