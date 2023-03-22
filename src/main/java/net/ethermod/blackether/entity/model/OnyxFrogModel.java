package net.ethermod.blackether.entity.model;

import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class OnyxFrogModel extends DefaultedEntityGeoModel<OnyxFrogEntity> {
    public OnyxFrogModel() {
        super(new ResourceLocation(MOD_ID, Naming.ONYX_FROG));
    }

    @Override
    public RenderType getRenderType(OnyxFrogEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}