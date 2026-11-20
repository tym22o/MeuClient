package com.github.tym22o.meuclient.module.impl;

import com.github.tym22o.meuclient.module.Module;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CpsModule extends Module {
    private static final int SIZE = 128;           // potência de 2
    private static final int MASK = SIZE - 1;

    private final long[] clicks = new long[SIZE];  // buffer circular, sem boxing de Long
    private int head = 0;                          // próxima posição de escrita
    private int count = 0;

    private int lastCps = -1;
    private String text = "CPS: 0";

    public CpsModule() {
        super("CPS", true);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onMouse(MouseEvent e) {
        if (e.button == 0 && e.buttonstate) {
            clicks[head] = System.currentTimeMillis();
            head = (head + 1) & MASK;
            if (count < SIZE) count++;
        }
    }

    @Override
    public void onTick() {
        long now = System.currentTimeMillis();
        int cps = 0;
        // percorre do clique mais novo para o mais antigo e para no primeiro vencido
        for (int i = 0; i < count; i++) {
            long t = clicks[(head - 1 - i) & MASK];
            if (now - t <= 1000L) cps++;
            else break;
        }
        if (cps != lastCps) {
            lastCps = cps;
            text = "CPS: " + cps;
        }
    }

    @Override
    public void renderHud(int x, int y) {
        mc.fontRendererObj.drawStringWithShadow(text, x, y, 0xFFFFFF);
    }
}
