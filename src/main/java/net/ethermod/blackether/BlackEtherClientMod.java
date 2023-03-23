package net.ethermod.blackether;


import net.ethermod.blackether.entity.misc.NeutronBombEntity;
import net.ethermod.blackether.entity.misc.NeutronBombEntityRenderer;
import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.entity.renderer.OnyxFrogRenderer;
import net.ethermod.blackether.entity.renderer.OnyxSnakeRenderer;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.network.GeckoLibNetwork;

public final class BlackEtherClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerRenderers();
        registerNetwork();
    }

    private static void registerRenderers() {
        EntityRendererRegistry.register(EntityRegistry.getInstance().getEntityType(Naming.NEUTRON_BOMB, NeutronBombEntity.class), NeutronBombEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.getInstance().getEntityType(Naming.ONYX_SNAKE, OnyxSnakeEntity.class), OnyxSnakeRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.getInstance().getEntityType(Naming.ONYX_FROG, OnyxFrogEntity.class), OnyxFrogRenderer::new);

        registerTrees();
    }

    private static void registerTrees() {
        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SAPLING),
                RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LEAVES),
                RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG),
                RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS),
                RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG_STRIPPED),
                RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD),
                RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD_STRIPPED),
                RenderType.cutout());
    }

    private static void registerNetwork() {
        GeckoLibNetwork.registerClientReceiverPackets();
    }
}
