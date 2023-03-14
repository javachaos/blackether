package net.ethermod.blackether.registries;

import net.ethermod.blackether.BlackEtherMod;
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

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class ItemRegistry extends BaseRegistry {
    private static ItemRegistry INSTANCE;

    private ItemRegistry() {
    }

    public static ItemRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemRegistry();
        }
        return INSTANCE;
    }

    //public final

    public <I extends Item> I registerItem(String name, I item) {
        putItem(name, item);
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, name), item);
    }

    @Override
    public Item getItem(final String name) {
        return super.getItem(name);
    }

    @Override
    public void register() {
        ArmorMaterial ONYX_ARMOR_MATERIAL = new CustomArmorMaterial();
        Item ONYX_SNAKE_EGG = registerItem(Naming.ONYX_SNAKE_EGG,
                new SpawnEggItem(EntityRegistry.ONYX_SNAKE, 0x1F1F1F, 0x0D0D0D,
                        new Item.Properties()));
        Item ONYX_APPLE = registerItem(Naming.ONYX_APPLE, new OnyxApple());
        Item ONYX_ORE = registerItem(Naming.ONYX_ORE, new Item(new FabricItemSettings()));
        Item NEUTRONIUM = registerItem(Naming.NEUTRONIUM, new Item(new FabricItemSettings()));
        Item ONYX_HELMET = registerItem(Naming.ONYX_HELMET,
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Properties())));
        Item ONYX_CHESTPLATE = registerItem(Naming.ONYX_CHESTPLATE,
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Properties())));
        Item ONYX_LEGGINGS = registerItem(Naming.ONYX_LEGGINGS,
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Properties())));
        Item ONYX_BOOTS = registerItem(Naming.ONYX_BOOTS,
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Properties())));
        Item ETHER_ORE = registerItem(Naming.ETHER_ORE, new Item(new FabricItemSettings()));
        Item ONYX_DUST = registerItem(Naming.ONYX_DUST, new Item(new FabricItemSettings()));
        Item ONYX_PICKAXE = registerItem(Naming.ONYX_PICKAXE, new OnyxPickaxe());
        Item ONYX_SHOVEL = registerItem(Naming.ONYX_SHOVEL, new OnyxShovel());
        Item ONYX_AXE = registerItem(Naming.ONYX_AXE, new OnyxAxe());
        Item ONYX_HOE = registerItem(Naming.ONYX_HOE, new OnyxHoe());
        Item ONYX_SWORD = registerItem(Naming.ONYX_SWORD, new OnyxSword());
        CreativeModeTab BLACKETHERMOD_GROUP = FabricItemGroup.builder(
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
