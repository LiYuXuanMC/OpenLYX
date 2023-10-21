package al.logger.client;

import al.logger.client.agent.Agent;
import al.logger.client.bridge.BridgeManager;
import al.logger.client.config.ConfigManager;
import al.logger.client.event.EventBus;
import al.logger.client.features.commands.CommandManager;
import al.logger.client.features.modules.GlobalConfiguration;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.lock.LockManager;
import al.logger.client.managers.AuthUserManager;
import al.logger.client.resource.ResourceManager;
import al.logger.client.script.ScriptManager;
import al.logger.client.transform.TransformManager;
import al.logger.client.ui.managers.GuiManager;
import al.logger.client.ui.managers.MessageManager;
import al.logger.client.ui.managers.MusicManager;
import al.logger.client.ui.managers.NotificationManager;
import al.logger.client.utils.FriendManager;
import al.logger.client.utils.LoggerUser;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.value.ValueManager;
import al.logger.client.wrapper.map.MapParser;
import est.builder.annotations.Export;
import lombok.Getter;
import lombok.Setter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Project Logger
 * 尽可能保证面向对象的写法
 */


@Export(name = "al_logger_client_Logger_className")
public class Logger {
    private static Logger Instance;
    public ModuleManager moduleManager;
    @Getter
    public TransformManager transformManager;
    @Getter
    public ScriptManager scriptManager;
    @Getter
    public ConfigManager configManager;
    @Getter
    public CommandManager commandManager;
    public MessageManager messageManager;
    @Getter
    public ValueManager valueManager;
    public EventBus eventBus;
    @Getter
    public MapParser mapParser;
    @Getter
    public ResourceManager resourceManager;
    @Getter
    public GuiManager guiManager;
    @Getter
    public LoggerWS loggerWS;
    @Getter
    public boolean isMySelfObf = false;
    @Getter
    @Setter
    Agent agent;
    @Getter
    @Setter
    LoggerUser loggerUser;
    @Getter
    BridgeManager bridgeManager;
    @Getter
    public NotificationManager notificationManager;
    @Getter
    public FriendManager friendManager;
    @Getter
    private boolean unloading = false;
    public MusicManager musicManager;
    @Getter
    private InjectorSocket injectorSocket;
    @Getter
    public AuthUserManager authUserManager;
    @Getter
    public LockManager lockManager;
    @Getter
    public GlobalConfiguration globalConfiguration;
    public final String currentVer = "Private";

    public Logger() {


        if (Instance != null) return;

        Instance = this;
        try {
            Class.forName(new StringBuilder().append("al").append(".").append("logger").append(".").append("client").append(".").append("features").append(".").append("modules").append(".").append("ModuleManager").toString());
        } catch (ClassNotFoundException e) {
            isMySelfObf = true;
        }
        System.out.println("Injected");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Connecting to injector...");
                injectorSocket = new InjectorSocket();
                injectorSocket.connect();
            }
        }).start();
    }

    public void addProgressBar(String name, int max) {
        if (injectorSocket != null)
            injectorSocket.addProgressBar(name, max);
    }

    public void setProgressBar(String name, int value) {
        if (injectorSocket != null)
            injectorSocket.setProgressBar(name, value);
    }

    public void removeProgressBar(String name) {
        if (injectorSocket != null)
            injectorSocket.removeProgressBar(name);
    }

    public void injectFailed(String reason) {
        if (injectorSocket != null)
            injectorSocket.injectFailed(reason);
    }

    public void injectSucceed() {
        if (injectorSocket != null)
            injectorSocket.injectSucceed();
    }

    public void unload() {
        unloading = true;
    }

    public void Logger_Verify() {
        try {
            loggerWS = new LoggerWS();
            loggerWS.connect();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Export(name = "al_logger_client_Logger_registerNative_methodName")
    public static native void registerNative(Class<?> targetClass);

    @ExportObfuscate(name = "getEventBus")
    public EventBus getEventBus() {
        return eventBus;
    }

    @ExportObfuscate(name = "getInstance")
    public static Logger getInstance() {
        return Instance;
    }

    @ExportObfuscate(name = "getModuleManager")
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
