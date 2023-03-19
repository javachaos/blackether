package net.ethermod.blackether.entity.renderer;

import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.entity.model.OnyxSnakeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * OnyxSnake entity renderer.
 *
 * @see OnyxSnakeModel
 * @see OnyxSnakeEntity
 */
public class OnyxSnakeRenderer extends GeoEntityRenderer<OnyxSnakeEntity> {
    public OnyxSnakeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OnyxSnakeModel());
    }
}

