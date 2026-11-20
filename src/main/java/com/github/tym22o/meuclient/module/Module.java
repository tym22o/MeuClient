package com.github.tym22o.meuclient.module;

import net.minecraft.client.Minecraft;

public abstract class Module {
    protected final Minecraft mc = Minecraft.getMinecraft();
    private final String name;
    private boolean enabled;

    public Module(String name, boolean enabledByDefault) {
        this.name = name;
        this.enabled = enabledByDefault;
    }

    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void toggle() { enabled = !enabled; }

    /** Altura em pixels que o módulo ocupa no HUD. */
    public int getHeight() { return 12; }

    /** Chamado 20x por segundo (tick). Faça cálculos e monte Strings AQUI, nunca no render. */
    public void onTick() {}

    /** Chamado a cada frame. Deve apenas desenhar valores já prontos (sem alocar objetos). */
    public abstract void renderHud(int x, int y);
}
