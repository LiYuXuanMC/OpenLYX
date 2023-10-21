package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;

public class SelfDestruct extends Command {
    public SelfDestruct() {
        super("SelfDestruct", "selfdestruct");
    }

    @Override
    public boolean trigger(String[] args) {
        //See you next time
        Logger.getInstance().unload();
        return true;
    }

    @Override
    public void help() {
        printHelp(new String[]{},"Unload Logger");
    }
}
