package cc.systemv.rave.feature.command;


import cc.systemv.rave.utils.client.ChatUtils;

public abstract class Command {
    private String name;
    private String command;
    private String[] alias = new String[]{};
    public Command(String name, String command){
        this.name = name;
        this.command = command;
    }
    public Command(String name, String command, String[] alias){
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
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public String[] getAlias() {
        return alias;
    }
}
