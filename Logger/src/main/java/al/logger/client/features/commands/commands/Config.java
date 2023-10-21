package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;

public class Config extends Command {
    public Config() {
        super("Config","config",new String[]{"cfg"});
    }

    @Override
    public boolean trigger(String[] args) {
        if (args[0].equalsIgnoreCase("list") && args.length == 1){
            Logger.getInstance().configManager.listConfigs(true);
            return true;
        }

        if (args[0].equalsIgnoreCase("load") && args.length == 3){
            if (args[2].equalsIgnoreCase("false")){
                Logger.getInstance().configManager.loadConfig(args[1],false);
                return true;
            }else if (args[2].equalsIgnoreCase("true")){
                Logger.getInstance().configManager.loadConfig(args[1],true);
                return true;
            }
            return false;
        }
        if (args[0].equalsIgnoreCase("save") && args.length == 3){
            if (args[2].equalsIgnoreCase("false")){
                Logger.getInstance().configManager.saveConfig(args[1],false,"");
                return true;
            }else if (args[2].equalsIgnoreCase("true")){
                Logger.getInstance().configManager.saveConfig(args[1],true,"");
                return true;
            }
            return false;
        }
        if (args[0].equalsIgnoreCase("save") && args.length == 4){
            if (args[2].equalsIgnoreCase("false")){
                Logger.getInstance().configManager.saveConfig(args[1],false,args[3]);
                return true;
            }else if (args[2].equalsIgnoreCase("true")){
                Logger.getInstance().configManager.saveConfig(args[1],true,args[3]);
                return true;
            }
            return false;
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("save")){
                Logger.getInstance().configManager.saveConfig(args[1],false,"");
                return true;
            }
            if (args[0].equalsIgnoreCase("load")){
                Logger.getInstance().configManager.loadConfig(args[1],true);
                return true;
            }
            if (args[0].equalsIgnoreCase("del")){
                Logger.getInstance().configManager.delConfig(args[1]);
                return true;
            }
            if (args[0].equalsIgnoreCase("list")){
                if (args[1].equalsIgnoreCase("false")){
                    Logger.getInstance().configManager.listConfigs(false);
                    return true;
                }else if (args[1].equalsIgnoreCase("true")){
                    Logger.getInstance().configManager.listConfigs(true);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"load","[onlyMine]"},"List all configs");
        printHelp(new String[]{"load","[Config]","[onlyMine]"},"Load a config");
        printHelp(new String[]{"save","[Config]","[isPublic]","[Password]"},"Save a config");
        printHelp(new String[]{"del","[Config_keycode]"},"Del a config");
    }
}
