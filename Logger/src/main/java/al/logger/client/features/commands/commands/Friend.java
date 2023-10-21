package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.utils.ChatUtils;

public class Friend extends Command {
    public Friend() {
        super("Friend","friend");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("add")){
                if (Logger.getInstance().friendManager.addFriend(args[1])){
                    ChatUtils.message("Added " + args[1] + " to friends list");
                }else {
                    ChatUtils.error("Failed to add " + args[1] + " to friends list");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")){
                if (Logger.getInstance().friendManager.removeFriend(args[1])){
                    ChatUtils.message("Removed " + args[1] + " from friends list");
                }else {
                    ChatUtils.error("Failed to remove " + args[1] + " from friends list");
                }
                return true;
            }
        }else if (args.length == 1){
            if (args[0].equalsIgnoreCase("list")){
                ChatUtils.message("Friends:");
                for (String friend : Logger.getInstance().friendManager.getFriends()){
                    ChatUtils.message(friend);
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("clear")){
                Logger.getInstance().friendManager.clearFriends();
                ChatUtils.message("Cleared friends list");
                return true;
            }
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"add","[Player]"},"Add a player to friends list");
        printHelp(new String[]{"remove","[Player]"},"Remove a player from friends list");
        printHelp(new String[]{"list"},"List all friends");
        printHelp(new String[]{"clear"},"Clear friends list");
    }
}
