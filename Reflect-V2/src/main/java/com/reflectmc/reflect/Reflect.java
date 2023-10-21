package com.reflectmc.reflect;

import com.reflectmc.builder.annotation.ExportName;
import com.reflectmc.loader.Loader;
import com.reflectmc.loader.data.UserData;
import com.reflectmc.loader.packet.local.LocalPacketProcessor;
import com.reflectmc.loader.packet.local.PacketI01Metadata;
import com.reflectmc.loader.socket.InjectorSocket;
import com.reflectmc.loader.socket.ServerSocket;
import com.reflectmc.reflect.bridge.BridgeManager;
import com.reflectmc.reflect.config.ConfigManager;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.features.CommandManager;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;
import com.reflectmc.reflect.resource.ResourceManager;
import com.reflectmc.reflect.socket.irc.IServerPacketProcessor;
import com.reflectmc.reflect.transform.TransformManager;
import com.reflectmc.reflect.utils.render.font.FontManager;
import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.cactus.CactusAdaptor;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.FontRenderer;
import lombok.Getter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@ExportName(export = "OBF/Reflect")
public class Reflect {
    private static Reflect INSTANCE = null;
    @Getter
    private final ServerSocket serverSocket;
    @Getter
    private final InjectorSocket injectorSocket;
    @Getter
    private final UserData userData;
    @Getter
    private final PacketI01Metadata metadata;
    @Getter
    private final ResourceManager resourceManager;
    private final EventBus eventBus;
    private final ModuleManager moduleManager;
    @Getter
    private final CommandManager commandManager;
    @Getter
    private final TransformManager transformManager;
    @Getter
    private final BridgeManager bridgeManager;
    @Getter
    private final ConfigManager configManager;
    public static final String CLIENT_VERSION = "NewEra";
    public static final String CLIENT_SUB_VERSION = "1.0";
    public static Color ColorStyle = new Color(0, 162, 232);
    public static boolean LOAD_SUCCEED = false;
    public Reflect() throws FileNotFoundException {
        INSTANCE = this;
        //System.setOut(new PrintStream("out.txt"));
        System.out.println("Reflect the darkest side in your HEART");
        Loader loader = Loader.getINSTANCE();
        this.serverSocket = loader.getServerSocket();
        this.serverSocket.setPacketProcessor(new IServerPacketProcessor(serverSocket));
        this.injectorSocket = loader.getInjectorSocket();
        this.userData = loader.getUserData();
        this.metadata = LocalPacketProcessor.getMetadata();
        this.resourceManager = new ResourceManager();
        this.eventBus = new EventBus();
        this.moduleManager = new ModuleManager();
        this.commandManager = new CommandManager();
        this.transformManager = new TransformManager();
        this.bridgeManager = new BridgeManager();
        this.configManager = new ConfigManager();

        new Thread(() -> {
            injectorSocket.updateProgress("Init Wrapper",0,0);
            Wrapper.init();
            injectorSocket.updateProgress("Init Resource",0,0);
            resourceManager.init();
            injectorSocket.updateProgress("Wrapping",0,0);
            Wrapper.wrap();
            if (Wrapper.getMapper().hasAntiCheat(Environment.NPlusAntiCheat)){
                try {
                    CactusAdaptor.deobfuscate();
                    CactusAdaptor.locateVariable();
                } catch (Exception e) {
                    e.printStackTrace();
                    injectorSocket.injectFail(e.getMessage());
                }
            }
            injectorSocket.updateProgress("Init EventBus",0,0);
            eventBus.init();
            injectorSocket.updateProgress("Init Modules",0,0);
            moduleManager.init();
            commandManager.init();
            injectorSocket.updateProgress("Load Fonts",0,0);
            FontManager.init();
            bridgeManager.init();
            injectorSocket.updateProgress("Loading",0,0);
            transformManager.loadTransformers();
            transformManager.transform();
            configManager.init();
            LOAD_SUCCEED = true;
        }).start();
    }
    @ExportObfuscate(name = "getEventBus")
    public EventBus getEventBus(){
        return eventBus;
    }
    @ExportObfuscate(name = "getINSTANCE")
    public static Reflect getINSTANCE(){
        return INSTANCE;
    }
    @ExportObfuscate(name = "getModuleManager")
    public ModuleManager getModuleManager(){
        return moduleManager;
    }
}
