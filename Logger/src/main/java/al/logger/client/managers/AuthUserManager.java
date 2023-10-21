package al.logger.client.managers;

import al.logger.client.value.bases.AuthUser;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class AuthUserManager {

    @Getter
    @Setter
    private HashMap<String, AuthUser> authUsers = new HashMap<>();

    public AuthUserManager() {
    }

    public AuthUser getAuthUser(String username) {
        return authUsers.get(username);
    }

    public void addAuthUser(AuthUser authUser) {
        authUsers.put(authUser.gameid, authUser);
    }

    public void removeAuthUser(String username) {
        authUsers.remove(username);
    }
    public void clear() {
        authUsers.clear();
    }

}
