package net.ethermod.blackether;


import net.ethermod.blackether.entity.misc.NeutronBombEntity;
import net.ethermod.blackether.entity.misc.NeutronBombEntityRenderer;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.entity.renderer.OnyxSnakeRenderer;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.utils.Naming;
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
        EntityRendererRegistry.register(EntityRegistry.getInstance().getEntityType(Naming.NEUTRON_BOMB, NeutronBombEntity.class), NeutronBombEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.getInstance().getEntityType(Naming.ONYX_SNAKE, OnyxSnakeEntity.class), OnyxSnakeRenderer::new);
    }

    private static void registerNetwork() {
        GeckoLibNetwork.registerClientReceiverPackets();
    }
}
