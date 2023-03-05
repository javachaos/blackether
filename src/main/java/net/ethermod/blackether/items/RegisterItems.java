package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.DarkGrassBlock;
import net.ethermod.blackether.blocks.NeutronBomb;
import net.ethermod.blackether.entity.NeutronBombEntity;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RegisterItems {
    public static final Logger LOGGER = LogManager.getLogger(RegisterItems.class);

//    public static final ItemGroup BLACKETHERMOD_GROUP = FabricItemGroupBuilder.create(
//            new Identifier(BlackEtherMod.MODID, "ethermod_group"))
//            .icon(() -> new ItemStack(RegisterItems.ONYX_APPLE))
//            .build();
    public static final ItemGroup BLACKETHERMOD_GROUP = FabricItemGroup.builder(
                    new Identifier(BlackEtherMod.MODID, "ethermod_group"))
            .icon(() -> new ItemStack(RegisterItems.ONYX_APPLE))
            .build();

    public static final EntityType<NeutronBombEntity> NEUTRON_BOMB_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(BlackEtherMod.MODID, "neutron_bomb"),
            FabricEntityTypeBuilder.<NeutronBombEntity>create(SpawnGroup.MISC, NeutronBombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );


    public static final ArmorMaterial ONYX_ARMOR_MATERIAL = new CustomArmorMaterial();
    public static final Item ONYX_HELMET = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()));
    public static final Item ONYX_CHESTPLATE = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()));
    public static final Item ONYX_LEGGINGS = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()));
    public static final Item ONYX_BOOTS = new ArmorItem(ONYX_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()));
    public static final Item ETHER_ORE = new Item(new FabricItemSettings());
    public static final Item ONYX_DUST = new Item(new FabricItemSettings());
    public static final Item ONYX_PICKAXE = new OnyxPickaxe();
    public static final Item ONYX_SHOVEL = new OnyxShovel();
    public static final Item ONYX_AXE = new OnyxAxe();
    public static final Item ONYX_HOE = new OnyxHoe();
    public static final Item ONYX_SWORD = new OnyxSword();
    public static final Block BLOCK_OF_ETHER = new BlockOfEther(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).strength(10.0F, 6.0F));
    public static final Item ONYX_APPLE = new OnyxApple();
    public static final Item ONYX_ORE = new Item(new FabricItemSettings());
    public static final Item NEUTRONIUM = new Item(new FabricItemSettings());
    public static final Block NEUTRON_BOMB = new NeutronBomb(FabricBlockSettings.of(Material.AIR).breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final DarkGrassBlock DARK_GRASS = new DarkGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(0.6f));

    public static final List<Item> BLACK_ETHER_ITEMS = List.of(
            ETHER_ORE, ONYX_DUST, ONYX_PICKAXE, ONYX_SHOVEL, ONYX_AXE, ONYX_HOE, ONYX_SWORD, ONYX_APPLE,
            ONYX_ORE, NEUTRONIUM);

    public static void register() {
        LOGGER.info("Registering items and blocks for Black Ether Mod");
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_apple"), ONYX_APPLE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_pickaxe"), ONYX_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_axe"), ONYX_AXE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_hoe"), ONYX_HOE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_sword"), ONYX_SWORD);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_shovel"), ONYX_SHOVEL);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "ether_ore"), ETHER_ORE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_ore"), ONYX_ORE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "neutronium"), NEUTRONIUM);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_dust"), ONYX_DUST);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "ether_ore_block"), new BlockItem(BlackEtherMod.ETHER_ORE_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "block_of_ether"), new BlockItem(BLOCK_OF_ETHER, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_helmet"), ONYX_HELMET);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_chestplate"), ONYX_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_leggings"), ONYX_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "onyx_boots"), ONYX_BOOTS);
        Registry.register(Registries.BLOCK, new Identifier(BlackEtherMod.MODID, "neutron_bomb"), NEUTRON_BOMB);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "neutron_bomb"), new BlockItem(NEUTRON_BOMB, new FabricItemSettings()));

        //BLOCKS
        Registry.register(Registries.BLOCK, new Identifier(BlackEtherMod.MODID, "ether_ore_block"), BlackEtherMod.ETHER_ORE_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(BlackEtherMod.MODID, "block_of_ether"), BLOCK_OF_ETHER);

        //Add darkgrass block
        Registry.register(Registries.BLOCK, new Identifier(BlackEtherMod.MODID, "dark_grass"), DARK_GRASS);
        Registry.register(Registries.ITEM, new Identifier(BlackEtherMod.MODID, "dark_grass"), new BlockItem(DARK_GRASS, new FabricItemSettings()));

        ItemGroupEvents.modifyEntriesEvent(BLACKETHERMOD_GROUP).register(content -> {
            BLACK_ETHER_ITEMS.forEach(content::add);
            content.add(BLOCK_OF_ETHER);
            content.add(BlackEtherMod.ETHER_ORE_BLOCK);
            content.add(DARK_GRASS);
            content.add(ONYX_HELMET);
            content.add(ONYX_CHESTPLATE);
            content.add(ONYX_LEGGINGS);
            content.add(ONYX_BOOTS);
            content.add(NEUTRON_BOMB);
        });

        FuelRegistry.INSTANCE.add(ETHER_ORE, 3000);
    }

}
