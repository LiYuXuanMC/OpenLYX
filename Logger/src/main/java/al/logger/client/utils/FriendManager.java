package al.logger.client.utils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FriendManager {
    private final List<String> friends = new CopyOnWriteArrayList<>();
    public FriendManager(){

    }
    public boolean addFriend(String id){
        if (isFriend(id))
            return false;
        friends.add(id);
        return true;
    }
    public boolean removeFriend(String id){
        if (!isFriend(id))
            return false;
        friends.removeIf(id::equals);
        return true;
    }
    public boolean isFriend(String id){
        return friends.contains(id);
    }
    public List<String> getFriends(){
        return friends;
    }
    public void clearFriends(){
        friends.clear();
    }
}
