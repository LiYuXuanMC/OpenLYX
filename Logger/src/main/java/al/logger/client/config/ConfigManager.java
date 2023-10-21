package al.logger.client.config;

import al.logger.client.Logger;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.ChatUtils;
import al.logger.client.value.bases.Value;
import by.radioegor146.nativeobfuscator.Native;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Native
public class ConfigManager {
    public List<ConfigInfo> MyConfigList = new ArrayList<ConfigInfo>();
    public List<ConfigInfo> PublicConfigList = new ArrayList<ConfigInfo>();
    public String configURL = "http://111.180.205.223:22218";
    @Getter
    private File path;
    @Getter
    private boolean isInitialized = false;
    @Getter
    @Setter
    private ConfigInfo currentConfigName;

    public ConfigManager() {


    }

    public void init() {
        //Reset
        PublicConfigList.clear();
        MyConfigList.clear();
        //Mine
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(configURL + "/config/list" +
                    "?userid=" + Logger.getInstance().getLoggerUser().getUserid() +
                    "&idun=" + Logger.getInstance().getLoggerUser().getIdunToken()
            ).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            in.close();
            String ConfigList = response.toString();
            String[] ConfigsInfo = ConfigList.split("\n");
            for (String cfg : ConfigsInfo) {
                String[] cfgInfo = cfg.split("   ");
                if (cfgInfo.length == 2) {
                    ConfigInfo cInfo = new ConfigInfo(cfgInfo[0], cfgInfo[1]);
                    MyConfigList.add(cInfo);
                    if (Logger.getInstance().isMySelfObf()){
                        System.out.println("[ConfigManager]Get My Config: " + cInfo.name);
                    }
                }
                if (cfgInfo.length == 3) {
                    ConfigInfo cInfo = new ConfigInfo(cfgInfo[0], cfgInfo[1], cfgInfo[2]);
                    MyConfigList.add(cInfo);
                    if (Logger.getInstance().isMySelfObf()){
                        System.out.println("[ConfigManager]Get My Private Config: " + cInfo.name);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ALL(Except Private)
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(configURL + "/config/list").openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            in.close();
            String ConfigList = response.toString();
            String[] ConfigsInfo = ConfigList.split("\n");
            for (String cfg : ConfigsInfo) {
                String[] cfgInfo = cfg.split("   ");
                if (cfgInfo.length == 2) {
                    ConfigInfo cInfo = new ConfigInfo(cfgInfo[0], cfgInfo[1]);
                    PublicConfigList.add(cInfo);
                    if (Logger.getInstance().isMySelfObf()){
                        System.out.println("[ConfigManager]Find Public Config: " + cInfo.name);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listConfigs(boolean onlyMine) {
        this.init();
        if (onlyMine) {
            for (ConfigInfo cfg : MyConfigList) {
                ChatUtils.message("[Mine]" + cfg.name + "   " + cfg.keycode + "   " + cfg.passowrd);
            }
        } else {
            for (ConfigInfo cfg : PublicConfigList) {
                ChatUtils.message("[Public]" + cfg.name + "   " + cfg.keycode);
            }
        }
    }

    public void delConfig(String keycode) {
        this.init();
        try {

            HttpURLConnection con = (HttpURLConnection) new URL(configURL + "/config?mode=del" +
                    "&userid=" + Logger.getInstance().getLoggerUser().getUserid() +
                    "&idun=" + Logger.getInstance().getLoggerUser().getIdunToken() +
                    "&password=" + Logger.getInstance().getLoggerUser().getPassword() +
                    "&keycode=" + keycode
            ).openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            in.close();
            String resp = response.toString();
            System.out.println(resp);
            ChatUtils.message("Server Response: " + resp.replaceAll("\n", ""));
            if (resp.contains("successfully")) {
                ChatUtils.message("Deleted Config by [" + Logger.getInstance().getLoggerUser().getUsername() + "] for Logger#" + Logger.getInstance().currentVer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveConfig(String name, boolean openness, String password) {
        this.init();
        try {
            JsonObject config = new JsonObject();
            config.addProperty("author", Logger.getInstance().getLoggerUser().getUsername());
            config.addProperty("clientVer", Logger.getInstance().currentVer);
            JsonObject modules = new JsonObject();
            for (Module m : Logger.getInstance().getModuleManager().getModules()) {
                modules.add(m.getName(), getModuleConfig(m));
            }
            JsonObject elements = new JsonObject();
            for (String key : Logger.getInstance().guiManager.GuiElementMap.keySet()) {
                JsonObject syJsonObject = new JsonObject();
                ComponentBase componentBase = Logger.getInstance().guiManager.GuiElementMap.get(key);
                syJsonObject.addProperty("positionX", componentBase.getPosition().getX());
                syJsonObject.addProperty("positionY", componentBase.getPosition().getY());
                syJsonObject.addProperty("positionWidth", componentBase.getPosition().getWidth());
                syJsonObject.addProperty("positionHeight", componentBase.getPosition().getHeight());
                elements.add(key, syJsonObject);
            }
            config.add("elements", elements);
            config.add("modules", modules);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            HttpURLConnection con = (HttpURLConnection) new URL(configURL + "/config?mode=save" +
                    "&userid=" + Logger.getInstance().getLoggerUser().getUserid() +
                    "&idun=" + Logger.getInstance().getLoggerUser().getIdunToken() +
                    "&title=" + name +
                    "&openness=" + (openness ? "pr" : "pb") +
                    "&password=" + password
            ).openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.connect();
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(gson.toJson(config).getBytes(StandardCharsets.UTF_8));

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            in.close();
            String resp = response.toString();
            System.out.println(resp);
            ChatUtils.message("Server Response: " + resp.replaceAll("\n", ""));
            if (resp.contains("successfully")) {
                ChatUtils.message("Saved Config by [" + Logger.getInstance().getLoggerUser().getUsername() + "] for Logger#" + Logger.getInstance().currentVer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(String ckeycode, boolean isMine) {
        try {

            this.init();

            String configContent = "";

            for (ConfigInfo C : isMine ? this.MyConfigList : this.PublicConfigList) {
                if (C.keycode.equals(ckeycode)) {
                    currentConfigName = C;
                    break;
                }
            }
            if (currentConfigName == null) {
                ChatUtils.error("Config Not Found");
                return;
            }

            HttpURLConnection con = (HttpURLConnection) new URL(configURL + "/config?keycode=" + currentConfigName.keycode + "&password=" + currentConfigName.passowrd).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            in.close();
            configContent = response.toString();

            JsonObject jsonObject = new Gson().fromJson(configContent, JsonObject.class);
            String cfgAuthor = jsonObject.get("author").getAsString();
            String cfgClientVer = jsonObject.get("clientVer").getAsString();

            JsonObject modules = jsonObject.getAsJsonObject("modules");
            for (Module module : Logger.getInstance().moduleManager.getModules()) {

                if (modules.has(module.getName())) {
                    JsonObject modjs = modules.getAsJsonObject(module.getName());
                    boolean enabled = modjs.get("enable").getAsBoolean();
                    int keycode = modjs.get("key").getAsInt();
                    if (modjs.has("values")) {
                        JsonObject values = modjs.getAsJsonObject("values");
                        for (Value<?> value : module.getValues()) {
                            if (values.has(value.getName())) {
                                String vjs = values.get(value.getName()).getAsString();
                                value.load(vjs);
                            }
                        }
                    }
                    if (enabled != module.isEnable()) {
                        module.toggle();
                    }
                    module.setKeyCode(keycode);
                }
            }
            JsonObject elements = jsonObject.getAsJsonObject("elements");
            for (String value : Logger.getInstance().guiManager.GuiElementMap.keySet()) {
                if (elements.has(value)) {
                    JsonObject element = elements.getAsJsonObject(value);
                    ComponentBase componentBase = Logger.getInstance().guiManager.GuiElementMap.get(value);
                    componentBase.getPosition().setX(element.get("positionX").getAsFloat());
                    componentBase.getPosition().setY(element.get("positionY").getAsFloat());
                    componentBase.getPosition().setWidth(element.get("positionWidth").getAsFloat());
                    componentBase.getPosition().setHeight(element.get("positionHeight").getAsFloat());
                }
            }
            ChatUtils.message("Loaded Config from [" + cfgAuthor + "] for Logger#" + cfgClientVer);
        } catch (Throwable e) {
            ChatUtils.error(e.getMessage());
        }
    }

    private JsonObject getModuleConfig(Module module) {
        JsonObject object = new JsonObject();
        object.addProperty("enable", module.isEnable());
        object.addProperty("key", module.getKeyCode());
        JsonObject values = new JsonObject();
        for (Value value : module.getValues()) {
            values.addProperty(value.getName(), value.save());
        }
        object.add("values", values);
        return object;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public static boolean writeFile(File f, byte[] bytes) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] readFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        return bytes;
    }
}
