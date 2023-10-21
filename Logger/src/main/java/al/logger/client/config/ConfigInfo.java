package al.logger.client.config;

public class ConfigInfo {
    public String name;
    public String keycode;
    public String content;
    public String passowrd;
    public ConfigInfo(String name,String keycode){
        this.name = name;
        this.keycode = keycode;
        this.passowrd = "";
        this.content = "undefined";
    }
    public ConfigInfo(String name,String keycode,String passowrd){
        this.name = name;
        this.keycode = keycode;
        this.passowrd = passowrd;
        this.content = "undefined";
    }
}
