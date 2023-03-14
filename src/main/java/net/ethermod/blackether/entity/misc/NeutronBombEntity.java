package net.ethermod.blackether.entity.misc;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.registries.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class NeutronBombEntity extends Entity {
    private static final EntityDataAccessor<Integer> FUSE;
    private static final int DEFAULT_FUSE = 80;

    @Nullable
    private LivingEntity causingEntity;

    public NeutronBombEntity(EntityType<NeutronBombEntity> entityType, Level world) {
        super(entityType, world);
    }

    public NeutronBombEntity(Level world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(EntityRegistry.NEUTRON_BOMB_ENTITY, world);
        this.setPos(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setDeltaMovement(-Math.sin(d) * 0.02D, 0.020000000298023224D, -Math.cos(d) * 0.02D);
        this.setFuse(DEFAULT_FUSE);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(FUSE, 80);
    }

    @Override
    protected Entity.@NotNull MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0D, 0.0004D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.78D));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, 0.0008D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX(), getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void explode() {
        float radius = BlackEtherMod.PROPERTIES.getFloatProperty("neutron.bomb.radius", 55f);
        float power = BlackEtherMod.PROPERTIES.getFloatProperty("neutron.bomb.power", 150f);
        explodeNeutron(power, radius);
    }

    private void explodeNeutron(double power, double radius) {
        double dy = getY(2.0625D);
        this.level.gameEvent(this, GameEvent.EXPLODE, new BlockPos(getX(), getY(), getZ()));
        double q = radius * 2.0F;
        double k = Mth.floor(getX() - q - 1.0D);
        double l = Mth.floor(getX() + q + 1.0D);
        int r = Mth.floor(dy - q - 1.0D);
        int s = Mth.floor(dy + q + 1.0D);
        int t = Mth.floor(getZ() - q - 1.0D);
        int u = Mth.floor(getZ() + q + 1.0D);
        List<Entity> list = this.level.getEntities(this,
                new AABB(k, r, t, l, s, u));
        Vec3 vec3d = new Vec3(this.getX(), dy, this.getZ());
        calculateEntities(list, vec3d, dy, q, power);
        showParticles();
    }

    private void calculateEntities(List<Entity> list, Vec3 vec3d, double dy, double q, double power) {
        for (Entity entity : list) {
            if (!entity.ignoreExplosion()) {
                double w = Math.sqrt(entity.distanceToSqr(vec3d)) / q;
                if (w <= 1.0D) {
                    double x = entity.getX() - this.getX();
                    double y = (entity instanceof PrimedTnt ? entity.getY() : entity.getEyeY()) - dy;
                    double z = entity.getZ() - this.getZ();
                    double aa = Math.sqrt(x * x + y * y + z * z);
                    if (aa != 0.0D) {
                        x /= aa;
                        y /= aa;
                        z /= aa;
                        double ab = Explosion.getSeenPercent(vec3d, entity);
                        double ac = (1.0D - w) * ab;
                        entity.hurt(DamageSource.thorns(this), ((int) ((ac * ac + ac) * power / 2.0 * 7.0 * q + 1.0)));
                        double ad = ac;
                        if (entity instanceof LivingEntity e) {
                            ad = ProtectionEnchantment.getExplosionKnockbackAfterDampener(e, ac);
                        }
                        entity.setDeltaMovement(entity.getDeltaMovement().add(x * ad, y * ad, z * ad));
                    }
                }
            }
        }
    }

    private void showParticles() {
        level.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ(), 1.0D, 0.0D, 0.0D);
        level.explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
        animateExplosion();
    }

    private void animateExplosion() {
        AreaEffectCloud areaEffectCloudEntity = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
        areaEffectCloudEntity.setRadius(2.5F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
        areaEffectCloudEntity.setRadiusPerTick(-areaEffectCloudEntity.getRadius() / areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(3))));
        areaEffectCloudEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(2))));
        areaEffectCloudEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(1))));
        areaEffectCloudEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(4))));
        this.level.addFreshEntity(areaEffectCloudEntity);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putShort("Fuse", (short) this.getFuse());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.setFuse(nbt.getShort("Fuse"));
    }

    @Override
    protected float getEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int fuse) {
        entityData.set(FUSE, fuse);
    }

    public int getFuse() {
        return entityData.get(FUSE);
    }

    static {
        FUSE = SynchedEntityData.defineId(NeutronBombEntity.class, EntityDataSerializers.INT);
    }
}