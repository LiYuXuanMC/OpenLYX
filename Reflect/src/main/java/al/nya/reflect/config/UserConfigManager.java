package al.nya.reflect.config;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.FileUtil;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.data.ModuleConfig;
import al.nya.reflect.utils.data.UserConfig;
import al.nya.reflect.utils.data.ValueConfig;
import al.nya.reflect.value.Value;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserConfigManager {
    public static String using = null;
    public static File configDir = new File(Reflect.ReflectDir + "/configs");

    public static void init() {
        configDir.mkdir();
    }

    public static List<String> getAvailableConfigs() {
        List<String> available = new ArrayList<String>();
        for (File file : new File(Reflect.ReflectDir + "/configs").listFiles()) {
            if (getExtension(file).equals("cfg")) {
                available.add(file.getName().replace(".cfg", ""));
            }
        }
        return available;
    }

    public static void load(String name) {
        File file = new File(Reflect.ReflectDir + "/configs/" + name + ".cfg");
        if (file.exists()) {
            try {
                String cfg = new String(FileUtil.readFile(file));
                UserConfig config = new Gson().fromJson(cfg, UserConfig.class);
                ClientUtil.printChat("\u00a7aLoading config " + name + " created by " + config.creator);
                new Thread(() -> {
                    for (ModuleConfig module : config.modules) {
                        Module targetModule = ModuleManager.getModuleByName(module.moduleName);
                        if (targetModule != null) {
                            if (targetModule.isEnable() != module.enable)
                                targetModule.setEnableNoNotification(module.enable);
                            targetModule.setFavorite(module.favorite);
                            targetModule.setBinding(EnumKey.getKey(module.bind));
                            for (ValueConfig value : module.values) {
                                for (Value targetModuleValue : targetModule.getValues()) {
                                    if (targetModuleValue.getName().equals(value.name)) {
                                        targetModuleValue.load(value.value);
                                    }
                                }
                            }
                        } else {
                            ClientUtil.printChat(ClientUtil.Level.ERROR, "Parse error unknown module:" + module.moduleName);
                        }
                    }
                }).start();
                using = name;
            } catch (IOException e) {
                e.printStackTrace();
                ClientUtil.printChat(ClientUtil.Level.ERROR, "Cannot load config " + e.getMessage());
            }
        } else {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Cannot find config " + name);
        }
    }

    public static void save(String name) {
        File file = new File(Reflect.ReflectDir + "/configs/" + name + ".cfg");
        UserConfig config = new UserConfig(Reflect.USER.name);
        for (Module module : ModuleManager.getModules()) {
            ModuleConfig moduleConfig = new ModuleConfig(module.getName());
            moduleConfig.enable = module.isEnable();
            moduleConfig.bind = module.getBinding().getKeyCode();
            moduleConfig.favorite = module.isFavorite();
            for (Value value : module.getValues()) {
                ValueConfig valueConfig = new ValueConfig(value);
                moduleConfig.values.add(valueConfig);
            }
            config.modules.add(moduleConfig);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(new Gson().toJson(config).getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Cannot save config " + e.getMessage());
            return;
        }
        ClientUtil.printChat("Save config successfully ");
        using = name;
    }

    private static String getExtension(File file) {
        String fileName = file.getName();
        String fe = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            fe = fileName.substring(i + 1);
        }
        return fe;
    }
}
