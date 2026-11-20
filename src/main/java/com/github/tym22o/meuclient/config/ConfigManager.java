package com.github.tym22o.meuclient.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.github.tym22o.meuclient.module.Module;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type TYPE = new TypeToken<Map<String, Boolean>>() {}.getType();

    private static File file() {
        return new File(Minecraft.getMinecraft().mcDataDir, "config/meuclient.json");
    }

    public static void load(List<Module> modules) {
        File f = file();
        if (!f.exists()) return;
        try (Reader r = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)) {
            Map<String, Boolean> map = GSON.fromJson(r, TYPE);
            if (map == null) return;
            for (Module m : modules) {
                Boolean b = map.get(m.getName());
                if (b != null) m.setEnabled(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(List<Module> modules) {
        Map<String, Boolean> map = new LinkedHashMap<String, Boolean>();
        for (Module m : modules) map.put(m.getName(), m.isEnabled());
        File f = file();
        f.getParentFile().mkdirs();
        try (Writer w = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8)) {
            GSON.toJson(map, TYPE, w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
