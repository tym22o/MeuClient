package com.github.tym22o.meuclient.gui;

import com.github.tym22o.meuclient.MeuClient;
import com.github.tym22o.meuclient.config.ConfigManager;
import com.github.tym22o.meuclient.module.Module;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class ClickGui extends GuiScreen {
    @Override
    public void initGui() {
        List<Module> modules = MeuClient.moduleManager.getModules();
        int y = 20;
        for (int i = 0; i < modules.size(); i++) {
            Module m = modules.get(i);
            buttonList.add(new GuiButton(i, 10, y, 120, 20, label(m)));
            y += 24;
        }
    }

    private String label(Module m) {
        return m.getName() + ": " + (m.isEnabled() ? "ON" : "OFF");
    }

    @Override
    protected void actionPerformed(GuiButton b) {
        Module m = MeuClient.moduleManager.getModules().get(b.id);
        m.toggle();
        b.displayString = label(m);
    }

    @Override
    public void drawScreen(int mx, int my, float pt) {
        drawDefaultBackground();
        super.drawScreen(mx, my, pt);
    }

    @Override
    public void onGuiClosed() {
        ConfigManager.save(MeuClient.moduleManager.getModules());  // grava em disco só ao fechar
    }
}
