package net.ethermod.blackether.entity;

import com.google.common.collect.Maps;
import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.items.RegisterItems;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.List;

public class NeutronBombEntity extends Entity {
    private static final TrackedData<Integer> FUSE;
    private static final int DEFAULT_FUSE = 80;
    private final boolean inanimate;

    @Nullable
    private LivingEntity causingEntity;

    public NeutronBombEntity(World world) {
        super(RegisterItems.NEUTRON_BOMB_ENTITY, world);
        this.inanimate = false;
    }

    public NeutronBombEntity(EntityType<NeutronBombEntity> entityType, World world) {
        super(entityType, world);
        this.inanimate = false;
    }

    public NeutronBombEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(RegisterItems.NEUTRON_BOMB_ENTITY, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(d) * 0.02D, 0.020000000298023224D, -Math.cos(d) * 0.02D);
        this.setFuse(DEFAULT_FUSE);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 80);
    }

    @Override
    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    @Override
    public boolean collides() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0D, 0.0004D, 0.0D));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.78D));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7D, 0.0008D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient) {
                this.world.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX(), getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void explode() {
        float power = BlackEtherMod.PROPERTIES.getFloatProperty("neutron.bomb.power", 512.0F);
        float radius = BlackEtherMod.PROPERTIES.getFloatProperty("neutron.bomb.radius", 512.0F);
        explodeNeutron(power, radius);
    }
    
    private void explodeNeutron(double power, double radius) {
        double dy = getBodyY(2.0625D);
        this.world.emitGameEvent(this, GameEvent.EXPLODE, new BlockPos(getX(), getY(), getZ()));
        HashMap<LivingEntity, Vec3d> affectedPlayers = Maps.newHashMap();
        double q = power * 2.0F;
        double k = MathHelper.floor(getX() - q - 1.0D);
        double l = MathHelper.floor(getX() + q + 1.0D);
        int r = MathHelper.floor(dy - q - 1.0D);
        int s = MathHelper.floor(dy + q + 1.0D);
        int t = MathHelper.floor(getZ() - q - 1.0D);
        int u = MathHelper.floor(getZ() + q + 1.0D);
        List<Entity> list = this.world.getOtherEntities(this,
                new Box(k*radius, r*radius, t*radius, l*radius, s*radius, u*radius));
        Vec3d vec3d = new Vec3d(this.getX(), dy, this.getZ());

        for (Entity entity : list) {
            if (!entity.isImmuneToExplosion()) {
                double w = Math.sqrt(entity.squaredDistanceTo(vec3d)) / q;
                if (w <= 1.0D) {
                    double x = entity.getX() - this.getX();
                    double y = (entity instanceof TntEntity ? entity.getY() : entity.getEyeY()) - dy;
                    double z = entity.getZ() - this.getZ();
                    double aa = Math.sqrt(x * x + y * y + z * z);
                    if (aa != 0.0D) {
                        x /= aa;
                        y /= aa;
                        z /= aa;
                        double ab = Explosion.getExposure(vec3d, entity);
                        double ac = (1.0D - w) * ab;
                        entity.damage(DamageSource.thorns(this), (float) (power * ((int) ((ac * ac + ac) / 2.0D * 7.0D * q + 1.0D))));
                        double ad = ac;
                        if (entity instanceof LivingEntity) {
                            ad = ProtectionEnchantment.transformExplosionKnockback((LivingEntity) entity, ac);
                        }

                        entity.setVelocity(entity.getVelocity().add(x * ad, y * ad, z * ad));
                        if (entity instanceof PlayerEntity playerEntity && !playerEntity.isSpectator()
                                && (!playerEntity.getAbilities().flying)) {
                                affectedPlayers.put(playerEntity, new Vec3d(x * ac, y * ac, z * ac));
                        }
                    }
                }
            }
        }
        showParticles();
    }

    private void showParticles() {
        world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ(), 1.0D, 0.0D, 0.0D);
        world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)2, Explosion.DestructionType.BREAK);
        animateExplosion();
    }

    private void animateExplosion() {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setRadius(2.5F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffect.byRawId(3)));
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffect.byRawId(2)));
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffect.byRawId(1)));
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffect.byRawId(4)));
            this.world.spawnEntity(areaEffectCloudEntity);
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("Fuse", (short)this.getFuse());
    }
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int fuse) {
        dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return dataTracker.get(FUSE);
    }

    static {
        FUSE = DataTracker.registerData(NeutronBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}