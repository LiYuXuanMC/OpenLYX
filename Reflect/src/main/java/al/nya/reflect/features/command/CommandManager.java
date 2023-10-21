package al.nya.reflect.features.command;

import al.nya.reflect.features.command.commands.*;
import al.nya.reflect.utils.client.ClientUtil;

import java.util.ArrayList;

public class CommandManager {

    private static CommandManager Instance;
    public ArrayList<Command> commands = new ArrayList<Command>();
    private final char prefix = 46;
    public CommandManager(){
        registerCommand(new Help());
        registerCommand(new Bind());
        registerCommand(new Config());
        registerCommand(new Reconnect());
        registerCommand(new WayPoints());
        registerCommand(new Toggle());
        registerCommand(new OpenDir());
        registerCommand(new Play());
        registerCommand(new Teleport());
        registerCommand(new Friend());
        registerCommand(new FakeID());
        Instance = this;
    }

    public static CommandManager getInstance() {
        if (Instance != null) return Instance;
        return Instance = new CommandManager();
    }
    private void registerCommand(Command cmd) {
        commands.add(cmd);
    }

    public final boolean onMessage(String str) {
        if (str.startsWith(Character.toString(this.prefix))) {
            execCMD(str);
            return false;
        }
        return true;
    }
    private void execCMD(String str) {
        String readString = str.trim().substring(Character.toString(this.prefix).length()).trim();
        boolean hasBeenExecuted = false;
        boolean hasArgs = readString.trim().contains(" ");
        String commandName = hasArgs ? readString.split(" ")[0] : readString.trim();
        String[] args = hasArgs ? readString.substring(commandName.length()).trim().split(" ") : new String[0];

        for (Command command : commands) {
            if (command.getPrefix().trim().equalsIgnoreCase(commandName.trim())) {
                try {
                    command.onMessage(readString, args);
                } catch (Exception e) {
                    ClientUtil.printChat(ClientUtil.Level.ERROR, "用法错误!");
                }
                hasBeenExecuted = true;
                break;
            }
        }

        if (!hasBeenExecuted) {
            ClientUtil.printChat("Command Not Found!");
        }
    }
}
