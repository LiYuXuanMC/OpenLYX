package al.logger.client.value.bases;

import com.google.gson.annotations.SerializedName;

public class AuthUser {

    @SerializedName("gameid")
    public String gameid;

    @SerializedName("username")
    public String username;

    @SerializedName("power")
    public int power;

    @SerializedName("ranked")
    public String ranked;


    public AuthUser(String gameid, String username, int power, String ranked) {
        this.gameid = gameid;
        this.username = username;
        this.ranked = ranked;
        this.power = power;
    }

}
