package al.nya.reflect.socket;

public class KeyCode {
    private String key;
    private int type;
    public KeyCode(String key, int type){
        this.key = key;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getKey() {
        return key;
    }
}
