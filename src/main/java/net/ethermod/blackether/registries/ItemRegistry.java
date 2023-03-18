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
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.HELMET, (new Item.Properties())));
        registerItem(Naming.ONYX_CHESTPLATE,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
        registerItem(Naming.ONYX_LEGGINGS,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
        registerItem(Naming.ONYX_BOOTS,
                new ArmorItem(onyxArmorMaterial, ArmorItem.Type.BOOTS, (new Item.Properties())));
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
                .displayItems((displayParameters, x) -> {
                    x.accept(BlockRegistry.getInstance().getBlock(Naming.BLOCK_OF_ETHER));
                    x.accept(BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK));
                    x.accept(BlockRegistry.getInstance().getBlock(Naming.DARK_GRASS));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_HELMET));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_CHESTPLATE));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_LEGGINGS));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_BOOTS));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_DUST));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_PICKAXE));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_SHOVEL));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_AXE));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_HOE));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_SWORD));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.NEUTRONIUM));
                    x.accept(ItemRegistry.getInstance().getItem(Naming.ONYX_SNAKE_EGG));
                    x.accept(BlockRegistry.getInstance().getBlock(Naming.NEUTRON_BOMB));
                })
                .build();
    }
}
