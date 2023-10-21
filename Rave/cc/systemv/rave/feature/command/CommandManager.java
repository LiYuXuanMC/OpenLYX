package cc.systemv.rave.feature.command;

import cc.systemv.rave.utils.IManager;
import cc.systemv.rave.utils.client.ChatUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager extends IManager {
    private final List<Command> commands = new ArrayList<>();
    public CommandManager() {

    }
    public void init(){

    }
    public boolean executeCommand(String command) {
        if (command.startsWith(".")){
            runCommand(command);
            return true;
        }
        return false;
    }
    private void runCommand(String message){
        List<String> args = new ArrayList<>(Arrays.asList(message.replaceFirst(".", "").split(" ")));

        try{
            for (Command command : commands) {
                if (command.getCommand().equalsIgnoreCase(args.get(0))){
                    args.remove(0);
                    if(!command.trigger(args.toArray(new String[0]))){
                        command.help();
                    }
                    return;
                }
            }
            ChatUtils.error("Unknown command usage .help to get help");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
