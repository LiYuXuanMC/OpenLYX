package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;

public class Script extends Command {
    public Script() {
        super("Script","script");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 1){
            if(args[0].equalsIgnoreCase("search")){
                Logger.getInstance().getScriptManager().search();
            }
            return true;
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("load")){
                Logger.getInstance().getScriptManager().loadScript(args[1]);
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
