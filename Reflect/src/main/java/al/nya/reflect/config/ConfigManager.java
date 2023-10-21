package al.nya.reflect.config;

import al.nya.reflect.Reflect;
import al.nya.reflect.config.configs.HudConfig;
import al.nya.reflect.config.configs.ModuleConfig;
import al.nya.reflect.config.configs.WaypointsConfig;
import al.nya.reflect.events.events.client.EventLoadConfig;
import al.nya.reflect.events.events.client.EventSaveConfig;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Visual.HUD;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    public static final String CONFIG_PATH = Reflect.ReflectDir;
    private final List<Configurable> configurableList = new ArrayList<>();
    private File configDir;
    private File moduleConfigDir;
    private File hudConfigDir;
    @Setter
    private boolean firstLaunch = false;

    public ConfigManager() {
        this.generateDirectories();
    }

    private void generateDirectories() {
        this.configDir = new File(CONFIG_PATH);
        if (!this.configDir.exists()) {
            this.configDir.mkdirs();
        }

        this.moduleConfigDir = new File(CONFIG_PATH + "/Modules" + "/");
        if (!this.moduleConfigDir.exists()) {
            this.setFirstLaunch(true);
            this.moduleConfigDir.mkdirs();
        }

        this.hudConfigDir = new File(CONFIG_PATH + "/Hud" + "/");
        if (!this.hudConfigDir.exists()) {
            this.hudConfigDir.mkdirs();
        }

    }

    public void init() {
        /*ModuleManager.getModules().forEach(module -> {
            this.configurableList.add(new ModuleConfig(this.moduleConfigDir, module));
        });

         */

        HUD.hudObjects.forEach(hudObject -> {
            this.configurableList.add(new HudConfig(this.hudConfigDir, hudObject));
        });

        this.configurableList.add(new WaypointsConfig(configDir));


        if (this.firstLaunch) {
            this.saveAll();
        } else {
            this.loadAll();
        }
    }

    public void save(Class configurableClassType) {
        for (Configurable cfg : configurableList) {
            if (cfg.getClass().isAssignableFrom(configurableClassType)) {
                cfg.onSave();
            }
        }

        Reflect.Instance.eventBus.callEvent(new EventSaveConfig());
    }

    public void saveAll() {
        for (Configurable cfg : configurableList) {
            cfg.onSave();
        }
        Reflect.Instance.eventBus.callEvent(new EventSaveConfig());
    }

    public void load(Class configurableClassType) {
        for (Configurable cfg : configurableList) {
            if (cfg.getClass().isAssignableFrom(configurableClassType)) {
                cfg.onLoad();
            }
        }
        Reflect.Instance.eventBus.callEvent(new EventLoadConfig());
    }

    public void loadAll() {
        for (Configurable cfg : configurableList) {
            cfg.onLoad();
        }
        Reflect.Instance.eventBus.callEvent(new EventLoadConfig());
    }
}
