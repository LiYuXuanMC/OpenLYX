package al.logger.client.features.commands.commands;

import al.logger.client.ExceptionHandler;
import al.logger.client.features.commands.Command;
import al.logger.client.utils.ChatUtils;

public class Debug extends Command {
    private boolean debug = false;
    public Debug() {
        super("Debug","debug");
    }

    @Override
    public boolean trigger(String[] args) {


        debug = !debug;
        ExceptionHandler.outPut = debug;
        ChatUtils.warning("Debug mode is now " + (debug ? "enabled" : "disabled"));
        return true;
    }

    @Override
    public void help() {

    }
}
