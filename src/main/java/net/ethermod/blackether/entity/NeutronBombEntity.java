package net.ethermod.blackether.entity;

import net.ethermod.blackether.items.RegisterItems;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class NeutronBombEntity extends Entity {
    private static final TrackedData<Integer> FUSE;
    private static final int DEFAULT_FUSE = 80;

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
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 80);
    }

    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }

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
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 6.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(2.0625D), this.getZ(), 256.0F, Explosion.DestructionType.NONE);
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
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    static {
        FUSE = DataTracker.registerData(NeutronBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}