package al.nya.reflect.utils.data;

import al.nya.reflect.key.EnumKey;

import java.util.ArrayList;
import java.util.List;

public class ModuleConfig {
    public String moduleName;
    public boolean favorite;
    public boolean enable = false;
    public List<ValueConfig> values = new ArrayList<ValueConfig>();
    public int bind = EnumKey.None.getKeyCode();
    public ModuleConfig(String moduleName){
        this.moduleName = moduleName;
    }
}
