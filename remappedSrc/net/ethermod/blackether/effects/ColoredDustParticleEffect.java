package net.ethermod.blackether.effects;

import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Vec3d;

public class ColoredDustParticleEffect extends DustParticleEffect {
    public static final DustParticleEffect RED = new DustParticleEffect(new Vec3d(1.0, 0.0, 0.0).toVector3f(), 1.0F);
    public static final DustParticleEffect BLACK = new DustParticleEffect(new Vec3d(0.0F, 0.0F, 0.0F).toVector3f(), 1.0F);
    public static final DustParticleEffect GREEN = new DustParticleEffect(new Vec3d(0.0F, 0.5F, 0.0F).toVector3f(), 1.0F);
    public static final DustParticleEffect NEON_GREEN = new DustParticleEffect(new Vec3d(0.23F, 1F, 0.008F).toVector3f(), 1.0F);


    /**
     * Created a colored Dust particle effect.
     *
     * @param r     red component
     * @param g     green component
     * @param b     blue component
     * @param scale scale
     */
    public ColoredDustParticleEffect(float r, float g, float b, float scale) {
        super(new Vec3d(r, g, b).toVector3f(), scale);
    }


}
