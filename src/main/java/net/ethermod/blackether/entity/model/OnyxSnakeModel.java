package net.ethermod.blackether.entity.model;

import net.ethermod.blackether.entity.living.OnyxSnakeEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static net.ethermod.blackether.BlackEtherMod.MODID;

public class OnyxSnakeModel extends DefaultedEntityGeoModel<OnyxSnakeEntity> {
    public OnyxSnakeModel() {
        super(new ResourceLocation(MODID, "onyx_snake"));
    }

    @Override
    public RenderType getRenderType(OnyxSnakeEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}