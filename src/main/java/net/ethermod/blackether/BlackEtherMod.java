package net.ethermod.blackether;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.ethermod.blackether.biomes.OnyxBiome;
import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.features.OnyxFortFeature;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.ethermod.blackether.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.impl.registry.FuelRegistryImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.network.packet.PlayerPositionLookS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TeleportCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.EnumSet;
import java.util.Set;

import static com.mojang.brigadier.arguments.StringArgumentType.getString; // getString(ctx, "string")
import static com.mojang.brigadier.arguments.StringArgumentType.word; // word(), string(), greedyString()
import static com.mojang.brigadier.arguments.IntegerArgumentType.*; // word(), string(), greedyString()
import static com.mojang.brigadier.arguments.ArgumentType.*; // word(), string(), greedyString()
import static net.minecraft.server.command.CommandManager.literal; // literal("foo")
import static net.minecraft.server.command.CommandManager.argument; // argument("bar", word())
import static net.minecraft.server.command.CommandManager.*; // Import everything

public class BlackEtherMod implements ModInitializer {
	public static final String MODID = "ethermod";
	public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.LAVA).ticksRandomly().lightLevel(9).strength(5.0F, 6.0F).build());
	public static final Item ETHER_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_DUST = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_PICKAXE = new OnyxPickaxe();
	public static final Item ONYX_SHOVEL = new OnyxShovel();
	public static final Item ONYX_AXE = new OnyxAxe();
	public static final Item ONYX_HOE = new OnyxHoe();
	public static final Item ONYX_SWORD = new OnyxSword();
	public static final Block BLOCK_OF_ETHER = new BlockOfEther(FabricBlockSettings.of(Material.METAL, MaterialColor.BLACK).strength(10.0F, 6.0F).build());
	public static final StructurePieceType myStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "onyx_fort_piece", OnyxFortGenerator.Piece::new);
	public static final StructureFeature<DefaultFeatureConfig> onyxFortFeature = Registry.register(Registry.FEATURE, "onyx_fort_feature", new OnyxFortFeature());
	public static final StructureFeature<?> myStructure = Registry.register(Registry.STRUCTURE_FEATURE, "onyx_fort_structure", onyxFortFeature);
    public static final Biome ONYX_BIOME = Registry.register(Registry.BIOME, new Identifier(MODID, "onyx_biome"), new OnyxBiome());
	public static final Item ONYX_HELMET = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.HEAD, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_CHESTPLATE = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.CHEST, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_LEGGINGS = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.LEGS, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_BOOTS = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.FEET, (new Item.Settings().group(ItemGroup.COMBAT)));

	static {
		Feature.STRUCTURES.put("onyx feature", onyxFortFeature);
		//ITEMS
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_pickaxe"), ONYX_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_axe"), ONYX_AXE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_hoe"), ONYX_HOE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_sword"), ONYX_SWORD);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_shovel"), ONYX_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(MODID, "ether_ore"), ETHER_ORE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_ore"), ONYX_ORE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_dust"), ONYX_DUST);
		Registry.register(Registry.ITEM, new Identifier(MODID, "ether_ore_block"), new BlockItem(ETHER_ORE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(MODID, "block_of_ether"), new BlockItem(BLOCK_OF_ETHER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_helmet"), ONYX_HELMET);
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_chestplate"), ONYX_CHESTPLATE);
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_leggings"), ONYX_LEGGINGS);
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_boots"), ONYX_BOOTS);
		//BLOCKS
		Registry.register(Registry.BLOCK, new Identifier(MODID, "ether_ore_block"), ETHER_ORE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(MODID, "block_of_ether"), BLOCK_OF_ETHER);
		FuelRegistryImpl.INSTANCE.add(ETHER_ORE, 3000);
	}

	private void registerCommands() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> { // Or directly registering the command to the dispatcher.
			dispatcher.register(CommandManager.literal("rtp")
					.then(CommandManager.argument("radius", integer(0,10000000))
							// The command to be executed if the command "foo" is entered with the argument "bar"
							.executes(ctx -> {
								PlayerEntity p = ctx.getSource().getPlayer();
								ServerWorld w = ctx.getSource().getWorld();
								int r = getInteger(ctx, "radius");
								double delta_x = r * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
								double delta_z = r * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
								double new_x = delta_x + p.x;
								double new_z = delta_z + p.z;
								double new_y = 0;
								int i = w.getSeaLevel();
								while(!w.isAir(new BlockPos(new_x,i++,new_z)) && new_y + w.getSeaLevel() < w.getHeight()) {
									new_y++;
								}
								teleport(ctx.getSource(), p, w, new_x, i,new_z, EnumSet.noneOf(PlayerPositionLookS2CPacket.Flag.class), p.yaw, p.pitch);
								System.out.println("Radius set to "+r+", spawning at ["+(int) new_x+"] ["+(int) p.y+"] ["+(int) new_z+"]");
								// Return a result. -1 is failure, 0 is a pass and 1 is success.
								return 1;
							}))
					// The command "foo" to execute if there are no arguments.
					.executes(ctx -> {
						PlayerEntity p = ctx.getSource().getPlayer();
						ServerWorld w = ctx.getSource().getWorld();
						double delta_x = 10000 * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
						double delta_z = 10000 * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
						double new_x = delta_x + p.x;
						double new_z = delta_z + p.z;
						double new_y = 0;
						int i = w.getSeaLevel();
						while(!w.isAir(new BlockPos(new_x,i++,new_z)) && new_y + w.getSeaLevel() < w.getHeight()) {
							new_y++;
						}
						teleport(ctx.getSource(), p, w, new_x, i,new_z, EnumSet.noneOf(PlayerPositionLookS2CPacket.Flag.class), p.yaw, p.pitch);
						System.out.println("Default radius set to 10000, spawning at ["+(int) new_x+"] ["+(int) p.y+"] ["+(int) new_z+"]");
						return 1;
					})
			);
		});
	}

	@Override
	public void onInitialize() {
		registerCommands();
		OverworldBiomes.addContinentalBiome(BlackEtherMod.ONYX_BIOME, OverworldClimate.TEMPERATE, 2D);
		OverworldBiomes.addContinentalBiome(BlackEtherMod.ONYX_BIOME, OverworldClimate.COOL, 2D);
		FabricBiomes.addSpawnBiome(BlackEtherMod.ONYX_BIOME);
		OverworldBiomes.setRiverBiome(BlackEtherMod.ONYX_BIOME, null);
		OverworldBiomes.addHillsBiome(BlackEtherMod.ONYX_BIOME, Biomes.MOUNTAINS, 0.1);
		OverworldBiomes.addBiomeVariant(Biomes.PLAINS, BlackEtherMod.ONYX_BIOME, 0.33);
		Registry.BIOME.forEach(this::handleBiome);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));

	}

	/**
	 * Called during init for each biome.
	 * @param biome
	 */
	private void handleBiome(Biome biome) {
		if(biome.getCategory() != Biome.Category.RIVER
				&& biome.getCategory() != Biome.Category.OCEAN
				&& biome.getCategory() != Biome.Category.THEEND) {
			biome.addStructureFeature(onyxFortFeature, new DefaultFeatureConfig());
			biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
					Biome.configureFeature(
							onyxFortFeature,
							new DefaultFeatureConfig(),
							Decorator.CHANCE_PASSTHROUGH,
							new ChanceDecoratorConfig(10)
					));
		}
		if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
			biome.addFeature(
					GenerationStep.Feature.UNDERGROUND_ORES,
					Biome.configureFeature(
							Feature.ORE,
							new OreFeatureConfig(
									OreFeatureConfig.Target.NATURAL_STONE,
									ETHER_ORE_BLOCK.getDefaultState(),
									5 //Ore vein size
							),
							Decorator.COUNT_RANGE,
							new RangeDecoratorConfig(
									8, //Number of veins per chunk
									0, //Bottom Offset
									0, //Min y level
									8 //Max y level
							)));
		}
	}

	private static void teleport(ServerCommandSource serverCommandSource_1, Entity entity_1, ServerWorld serverWorld_1, double double_1, double double_2, double double_3, Set<PlayerPositionLookS2CPacket.Flag> set_1, float float_1, float float_2) {
		if (entity_1 instanceof ServerPlayerEntity) {
			ChunkPos chunkPos_1 = new ChunkPos(new BlockPos(double_1, double_2, double_3));
			serverWorld_1.method_14178().addTicket(ChunkTicketType.POST_TELEPORT, chunkPos_1, 1, entity_1.getEntityId());
			entity_1.stopRiding();
			if (((ServerPlayerEntity)entity_1).isSleeping()) {
				((ServerPlayerEntity)entity_1).wakeUp(true, true, false);
			}

			if (serverWorld_1 == entity_1.world) {
				((ServerPlayerEntity)entity_1).networkHandler.teleportRequest(double_1, double_2, double_3, float_1, float_2, set_1);
			} else {
				((ServerPlayerEntity)entity_1).teleport(serverWorld_1, double_1, double_2, double_3, float_1, float_2);
			}

			entity_1.setHeadYaw(float_1);
		} else {
			float float_3 = MathHelper.wrapDegrees(float_1);
			float float_4 = MathHelper.wrapDegrees(float_2);
			float_4 = MathHelper.clamp(float_4, -90.0F, 90.0F);
			if (serverWorld_1 == entity_1.world) {
				entity_1.setPositionAndAngles(double_1, double_2, double_3, float_3, float_4);
				entity_1.setHeadYaw(float_3);
			} else {
				entity_1.detach();
				entity_1.dimension = serverWorld_1.dimension.getType();
				Entity entity_2 = entity_1;
				entity_1 = entity_1.getType().create(serverWorld_1);
				if (entity_1 == null) {
					return;
				}

				entity_1.copyFrom(entity_2);
				entity_1.setPositionAndAngles(double_1, double_2, double_3, float_3, float_4);
				entity_1.setHeadYaw(float_3);
				serverWorld_1.method_18769(entity_1);
				entity_2.removed = true;
			}
		}

		if (!(entity_1 instanceof LivingEntity) || !((LivingEntity)entity_1).isFallFlying()) {
			entity_1.setVelocity(entity_1.getVelocity().multiply(1.0D, 0.0D, 1.0D));
			entity_1.onGround = true;
		}

	}
}
