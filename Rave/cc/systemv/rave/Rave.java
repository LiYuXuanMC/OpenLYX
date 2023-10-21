package cc.systemv.rave;

import cc.systemv.rave.config.ConfigManager;
import cc.systemv.rave.event.EventBus;
import cc.systemv.rave.feature.command.CommandManager;
import cc.systemv.rave.feature.component.PullBackComponent;
import cc.systemv.rave.feature.component.RotationComponent;
import cc.systemv.rave.feature.module.ModuleManager;
import cc.systemv.rave.ui.animation.Animation;
import cc.systemv.rave.ui.font.FontManager;
import cc.systemv.rave.utils.friend.FriendManager;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Rave {
    public final static int DISPLAY_WIDTH = 1366;
    public final static int DISPLAY_HEIGHT = 768;
    public final static int FRAME_RATE = 120;
    public final static String CLIENT_NAME = "Rave";
    public final static String CLIENT_VERSION = "0.0.1";
    private static String AdditionalTitle;
    @Getter
    private static Rave Instance;
    private final static Logger logger = LogManager.getLogger("Rave");
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private EventBus eventBus;
    @Getter
    private CommandManager commandManager;
    @Getter
    private ConfigManager configManager;
    @Getter
    private FriendManager friendManager;
    @Getter
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    @Getter
    private RotationComponent rotationComponent;
    @Getter
    private PullBackComponent pullBackComponent;
    @Getter
    private final Animation guiAnimation = new Animation();
    public Rave(){
        Instance = this;
    }
    public void init(){
        logger.info("Loading Rave...");
        AdditionalTitle = "我们都在期待奇迹的出现";
        updateTitle("Loading");
        FontManager.init();
        moduleManager = new ModuleManager();
        eventBus = new EventBus();
        commandManager = new CommandManager();
        configManager = new ConfigManager();
        friendManager = new FriendManager();
        rotationComponent = new RotationComponent();
        pullBackComponent = new PullBackComponent();
        moduleManager.init();
        commandManager.init();
        friendManager.init();
        eventBus.init();
        configManager.init();
        eventBus.registerListener(moduleManager);
        eventBus.registerListener(rotationComponent);
        eventBus.registerListener(pullBackComponent);
        eventBus.registerListeners();
        eventBus.sortEvent();
        updateTitle("");
    }
    public void updateTitle(String add){
        Display.setTitle("Minecraft 1.8.9 - " + CLIENT_NAME + " " + CLIENT_VERSION + " -"+AdditionalTitle+" " + add);
    }
}
