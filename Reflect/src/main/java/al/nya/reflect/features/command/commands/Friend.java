package al.nya.reflect.features.command.commands;

import al.nya.reflect.config.FriendManager;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.utils.client.ClientUtil;

public class Friend extends Command {
    public Friend() {
        super("Friend", "friend");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        if (args.length >= 1) {
            switch (args[0]) {
                case "clear":
                    FriendManager.clear();
                    break;
                case "list":
                    ClientUtil.printChat("\u00a7b好友列表 \u00a77:(" + FriendManager.friendsList.size() + ")");
                    for (String friend : FriendManager.friendsList) {
                        ClientUtil.printChat("\u00a7b" + friend);
                    }
                    break;
                case "add":
                    FriendManager.addFriend(args[1]);
                    break;
                case "remove":
                    FriendManager.removeFriend(args[1]);
                    break;
                default:
                    ClientUtil.printChat("\u00a7c指令错误: \u00a7b/f <add/remove/clear/list>");
                    break;
            }
        } else {
            ClientUtil.printChat("\u00a7c指令错误: \u00a7b/f <add/remove/clear/list>");
        }
        super.onMessage(msg, args);
    }
}
