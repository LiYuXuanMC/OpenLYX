package al.logger.client.features.commands.commands;

import al.logger.client.features.commands.Command;
import al.logger.client.utils.ChatUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.utils.Session;

import java.lang.reflect.Field;

public class Auth extends Command {
    public Auth() {
        super("Auth","auth");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 1){
            //Key GameID
            Session session = Minecraft.getMinecraft().getSession();
            session.setUsername(args[0]);
            ChatUtils.message("Your New GameID is： " + args[0]);
            return true;
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Module]"},"Toggle a module");
    }
}
