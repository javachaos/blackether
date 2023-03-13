package net.ethermod.blackether;


import net.ethermod.blackether.entity.renderer.OnyxSnakeRenderer;
import net.ethermod.blackether.registries.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import software.bernie.geckolib.network.GeckoLibNetwork;

public final class BlackEtherClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerRenderers();
        registerNetwork();
    }

    private static void registerRenderers() {
        EntityRendererRegistry.register(EntityRegistry.ONYX_SNAKE, OnyxSnakeRenderer::new);
    }

    private static void registerNetwork() {
        GeckoLibNetwork.registerClientReceiverPackets();
    }
}
