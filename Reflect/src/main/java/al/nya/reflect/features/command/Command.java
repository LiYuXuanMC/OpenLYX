package al.nya.reflect.features.command;


import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;

public abstract class Command {
    public final Minecraft mc = Minecraft.getMinecraft();
    private final String name;
    private final String prefix;

    public Command(String CMDNAME, String prefix) {
        this.name = CMDNAME;
        this.prefix = prefix;
    }

    public void onMessage(String msg, String[] args) {
    }

    public String[] getHelpMessage() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean clamp(String input, int min, int max) {
        String[] split = input.split(" ");
        if (split.length > max) {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Too much input");
            return false;
        }
        if (split.length < min) {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Not enough input");
            return false;
        }
        return true;
    }

    public boolean clamp(String input, int min) {
        String[] split = input.split(" ");
        if (split.length < min) {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Not enough input");
            return false;
        }
        return true;
    }

    public boolean equals(String[] list, String input) {
        for (String s : list) {
            if (s.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public void printUsage() {
        ClientUtil.printChat(ClientUtil.Level.ERROR, "Usage:");
        for (String s : getHelpMessage()) {
            ClientUtil.printChat(ClientUtil.Level.ERROR, s);
        }
    }
}
