package al.logger.client.features.commands;

import al.logger.client.utils.ChatUtils;
import lombok.Getter;

public abstract class Command {
    @Getter
    private String name;
    @Getter
    private String command;
    @Getter
    private String[] alias = new String[]{};
    public Command(String name,String command){
        this.name = name;
        this.command = command;
    }
    public Command(String name,String command,String[] alias){
        this.name = name;
        this.command = command;
        this.alias = alias;
    }
    public abstract boolean trigger(String[] args);
    public abstract void help();
    public void printHelp(String[] args, String describe){
        StringBuilder sb = new StringBuilder();
        sb.append("⧈ ");
        sb.append(".").append(command);
        for (String arg : args) {
            sb.append(" ").append(arg);
        }
        sb.append(" -").append(describe);

        ChatUtils.message(sb.toString());
        //ClientUtil.printChat(ClientUtil.Level.INFO,sb.toString());
    }
}
