package net.ethermod.blackether.utils;

import net.minecraft.client.network.packet.PlayerPositionLookS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

import java.util.Set;

public class PlayerUtils {

    public static void teleport(ServerCommandSource serverCommandSource_1, Entity entity_1, ServerWorld serverWorld_1, double double_1, double double_2, double double_3, Set<PlayerPositionLookS2CPacket.Flag> set_1, float float_1, float float_2) {
        if (entity_1 instanceof ServerPlayerEntity) {
            ChunkPos chunkPos_1 = new ChunkPos(new BlockPos(double_1, double_2, double_3));
            serverWorld_1.method_14178().addTicket(ChunkTicketType.POST_TELEPORT, chunkPos_1, 1, entity_1.getEntityId());
            entity_1.stopRiding();
            if (((ServerPlayerEntity)entity_1).isSleeping()) {
                ((ServerPlayerEntity)entity_1).wakeUp(true, true, false);
            }

            if (serverWorld_1 == entity_1.world) {
                ((ServerPlayerEntity)entity_1).networkHandler.teleportRequest(double_1, double_2, double_3, float_1, float_2, set_1);
            } else {
                ((ServerPlayerEntity)entity_1).teleport(serverWorld_1, double_1, double_2, double_3, float_1, float_2);
            }

            entity_1.setHeadYaw(float_1);
        } else {
            float float_3 = MathHelper.wrapDegrees(float_1);
            float float_4 = MathHelper.wrapDegrees(float_2);
            float_4 = MathHelper.clamp(float_4, -90.0F, 90.0F);
            if (serverWorld_1 == entity_1.world) {
                entity_1.setPositionAndAngles(double_1, double_2, double_3, float_3, float_4);
                entity_1.setHeadYaw(float_3);
            } else {
                entity_1.detach();
                entity_1.dimension = serverWorld_1.dimension.getType();
                Entity entity_2 = entity_1;
                entity_1 = entity_1.getType().create(serverWorld_1);
                if (entity_1 == null) {
                    return;
                }

                entity_1.copyFrom(entity_2);
                entity_1.setPositionAndAngles(double_1, double_2, double_3, float_3, float_4);
                entity_1.setHeadYaw(float_3);
                serverWorld_1.method_18769(entity_1);
                entity_2.removed = true;
            }
        }

        if (!(entity_1 instanceof LivingEntity) || !((LivingEntity)entity_1).isFallFlying()) {
            entity_1.setVelocity(entity_1.getVelocity().multiply(1.0D, 0.0D, 1.0D));
            entity_1.onGround = true;
        }

    }

}
