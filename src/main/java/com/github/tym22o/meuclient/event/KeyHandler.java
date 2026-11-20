package com.github.tym22o.meuclient.event;

import com.github.tym22o.meuclient.gui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyHandler {
    public static final KeyBinding OPEN_GUI =
            new KeyBinding("Abrir menu", Keyboard.KEY_RSHIFT, "MeuClient");

    public KeyHandler() { ClientRegistry.registerKeyBinding(OPEN_GUI); }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
        if (OPEN_GUI.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
        }
    }
}
