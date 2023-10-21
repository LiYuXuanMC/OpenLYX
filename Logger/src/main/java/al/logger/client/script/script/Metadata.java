package al.logger.client.script.script;


import al.logger.client.script.ScriptManager;
import al.logger.libs.org.luaj.vm2.Globals;

import java.util.Properties;

public class Metadata {
    private String name = "Unknown";
    private String author = "Unknown";
    private String version = "Unknown";
    private int apiVersion = ScriptManager.apiVersion;
    private String mainScript;
    private boolean scriptIsolation = false;
    private Globals globals = null;
    public Metadata(){

    }

    public void bindGlobals(Globals globals) {
        this.globals = globals;
    }

    public Globals getGlobals() {
        return globals;
    }

    public String getName() {
        return name;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public String getAuthor() {
        return author;
    }

    public String getMainScript() {
        return mainScript;
    }

    public String getVersion() {
        return version;
    }

    public boolean isScriptIsolation() {
        return scriptIsolation;
    }

    public void loadMetadata(Properties properties){
        if (properties.containsKey("author")){
            author = properties.getProperty("author");
        }
        if (properties.containsKey("version")){
            version = properties.getProperty("version");
        }
        if (properties.containsKey("api_version")){
            apiVersion = Integer.parseInt(properties.getProperty("api_version"));
        }
        if (properties.containsKey("main_script")){
            mainScript = properties.getProperty("main_script");
        }
        if (properties.containsKey("name")){
            name = properties.getProperty("name");
        }
        if (properties.containsKey("script_isolation")){
            scriptIsolation = Boolean.parseBoolean(properties.getProperty("script_isolation"));
        }
    }
}
