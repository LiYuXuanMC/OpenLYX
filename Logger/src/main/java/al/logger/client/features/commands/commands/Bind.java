package al.logger.client.features.commands.commands;


import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import org.lwjgl.input.Keyboard;

public class Bind extends Command {
    public Bind() {
        super("Bind", "bind");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 2) {
            Module module = Logger.getInstance().getModuleManager().getModule(args[0]);
            if (module == null) {
                module = Logger.getInstance().getModuleManager().getByDisplayName(args[0]);
                if (module == null) {
                    ChatUtils.error("Unknown module: " + args[0]);
                    //ClientUtil.printChat(ClientUtil.Level.ERROR,"Unknown module: "+args[0]);
                    return true;
                }
            }
            int key = Keyboard.getKeyIndex(args[1].toUpperCase());
            module.setKeyCode(key);
            ChatUtils.message("Bound " + module.getDisplayName() + " to " + Keyboard.getKeyName(module.getKeyCode()));
            //ClientUtil.printChat(ClientUtil.Level.INFO,"Bound "+module.getName()+" to "+module.getBind().getDisplayName());
        } else {
            help();
        }
        return true;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Module]", "[Key]"}, "Bind module to a key");
    }
}
