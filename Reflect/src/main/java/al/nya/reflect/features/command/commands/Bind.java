package al.nya.reflect.features.command.commands;

import al.nya.reflect.features.command.Command;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.utils.client.ClientUtil;

public class Bind extends Command {
    public Bind() {
        super("Bind Moudle", "bind");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        if (args.length != 2){
            ClientUtil.printChat(".bind [Module] [Key] -Bind module to key");
            return;
        }
        Module module = ModuleManager.getModuleByName(args[0]);
        if (module == null) {
            ClientUtil.printChat("Cannot find module:"+args[0]);
            return;
        }
        EnumKey key = EnumKey.getKey(args[1]);
        if (key == null){
            ClientUtil.printChat("Cannot find key:"+args[1]);
            return;
        }
        module.setBinding(key);
        ClientUtil.printChat("Bind module "+module.getName()+" to "+key.getDisplayName());
    }

    @Override
    public String[] getHelpMessage() {
        return new String[]{".bind [Module] [Key] -Bind module to key"};
    }
}
