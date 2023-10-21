package al.logger.client.features.commands.commands;


import al.logger.client.Logger;
import al.logger.client.features.commands.Command;

public class Help extends Command {
    public Help() {
        super("Help","help",new String[]{"h"});
    }

    @Override
    public boolean trigger(String[] args) {
        //ClientUtil.printChat(ClientUtil.Level.INFO,"-Help");
        for (Command cd: Logger.getInstance().commandManager.getCommands()) {
            cd.help();
        }
        return true;
    }

    @Override
    public void help() {
        printHelp(new String[]{},"Get help");
    }
}
