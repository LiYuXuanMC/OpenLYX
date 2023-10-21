package com.reflectmc.reflect.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.events.client.EventConfigSave;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.SubModule;
import com.reflectmc.reflect.features.values.Value;
import com.reflectmc.reflect.utils.ClientUtil;
import com.reflectmc.reflect.utils.FileUtil;
import lombok.Getter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private static final String REFLECT_DIR_NAME = "Reflect";
    @Getter
    private File path;
    public ConfigManager(){

    }
    public void init(){
        path = new File(System.getProperty("user.home") + "/AppData/Roaming/" + REFLECT_DIR_NAME);
        new File(path,"configs").mkdir();
    }
    public void listConfigs(){
        File configDir = new File(path,"configs");
        List<File> configs = new ArrayList<>();
        for (File file : configDir.listFiles()) {
            if (file.isFile()){
                if (getFileExtension(file).equalsIgnoreCase("cfg")){
                    configs.add(file);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(configs.size()).append(" config(s):\n");
        for (File config : configs) {
            sb.append("§r§a").append(config.getName().replace("."+getFileExtension(config),"")).append("§r,");
        }
        ClientUtil.printChat(sb.toString());
    }
    public void saveConfig(String name){
        File configDir = new File(path,"configs");
        JsonObject config = new JsonObject();
        config.addProperty("author", Reflect.getINSTANCE().getUserData().getUsername());
        JsonObject version = new JsonObject();
        version.addProperty("clientVer",Reflect.CLIENT_VERSION);
        version.addProperty("clientSubVer",Reflect.CLIENT_SUB_VERSION);
        config.add("version",version);
        JsonObject modules = new JsonObject();
        for (Module m : Reflect.getINSTANCE().getModuleManager().getModules()) {
            modules.add(m.getName(),getModuleConfig(m));
        }
        config.add("modules",modules);
        JsonObject custom = new JsonObject();
        EventConfigSave configSave = new EventConfigSave();
        Reflect.getINSTANCE().getEventBus().callEvent(configSave);
        configSave.getObjectMap().forEach(custom::add);
        config.add("custom",custom);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileUtil.writeFile(new File(configDir,name+".cfg"),gson.toJson(config).getBytes(StandardCharsets.UTF_8));
    }
    private JsonObject getModuleConfig(Module module){
        JsonObject object = new JsonObject();
        object.addProperty("enable",module.isEnable());
        object.addProperty("key",module.getBind().getKeyCode());
        object.addProperty("favorite",module.isFavorite());
        JsonObject values = new JsonObject();
        for (Value value : module.getValues()) {
            values.addProperty(value.getName(),value.save());
        }
        object.add("values",values);
        JsonObject subModules = new JsonObject();
        for (SubModule subModule : module.getSubModules()) {
            subModules.add(subModule.getName(),getModuleConfig(subModule));
        }
        object.add("subModules",subModules);
        return object;
    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
