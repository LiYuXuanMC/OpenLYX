package al.logger.client.features.commands.commands;


import al.logger.client.Logger;
import al.logger.client.features.commands.Command;

public class IRC extends Command {
    public static boolean getList = false;
    public IRC() {
        super("IRC","irc");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args[0].equals("online")){
            Logger.getInstance().loggerWS.sendIRCHeartBeatPacket();
            return true;
        }
        if (args.length > 1){
            if (args[0].equals("send")){
                String message = args[1];
                for (int i = 2; i < args.length; i++) {
                    String str = args[i];
                    message = message + " " + str;
                }
                //System.out.println("message = " + message);
                Logger.getInstance().loggerWS.sendIRCMsgToServer(message,1);
            }
            if (args[0].equals("title")){
                String message = args[1];
                for (int i = 2; i < args.length; i++) {
                    String str = args[i];
                    message = message + " " + str;
                }
                message = message.replaceFirst(" ","");
                //System.out.println("message = " + message);
                Logger.getInstance().loggerWS.sendIRCMsgToServer(message,2);
            }
        }else {
            help();
        }
        return true;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[send]","[msg]"},"send msg to IRC Channel");
    }
}
