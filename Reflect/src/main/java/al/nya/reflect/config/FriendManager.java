package al.nya.reflect.config;

import al.nya.reflect.utils.client.ClientUtil;

import java.util.ArrayList;

public class FriendManager {
    public static ArrayList<String> friendsList = new ArrayList<>();

    public static void addFriend(String friendname) {
        if (!friendsList.contains(friendname)) {
            friendsList.add(friendname);
            ClientUtil.printChat("已添加 " + friendname + " 到 \u00a7b好友列表 \u00a77");
        }
    }

    public static void removeFriend(String friendname) {
        if (friendsList.contains(friendname)) {
            friendsList.remove(friendname);
            ClientUtil.printChat("已从 \u00a7b好友列表 \u00a77删除 " + friendname);
        }
    }

    public static void clear() {
        if (!friendsList.isEmpty()) {
            friendsList.clear();
            ClientUtil.printChat("\u00a7b好友列表 \u00a77已清空.");
        }
    }
}
