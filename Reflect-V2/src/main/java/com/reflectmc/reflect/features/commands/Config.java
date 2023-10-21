package com.reflectmc.reflect.features.commands;

import com.reflectmc.reflect.Reflect;

public class Config extends Command{
    public Config() {
        super("Config","config",new String[]{"cfg"});
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("list")){
                Reflect.getINSTANCE().getConfigManager().listConfigs();
                return true;
            }
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("save")){
                Reflect.getINSTANCE().getConfigManager().saveConfig(args[1]);
                return true;
            }
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"list"},"List all configs");
        printHelp(new String[]{"load","[Config]"},"Load a config");
        printHelp(new String[]{"save","[Config]"},"Save a config");
    }
}
