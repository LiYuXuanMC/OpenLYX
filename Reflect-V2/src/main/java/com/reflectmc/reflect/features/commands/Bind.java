package com.reflectmc.reflect.features.commands;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.key.EnumKey;
import com.reflectmc.reflect.utils.ClientUtil;

public class Bind extends Command{
    public Bind() {
        super("Bind","bind");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 2){
            Module module = Reflect.getINSTANCE().getModuleManager().getModule(args[0]);
            if (module == null){
                module = Reflect.getINSTANCE().getModuleManager().getByDisplayName(args[0]);
                if (module == null){
                    ClientUtil.printChat(ClientUtil.Level.ERROR,"Unknown module: "+args[0]);
                    return true;
                }
            }
            EnumKey key = EnumKey.getKey(args[1]);
            if (key == null){
                ClientUtil.printChat(ClientUtil.Level.ERROR,"Unknown key: "+args[1]);
                return true;
            }
            module.setBind(key);
            ClientUtil.printChat(ClientUtil.Level.INFO,"Bind "+module.getName()+" to "+module.getBind().getDisplayName());
        }else {
            help();
        }
        return true;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Module]","[Key]"},"Bind module to a key");
    }
}
