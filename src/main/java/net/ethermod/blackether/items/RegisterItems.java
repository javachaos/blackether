package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterItems {
    public static final Logger LOGGER = LogManager.getLogger(RegisterItems.class);

    public static final ArmorMaterial ONYX_ARMOR_MATERIAL = new CustomArmorMaterial();
    public static final Item CUSTOM_MATERIAL = new CustomMaterialItem(new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP));
    public static final Item ONYX_HELMET = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP)));
    public static final Item ONYX_CHESTPLATE = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP)));
    public static final Item ONYX_LEGGINGS = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP)));
    public static final Item ONYX_BOOTS = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP)));
    public static final Item ETHER_ORE = new Item(new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP));
    public static final Item ONYX_DUST = new Item(new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP));
    public static final Item ONYX_PICKAXE = new OnyxPickaxe();
    public static final Item ONYX_SHOVEL = new OnyxShovel();
    public static final Item ONYX_AXE = new OnyxAxe();
    public static final Item ONYX_HOE = new OnyxHoe();
    public static final Item ONYX_SWORD = new OnyxSword();
    public static final Block BLOCK_OF_ETHER = new BlockOfEther(FabricBlockSettings.of(Material.METAL, MaterialColor.BLACK).strength(10.0F, 6.0F));
    public static final Item ONYX_APPLE = new OnyxApple();


    public static void register() {
        LOGGER.info("Registering items and blocks for Black Ether Mod");
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_apple"), ONYX_APPLE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_pickaxe"), ONYX_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_axe"), ONYX_AXE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_hoe"), ONYX_HOE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_sword"), ONYX_SWORD);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_shovel"), ONYX_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "ether_ore"), ETHER_ORE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_ore"), BlackEtherMod.ONYX_ORE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_dust"), ONYX_DUST);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "ether_ore_block"), new BlockItem(BlackEtherMod.ETHER_ORE_BLOCK, new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "block_of_ether"), new BlockItem(BLOCK_OF_ETHER, new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_helmet"), ONYX_HELMET);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_chestplate"), ONYX_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_leggings"), ONYX_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_boots"), ONYX_BOOTS);
        //BLOCKS
        Registry.register(Registry.BLOCK, new Identifier(BlackEtherMod.MODID, "ether_ore_block"), BlackEtherMod.ETHER_ORE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(BlackEtherMod.MODID, "block_of_ether"), BLOCK_OF_ETHER);
        FuelRegistryImpl.INSTANCE.add(ETHER_ORE, 3000);
    }

}
