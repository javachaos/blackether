package net.ethermod.blackether.entity;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class NeutronBombEntityRenderer extends EntityRenderer<NeutronBombEntity>  {

    public NeutronBombEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(NeutronBombEntity entity) {
        return new Identifier(BlackEtherMod.MODID, "textures/block/neutron_bomb_side.png");
    }
}
