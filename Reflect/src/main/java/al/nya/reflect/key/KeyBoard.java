package al.nya.reflect.key;

import al.nya.reflect.Reflect;
import al.nya.reflect.config.configs.HudConfig;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;

public class KeyBoard {
    public static void onKey(EnumKey key){
        if (key == null) return;
        if (key == EnumKey.ESC) Reflect.Instance.configManager.save(HudConfig.class);
        for (Module module : ModuleManager.getModules()) {
            if (module.getBinding() == key){
                module.setEnable(!module.isEnable());
            }
        }
        //KeyManager.onKey(key.getKeyCode());
    }
}
