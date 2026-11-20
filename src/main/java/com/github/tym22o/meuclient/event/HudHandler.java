package com.github.tym22o.meuclient.event;

import com.github.tym22o.meuclient.MeuClient;
import com.github.tym22o.meuclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HudHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;
        if (MeuClient.moduleManager != null) MeuClient.moduleManager.tick();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        if (mc.gameSettings.showDebugInfo) return;     // não desenha sobre o F3

        Module[] modules = MeuClient.moduleManager.getArray();
        GlStateManager.color(1f, 1f, 1f, 1f);
        int y = 4;
        for (int i = 0; i < modules.length; i++) {      // for indexado: zero lixo por frame
            Module m = modules[i];
            if (!m.isEnabled()) continue;
            m.renderHud(4, y);
            y += m.getHeight();
        }
    }
}
