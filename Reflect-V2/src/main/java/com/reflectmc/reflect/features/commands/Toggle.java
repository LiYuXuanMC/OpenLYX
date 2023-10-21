package com.reflectmc.reflect.features.commands;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.modules.Module;

public class Toggle extends Command{
    public Toggle() {
        super("Toggle","toggle",new String[]{"t"});
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 1){
            String name = args[0];
            Module module = Reflect.getINSTANCE().getModuleManager().getModule(name);
            if (module != null){
                module.setEnable(!module.isEnable());
                return true;
            }
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Module]"},"Toggle a module");
    }
}
