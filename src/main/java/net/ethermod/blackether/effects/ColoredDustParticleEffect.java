package net.ethermod.blackether.effects;

import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3f;

public class ColoredDustParticleEffect extends DustParticleEffect {
    public static final DustParticleEffect RED = new DustParticleEffect(new Vec3f(1.0F, 0.0F, 0.0F), 1.0F);
    public static final DustParticleEffect BLACK = new DustParticleEffect(new Vec3f(0.0F, 0.0F, 0.0F), 1.0F);
    public static final ParticleEffect GREEN = new DustParticleEffect(new Vec3f(0.0F, 0.5F, 0.0F), 1.0F);

    /**
     * Created a colored Dust particle effect.
     *
     * @param r     red component
     * @param g     green component
     * @param b     blue component
     * @param scale scale
     */
    public ColoredDustParticleEffect(float r, float g, float b, float scale) {
        super(new Vec3f(r, g, b), scale);
    }


}
