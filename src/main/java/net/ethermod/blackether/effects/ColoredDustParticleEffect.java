package net.ethermod.blackether.effects;

import net.minecraft.particle.DustParticleEffect;

public class ColoredDustParticleEffect extends DustParticleEffect {
    public static final DustParticleEffect RED = new DustParticleEffect(1.0F, 0.0F, 0.0F, 1.0F);
    public static final DustParticleEffect BLACK = new DustParticleEffect(0.0F, 0.0F, 0.0F, 1.0F);
    public ColoredDustParticleEffect(float float_1, float float_2, float float_3, float float_4) {
        super(float_1, float_2, float_3, float_4);
    }


}
