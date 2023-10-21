package al.logger.client.features.commands;

import al.logger.client.ExceptionHandler;
import al.logger.client.Logger;
import al.logger.client.features.commands.commands.*;
import al.logger.client.ui.bases.components.InstanceEx;
import al.logger.client.utils.ChatUtils;
import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Native
public class CommandManager {
    @Getter
    private List<Command> commands = new ArrayList<>();
    public CommandManager(){

    }
    @Native
    //////@VM
    public void init(Class<?> aClazz){
        commands.add(new Help());
        commands.add(new Toggle());
        commands.add(new Bind());
        commands.add(new IRC());
        commands.add(new Config());
        commands.add(new Auth());
        commands.add(new SelfDestruct());
        commands.add(new FakeID());
        commands.add(new Friend());
        commands.add(new IGN());
        commands.add(new Hide());
        commands.add(new Script());
        if (!Logger.getInstance().isMySelfObf){
            commands.add(new Debug());
            commands.add(new ReloadClass());
            commands.add(new DumpClass());
            commands.add(new DumpPlayer());
            commands.add(new ExportDebug());
            commands.add(new DumpScoreboard());
        }

        throw new InstanceEx(Integer.parseInt(Logger.getInstance().getLoggerUser().getInstanceTokenCom().substring(0, 3)), Integer.parseInt(Logger.getInstance().getLoggerUser().getInstanceTokenCom().substring(3, 5)), Logger.getInstance().getLoggerUser().getInstanceTokenCom().substring(5), aClazz);
    }

    public void runCommand(String message){
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
            ExceptionHandler.handle(e);
        }

    }
}
