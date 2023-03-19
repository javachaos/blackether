package net.ethermod.blackether.entity.model;

import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class OnyxSnakeModel extends DefaultedEntityGeoModel<OnyxSnakeEntity> {
    public OnyxSnakeModel() {
        super(new ResourceLocation(MOD_ID, Naming.ONYX_SNAKE));
    }

    @Override
    public RenderType getRenderType(OnyxSnakeEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}