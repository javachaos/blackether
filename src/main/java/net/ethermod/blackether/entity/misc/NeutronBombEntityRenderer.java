package net.ethermod.blackether.entity.misc;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NeutronBombEntityRenderer extends EntityRenderer<NeutronBombEntity> {

    public NeutronBombEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull NeutronBombEntity entity) {
        return new ResourceLocation(BlackEtherMod.MODID, "textures/block/neutron_bomb_side.png");
    }
}
