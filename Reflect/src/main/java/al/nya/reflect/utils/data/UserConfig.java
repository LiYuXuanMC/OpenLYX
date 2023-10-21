package al.nya.reflect.utils.data;

import java.util.ArrayList;
import java.util.List;

public class UserConfig {
    public String creator;
    public String desc = "";
    public List<ModuleConfig> modules = new ArrayList<ModuleConfig>();

    public UserConfig(String creator) {
        this.creator = creator;
    }
}
