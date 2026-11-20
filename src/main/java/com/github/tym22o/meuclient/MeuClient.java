package com.github.tym22o.meuclient;

import com.github.tym22o.meuclient.event.HudHandler;
import com.github.tym22o.meuclient.event.KeyHandler;
import com.github.tym22o.meuclient.module.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "meuclient", useMetadata = true, clientSideOnly = true,
     acceptedMinecraftVersions = "[1.8.9]")
public class MeuClient {
    public static ModuleManager moduleManager;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager = new ModuleManager();
        MinecraftForge.EVENT_BUS.register(new HudHandler());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
    }
}
