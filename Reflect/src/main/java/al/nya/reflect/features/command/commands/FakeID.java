package al.nya.reflect.features.command.commands;

import al.nya.reflect.config.FriendManager;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.utils.client.ClientUtil;

public class FakeID extends Command {
    public FakeID() {
        super("FakeID", "fakeid");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        if (args.length >= 1) {
            switch (args[0]) {
                case "clear":
                    al.nya.reflect.features.modules.Visual.FakeID.clearFakeID();
                    break;
                case "list":
                    al.nya.reflect.features.modules.Visual.FakeID.listFakeID();
                case "add":
                    if (args.length != 3) {
                        ClientUtil.printChat(".fakeid add [ID] [FakeID] -Add ID to list");
                        return;
                    }
                    al.nya.reflect.features.modules.Visual.FakeID.addFakeID(args[1], args[2]);
                    break;
                case "remove":
                    al.nya.reflect.features.modules.Visual.FakeID.removeFakeID(args[1]);
                    break;
                default:
                    ClientUtil.printChat("\u00a7c指令错误: \u00a7b/fakeid <add/remove/clear/list>");
                    break;
            }
        } else {
            ClientUtil.printChat("\u00a7c指令错误: \u00a7b/fakeid <add/remove/clear/list>");
        }
    }
}
