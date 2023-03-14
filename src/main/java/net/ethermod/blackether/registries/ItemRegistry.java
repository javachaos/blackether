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
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, name), item);
    }

    @Override
    public Item getItem(final String name) {
        return super.getItem(name);
    }

    @Override
    public void register() {
        ArmorMaterial ONYX_ARMOR_MATERIAL = new CustomArmorMaterial();
        Item ONYX_SNAKE_EGG = registerItem("onyx_snake_egg",
                new SpawnEggItem(EntityRegistry.ONYX_SNAKE, 0x1F1F1F, 0x0D0D0D,
                        new Item.Properties()));
        Item ONYX_APPLE = registerItem("onyx_apple", new OnyxApple());
        Item ONYX_ORE = registerItem("onyx_ore", new Item(new FabricItemSettings()));
        Item NEUTRONIUM = registerItem("neutronium", new Item(new FabricItemSettings()));
        Item ONYX_HELMET = registerItem("onyx_helmet",
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Properties())));
        Item ONYX_CHESTPLATE = registerItem("onyx_chestplate",
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Properties())));
        Item ONYX_LEGGINGS = registerItem("onyx_leggings",
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Properties())));
        Item ONYX_BOOTS = registerItem("onyx_boots",
                new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Properties())));
        Item ETHER_ORE = registerItem("ether_ore", new Item(new FabricItemSettings()));
        Item ONYX_DUST = registerItem("onyx_dust", new Item(new FabricItemSettings()));
        Item ONYX_PICKAXE = registerItem("onyx_pickaxe", new OnyxPickaxe());
        Item ONYX_SHOVEL = registerItem("onyx_shovel", new OnyxShovel());
        Item ONYX_AXE = registerItem("onyx_axe", new OnyxAxe());
        Item ONYX_HOE = registerItem("onyx_hoe", new OnyxHoe());
        Item ONYX_SWORD = registerItem("onyx_sword", new OnyxSword());
        CreativeModeTab BLACKETHERMOD_GROUP = FabricItemGroup.builder(
                        new ResourceLocation(BlackEtherMod.MODID, "ethermod_group"))
                .icon(() -> new ItemStack(ItemRegistry.getInstance().getItem("onyx_apple")))
                .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                    entries.accept(BlockRegistry.getInstance().getBlock("block_of_ether"));
                    entries.accept(BlockRegistry.getInstance().getBlock("ether_ore_block"));
                    entries.accept(BlockRegistry.getInstance().getBlock("dark_grass"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_helmet"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_chestplate"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_leggings"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_boots"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_dust"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_pickaxe"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_shovel"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_axe"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_hoe"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_sword"));
                    entries.accept(ItemRegistry.getInstance().getItem("neutronium"));
                    entries.accept(ItemRegistry.getInstance().getItem("onyx_snake_egg"));
                    entries.accept(BlockRegistry.getInstance().getBlock("neutron_bomb"));
                })
                .build();
    }
}
