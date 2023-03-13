package net.ethermod.blackether.effects;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;

public class ColoredDustParticleEffect extends DustParticleOptions {
    public static final DustParticleOptions BLACK = new DustParticleOptions(new Vec3(0.0F, 0.0F, 0.0F).toVector3f(), 1.0F);
    public static final DustParticleOptions GREEN = new DustParticleOptions(new Vec3(0.0F, 0.5F, 0.0F).toVector3f(), 1.0F);
    public static final DustParticleOptions NEON_GREEN = new DustParticleOptions(new Vec3(0.23F, 1F, 0.008F).toVector3f(), 1.0F);


    /**
     * Created a colored Dust particle effect.
     *
     * @param r     red component
     * @param g     green component
     * @param b     blue component
     * @param scale scale
     */
    public ColoredDustParticleEffect(float r, float g, float b, float scale) {
        super(new Vec3(r, g, b).toVector3f(), scale);
    }


}
