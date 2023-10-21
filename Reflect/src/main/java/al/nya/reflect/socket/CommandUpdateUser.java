package al.nya.reflect.socket;

import lombok.Getter;

import java.util.List;
import java.util.Map;

public class CommandUpdateUser {
    @Getter
    private String[][] users;
    public CommandUpdateUser(String[][] users){
        this.users = users;
    }
}
