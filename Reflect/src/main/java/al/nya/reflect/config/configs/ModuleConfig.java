package al.nya.reflect.config.configs;

import al.nya.reflect.config.Configurable;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.FileUtil;
import al.nya.reflect.value.Value;
import com.google.gson.JsonObject;

import java.io.File;

public class ModuleConfig extends Configurable {

    private final Module module;

    public ModuleConfig(File dir, Module module) {
        super(FileUtil.createJsonFile(dir, module.getName()));
        this.module = module;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        this.getJsonObject().entrySet().forEach(entry -> {
            if (entry.getKey().equalsIgnoreCase("Keybind")) {
                module.setBinding(EnumKey.getKey(entry.getValue().getAsInt()));
            }

            if (entry.getKey().equalsIgnoreCase("Enabled")) {
                if (entry.getValue().getAsBoolean()) {
                    module.setEnableNoNotification(true);
                }
            }

            for (Value val : module.getValues()) {
                if (val.getName().equals(entry.getKey())) {
                    val.load(entry.getValue().getAsString());
                }
            }
        });
    }

    @Override
    public void onSave() {
        JsonObject moduleJsonObject = new JsonObject();
        moduleJsonObject.addProperty("Name", module.getName());
        moduleJsonObject.addProperty("Keybind", module.getBinding().getKeyCode());
        moduleJsonObject.addProperty("Enabled", module.isEnable());
        for (Value<?> value : module.getValues()) {
            moduleJsonObject.addProperty(value.getName(), value.getValue().toString());
        }
        this.saveJsonObjectToFile(moduleJsonObject);
    }
}