package al.nya.reflect.features.command.commands;

import al.nya.reflect.features.command.Command;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;

public class Toggle extends Command {
    public Toggle() {
        super("Toggles Module", "t");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        for (Module module : ModuleManager.getModules()) {
            if (module.getName().equalsIgnoreCase(args[0])) {
                module.setEnable(!module.isEnable());
            }
        }
        super.onMessage(msg, args);
    }
}
