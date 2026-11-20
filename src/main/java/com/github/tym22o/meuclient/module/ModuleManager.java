package com.github.tym22o.meuclient.module;

import com.github.tym22o.meuclient.config.ConfigManager;
import com.github.tym22o.meuclient.module.impl.CpsModule;
import com.github.tym22o.meuclient.module.impl.FpsModule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final Module[] modules;
    private final List<Module> view;

    public ModuleManager() {
        modules = new Module[] { new FpsModule(), new CpsModule() };
        view = Collections.unmodifiableList(Arrays.asList(modules));
        ConfigManager.load(view);
    }

    /** Use no render/tick: um array não cria Iterator a cada frame. */
    public Module[] getArray() { return modules; }

    /** Use apenas em GUI/config (fora do caminho quente). */
    public List<Module> getModules() { return view; }

    public void tick() {
        for (int i = 0; i < modules.length; i++) {
            Module m = modules[i];
            if (m.isEnabled()) m.onTick();
        }
    }
}
