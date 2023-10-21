package al.logger.client.script;


import al.logger.client.Logger;
import al.logger.client.script.api.APIManager;
import al.logger.client.script.script.Metadata;
import al.logger.client.script.script.ScriptModule;
import al.logger.libs.org.luaj.vm2.Globals;
import al.logger.libs.org.luaj.vm2.LuaValue;
import al.logger.libs.org.luaj.vm2.lib.jse.JsePlatform;
import al.logger.libs.org.luaj.vm2.luajc.LuaJC;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ScriptManager {
    private final File scriptDir;
    public static final int apiVersion = 20230808;
    private Globals publicGlobals;

    @Getter
    public ArrayList<String> funcNames = new ArrayList<>();

    public ScriptManager(File scriptDir){
        this.scriptDir = scriptDir;
        this.publicGlobals = JsePlatform.standardGlobals();
        LuaJC.install(publicGlobals);
        APIManager.loadAPI(publicGlobals);
        if (!scriptDir.exists()){
            scriptDir.mkdirs();
        }
    }

    public void initScriptModules(){
        funcNames.add("onEnable");
        funcNames.add("onDisable");
        funcNames.add("onUpdate");//EventLivingUpdate
        funcNames.add("onRender2D");
        funcNames.add("onRender3D");
    }
    public void reloadPublicGlobals(){
        publicGlobals = JsePlatform.standardGlobals();
        LuaJC.install(publicGlobals);
        APIManager.loadAPI(publicGlobals);
    }

    public void loadScript(String name){
        for (Metadata metadata : scripts.keySet()) {
            if (metadata.getName().equals(name)){
                loadScript(metadata);
                return;
            }
        }
    }
    private Map<Metadata,Map<String,byte[]>> scripts = new ConcurrentHashMap<>();
    public void loadModule(String name,String metaName){
        for (Metadata metadata : scripts.keySet()) {
            if (metadata.getName().equals(metaName)){
                Globals globals = metadata.getGlobals();
                for (Map.Entry<String, byte[]> entry : scripts.get(metadata).entrySet()) {
                    if(entry.getKey().equals(name+".lua")) {
                        LuaValue luaValue = globals.load(new String(entry.getValue()), entry.getKey());
                        luaValue.call();
                        LuaValue moduleName = globals.get("name");
                        LuaValue moduleCategory = globals.get("category");
                        ScriptModule scriptModule = new ScriptModule(moduleName.tojstring(),moduleCategory.tojstring(),globals);
                        Logger.getInstance().moduleManager.addPluginModule(scriptModule);
                    }
                }
                return;
            }
        }

    }

    public void loadScript(Metadata metadata){
        System.out.println("Loading script "+metadata.getName());
        Globals globals;
        if (metadata.isScriptIsolation()){
            globals = JsePlatform.standardGlobals();
            LuaJC.install(globals);
            APIManager.loadAPI(globals);
        }else {
            globals = publicGlobals;
        }
        LuaValue loader = null;
        for (Map.Entry<String, byte[]> entry : scripts.get(metadata).entrySet()) {
            if (entry.getKey().equals(metadata.getMainScript())){
                loader = globals.load(new String(entry.getValue()),entry.getKey());
            }
            if (entry.getKey().endsWith(".bin")){
                globals.load(new String(entry.getValue()),entry.getKey());
            }
        }
        metadata.bindGlobals(globals);
        if (loader != null){
            loader.call();
        }else {
            System.out.println("Loader script not found!");
        }
    }
    public void search(){
        for (File file : scriptDir.listFiles()) {
            if (file.getName().endsWith(".zip")){
                Map<String,byte[]> files = readZip(file);
                Metadata metadata = getMetadata(files);
                if (metadata != null){
                    if (metadata.getApiVersion() <= apiVersion){
                        if (metadata.getMainScript() != null){
                            System.out.println("Found script: " + metadata.getName());
                            scripts.put(metadata,files);
                        }
                    }
                }
            }
        }
    }
    public Metadata getMetadata(Map<String,byte[]> files){
        Metadata metadata = new Metadata();
        if (files.containsKey("script.properties")){
            Properties properties = new Properties();
            try {
                properties.load(new StringReader(new String(files.get("script.properties"))));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            metadata.loadMetadata(properties);
            return metadata;
        }else {
            return null;
        }
    }
    public Map<String,byte[]> readZip(File file){
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
            Map<String,byte[]> files = new ConcurrentHashMap<>();
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory())
                    continue;
                int len;
                byte[] buffer = new byte[2048];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = zis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                files.put(entry.getName(),bos.toByteArray());
            }
            return files;
        } catch (IOException e) {
            return null;
        }
    }
}
