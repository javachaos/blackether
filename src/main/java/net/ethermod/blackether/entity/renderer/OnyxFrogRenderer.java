package net.ethermod.blackether.entity.renderer;

import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.entity.model.OnyxFrogModel;
import net.ethermod.blackether.entity.model.OnyxSnakeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * OnyxSnake entity renderer.
 *
 * @see OnyxSnakeModel
 * @see OnyxSnakeEntity
 */
public class OnyxFrogRenderer extends GeoEntityRenderer<OnyxFrogEntity> {
    public OnyxFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OnyxFrogModel());
    }
}

