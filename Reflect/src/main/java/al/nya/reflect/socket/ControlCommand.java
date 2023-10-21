package al.nya.reflect.socket;

public class ControlCommand {
    String command;
    String data;
    public ControlCommand(String command,String data){
        this.command =command;
        this.data = data;
    }
    public String getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }
}
