package net.ethermod.blackether.utils;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.client.MinecraftClient;

public class GameUtils {

    public static void displayTextInGame(String text) {
        if (text != null && !text.isEmpty() && text.matches("^[0-9a-zA-Z &_.!?$%^#/,\\[\\]()@]*$")) {
            MinecraftClient.getInstance().inGameHud.setOverlayMessage(text, false);
        } else {
            BlackEtherMod.LOGGER.warn("Cannot display text, does not match regex. Or is empty.");
            MinecraftClient.getInstance().inGameHud.setOverlayMessage("Mod error. In mod: " + BlackEtherMod.MODID, false);
        }
    }
}
