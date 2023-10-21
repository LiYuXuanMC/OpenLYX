package al.nya.reflect.features.command.commands;

import al.nya.reflect.features.command.Command;
import al.nya.reflect.features.command.CommandManager;
import al.nya.reflect.utils.client.ClientUtil;

public class Help extends Command {
    public Help() {
        super("Command Helper","help");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        for (Command cmd : CommandManager.getInstance().commands) {
            ClientUtil.printChat(cmd.getName() + "-----");
            if (cmd.getHelpMessage() == null) ClientUtil.printChat("No Help Message Found!");
            else {
                for (String str : cmd.getHelpMessage()) {
                    ClientUtil.printChat(str);
                }
            }
        }
        super.onMessage(msg, args);
    }

    @Override
    public String[] getHelpMessage() {
        return new String[]{".help 获取帮助"};
    }
}
