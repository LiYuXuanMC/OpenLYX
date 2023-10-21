package al.nya.reflect.config.configs;

import al.nya.reflect.config.Configurable;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.utils.FileUtil;
import com.google.gson.JsonObject;

import java.io.File;

public class HudConfig extends Configurable {

    private final HUDObject hudObject;

    public HudConfig(File dir, HUDObject hudObject) {
        super(FileUtil.createJsonFile(dir, hudObject.getName()));
        this.hudObject = hudObject;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        this.getJsonObject().entrySet().forEach(entry -> {
            if (entry.getKey().equalsIgnoreCase("X")) {
                hudObject.setPosX(entry.getValue().getAsInt());
            }

            if (entry.getKey().equalsIgnoreCase("Y")) {
                hudObject.setPosY(entry.getValue().getAsInt());
            }

        });
    }

    @Override
    public void onSave() {
        JsonObject moduleJsonObject = new JsonObject();
        moduleJsonObject.addProperty("Name", hudObject.getName());
        moduleJsonObject.addProperty("X", hudObject.getPosX());
        moduleJsonObject.addProperty("Y", hudObject.getPosY());
        this.saveJsonObjectToFile(moduleJsonObject);
    }
}