package al.nya.reflect.features.command.commands;

import al.nya.reflect.config.UserConfigManager;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.utils.client.ClientUtil;

import java.util.List;

public class Config extends Command {

    public Config() {
        super("Config Command", "config");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        if (args[0].equalsIgnoreCase("list")) {
            List<String> configs = UserConfigManager.getAvailableConfigs();
            ClientUtil.printChat(configs.size() + " Config(s):");
            StringBuilder sb = new StringBuilder();
            for (String config : configs) {
                sb.append(config.equals(UserConfigManager.using) ? "\u00a75" : "\u00a7a").append(config).append("\u00a77,");
            }
            ClientUtil.printChat(sb.toString());
        }
        if (args[0].equalsIgnoreCase("save")) {
            if (args.length != 2) {
                ClientUtil.printChat(".config save [name] -save config");
            } else {
                UserConfigManager.save(args[1]);
            }
        }
        if (args[0].equalsIgnoreCase("load")) {
            if (args[1] != null) {
                UserConfigManager.load(args[1]);
            } else {
                ClientUtil.printChat(".config save [name] -load config");
            }
        }
    }

    @Override
    public String[] getHelpMessage() {
        return new String[]{".config list -List all available configs",
        ".config save [name] -Save config",
        ".config load [name] -Load config"};
    }
}
