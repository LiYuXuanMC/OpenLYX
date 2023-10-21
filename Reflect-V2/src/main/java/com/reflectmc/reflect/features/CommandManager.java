package com.reflectmc.reflect.features;

import com.reflectmc.reflect.event.events.game.EventSendMessage;
import com.reflectmc.reflect.features.commands.*;
import com.reflectmc.reflect.utils.ClientUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    @Getter
    private List<Command> commands = new ArrayList<>();
    public CommandManager(){

    }
    public void init(){
        commands.add(new Help());
        commands.add(new Toggle());
        commands.add(new Bind());
        commands.add(new Config());
        commands.add(new Say());
    }
    public void onMessage(EventSendMessage message){
        if (message.getMessage().startsWith(".")){
            message.cancel();
            List<String> args = new ArrayList<>(Arrays.asList(message.getMessage().replaceFirst(".", "").split(" ")));
            for (Command command : commands) {
                if (command.getCommand().equalsIgnoreCase(args.get(0))){
                    args.remove(0);
                    if(!command.trigger(args.toArray(new String[0]))){
                        ClientUtil.printChat(ClientUtil.Level.ERROR,"Unknown command usage .help to get help");
                    }
                    return;
                }
            }
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Unknown command use .help to get help");
        }
    }
}
