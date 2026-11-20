package com.github.tym22o.meuclient.module.impl;

import com.github.tym22o.meuclient.module.Module;
import net.minecraft.client.Minecraft;

public class FpsModule extends Module {
    private int lastFps = -1;
    private String text = "FPS: 0";

    public FpsModule() { super("FPS", true); }

    @Override
    public void onTick() {
        int fps = Minecraft.getDebugFPS();
        if (fps != lastFps) {          // só cria String nova quando o valor muda
            lastFps = fps;
            text = "FPS: " + fps;
        }
    }

    @Override
    public void renderHud(int x, int y) {
        mc.fontRendererObj.drawStringWithShadow(text, x, y, 0xFFFFFF);
    }
}
