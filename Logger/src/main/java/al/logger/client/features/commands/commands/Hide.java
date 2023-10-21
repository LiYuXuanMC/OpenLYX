package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.utils.ChatUtils;

public class Hide extends Command {
    public Hide(){
        super("Hide", "hide");
    }
    @Override
    public boolean trigger(String[] args) {
        if (args.length == 1) {
            if (Logger.getInstance().getModuleManager().getModule(args[0]).isHide()){
                Logger.getInstance().getModuleManager().getModule(args[0]).setHide(false);
                ChatUtils.message("Display Module:" + args[0]);
                return true;
            }else {
                Logger.getInstance().getModuleManager().getModule(args[0]).setHide(true);
                ChatUtils.message("Hide Module:" + args[0]);
                return true;
            }
        }

        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Module Name]"},"Hide Module");
    }
}
