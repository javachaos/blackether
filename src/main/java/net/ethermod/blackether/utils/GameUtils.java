package net.ethermod.blackether.utils;

import net.ethermod.blackether.BlackEtherMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class GameUtils {

    @Environment(EnvType.CLIENT)
    public static void displayTextInGame(String text) {
        if (text != null && !text.isEmpty() && text.matches("^[0-9a-zA-Z &_.!?$%^#/,\\[\\]()@]*$")) {
            MinecraftClient.getInstance().inGameHud.setOverlayMessage(text, false);
        } else {
            BlackEtherMod.LOGGER.warn("Cannot display text, does not match regex. Or is empty.");
            MinecraftClient.getInstance().inGameHud.setOverlayMessage("Mod error. In mod: " + BlackEtherMod.MODID, false);
        }
    }
}
