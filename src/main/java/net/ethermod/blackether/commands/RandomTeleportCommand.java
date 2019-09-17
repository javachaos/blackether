package net.ethermod.blackether.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.ethermod.blackether.utils.PlayerUtils;
import net.minecraft.client.network.packet.PlayerPositionLookS2CPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;

public class RandomTeleportCommand {

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
            commandDispatcher.register(CommandManager.literal("rtp")
                    .then(CommandManager.argument("radius", integer(0,10000000))
                            .executes(RandomTeleportCommand::execArgs))
                    .executes(RandomTeleportCommand::execNoArgs)
            );
    }

    private static int execNoArgs(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        PlayerEntity p = ctx.getSource().getPlayer();
        ServerWorld w = ctx.getSource().getWorld();
        double delta_x = 10000 * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
        double delta_z = 10000 * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
        double new_x = delta_x + p.x;
        double new_z = delta_z + p.z;
        double new_y = 0;
        int i = w.getSeaLevel();
        while (!w.isAir(new BlockPos(new_x, i++, new_z)) && new_y + w.getSeaLevel() < w.getHeight()) {
            new_y++;
        }
        PlayerUtils.teleport(ctx.getSource(), p, w, new_x, i, new_z, EnumSet.noneOf(PlayerPositionLookS2CPacket.Flag.class), p.yaw, p.pitch);
        System.out.println("Default radius set to 10000, spawning at [" + (int) new_x + "] [" + (int) p.y + "] [" + (int) new_z + "]");
        return 1;
    }

    private static int execArgs(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        PlayerEntity p = ctx.getSource().getPlayer();
        ServerWorld w = ctx.getSource().getWorld();
        int r = getInteger(ctx, "radius");
        double delta_x = r * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
        double delta_z = r * ctx.getSource().getPlayer().getRand().nextDouble() + 1;
        double new_x = delta_x + p.x;
        double new_z = delta_z + p.z;
        double new_y = 0;
        int i = w.getSeaLevel();
        while (!w.isAir(new BlockPos(new_x, i++, new_z)) && new_y + w.getSeaLevel() < w.getHeight()) {
            new_y++;
        }
        PlayerUtils.teleport(ctx.getSource(), p, w, new_x, i, new_z, EnumSet.noneOf(PlayerPositionLookS2CPacket.Flag.class), p.yaw, p.pitch);
        System.out.println("Radius set to " + r + ", spawning at [" + (int) new_x + "] [" + (int) p.y + "] [" + (int) new_z + "]");
        return 1;
    }
}
