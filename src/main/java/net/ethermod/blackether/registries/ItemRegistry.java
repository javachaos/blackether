package net.ethermod.blackether.registries;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import static net.ethermod.blackether.BlackEtherMod.MODID;

public class ItemRegistry {

    public static final ArmorMaterial ONYX_ARMOR_MATERIAL = new CustomArmorMaterial();

    public static final SpawnEggItem ONYX_SNAKE_EGG = registerItem("onyx_snake_egg", new SpawnEggItem(EntityRegistry.ONYX_SNAKE, 0x1F1F1F, 0x0D0D0D, new Item.Properties()));

    public static final Item ONYX_APPLE = registerItem("onyx_apple", new OnyxApple());
    public static final Item ONYX_ORE = registerItem("onyx_ore", new Item(new FabricItemSettings()));
    public static final Item NEUTRONIUM = registerItem("neutronium", new Item(new FabricItemSettings()));
    public static final Item ONYX_HELMET = registerItem("onyx_helmet",
            new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Properties())));
    public static final Item ONYX_CHESTPLATE = registerItem("onyx_chestplate",
            new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Properties())));
    public static final Item ONYX_LEGGINGS = registerItem("onyx_leggings",
            new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Properties())));
    public static final Item ONYX_BOOTS = registerItem("onyx_boots",
            new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Properties())));
    public static final Item ETHER_ORE = registerItem("ether_ore", new Item(new FabricItemSettings()));
    public static final Item ONYX_DUST = registerItem("onyx_dust", new Item(new FabricItemSettings()));
    public static final Item ONYX_PICKAXE = registerItem("onyx_pickaxe", new OnyxPickaxe());
    public static final Item ONYX_SHOVEL = registerItem("onyx_shovel", new OnyxShovel());
    public static final Item ONYX_AXE = registerItem("onyx_axe", new OnyxAxe());
    public static final Item ONYX_HOE = registerItem("onyx_hoe", new OnyxHoe());
    public static final Item ONYX_SWORD = registerItem("onyx_sword", new OnyxSword());

    public static final CreativeModeTab BLACKETHERMOD_GROUP = FabricItemGroup.builder(
                    new ResourceLocation(BlackEtherMod.MODID, "ethermod_group"))
            .icon(() -> new ItemStack(ONYX_APPLE))
            .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                entries.accept(BlockRegistry.BLOCK_OF_ETHER);
                entries.accept(BlockRegistry.ETHER_ORE_BLOCK);
                entries.accept(BlockRegistry.DARK_GRASS);
                entries.accept(ONYX_HELMET);
                entries.accept(ONYX_CHESTPLATE);
                entries.accept(ONYX_LEGGINGS);
                entries.accept(ONYX_BOOTS);
                entries.accept(ONYX_DUST);
                entries.accept(ONYX_PICKAXE);
                entries.accept(ONYX_SHOVEL);
                entries.accept(ONYX_AXE);
                entries.accept(ONYX_HOE);
                entries.accept(ONYX_SWORD);
                entries.accept(NEUTRONIUM);
                entries.accept(ONYX_SNAKE_EGG);
                entries.accept(BlockRegistry.NEUTRON_BOMB);
            })
            .build();

    public static <I extends Item> I registerItem(String name, I item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, name), item);
    }
}
