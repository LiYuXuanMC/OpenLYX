package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;

public class Toggle extends Command {
    public Toggle() {
        super("Toggle","toggle",new String[]{"t"});
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 1){
            Module module = Logger.getInstance().getModuleManager().getModule(args[0]);
            if (module == null){
                module = Logger.getInstance().getModuleManager().getByDisplayName(args[0]);
                if (module == null){
                    ChatUtils.error("Unknown module: "+args[0]);
                    //ClientUtil.printChat(ClientUtil.Level.ERROR,"Unknown module: "+args[0]);
                    return true;
                }
            }
            module.toggle();
            return true;
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Module]"},"Toggle a module");
    }
}
