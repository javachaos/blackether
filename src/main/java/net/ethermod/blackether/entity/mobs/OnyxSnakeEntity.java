package net.ethermod.blackether.entity.mobs;

import net.ethermod.blackether.registries.SoundRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;

public class OnyxSnakeEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public OnyxSnakeEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);

    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.getInstance().getSoundEvent(Naming.SNAKE_HISS);
    }

    @Override
    protected float getSoundVolume() {
        return 0.25f;
    }

    @Override
    public int getAmbientSoundInterval() {
        return super.getAmbientSoundInterval() + 500;
    }

    @Override
    public void onEnterCombat() {
        super.onEnterCombat();
        getLevel().playSound(null, getX(), getY(), getZ(),
                SoundRegistry.getInstance().getSoundEvent(Naming.SNAKE_HISS),
                SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 6.0D));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.1, false));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }


    // Add our animations
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != Objects.requireNonNull(o).getClass()) return false;
        if (!super.equals(o)) return false;
        OnyxSnakeEntity that = (OnyxSnakeEntity) o;
        return Objects.equals(cache, that.cache);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cache);
    }
}
