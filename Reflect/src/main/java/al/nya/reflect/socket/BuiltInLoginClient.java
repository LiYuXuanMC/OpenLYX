package al.nya.reflect.socket;

import al.nya.reflect.Reflect;
import al.nya.reflect.libraries.java_websocket.client.WebSocketClient;
import al.nya.reflect.libraries.java_websocket.handshake.ServerHandshake;
import al.nya.reflect.utils.client.ClientUtil;
import com.google.gson.Gson;
import java.net.URI;
import java.net.URISyntaxException;

public class BuiltInLoginClient extends WebSocketClient {
    public BuiltInLoginClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }


    @Override
    public void onMessage(String message) {
        LoginReturnPack loginReturnPack = new Gson().fromJson(message,LoginReturnPack.class);
        if (loginReturnPack.Code == 404){
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Fail to reconnect:Unknown player");
        }
        if (loginReturnPack.Code == 403){
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Fail to reconnect:Wrong password or username");
        }
        if (loginReturnPack.Code == 407){
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Fail to reconnect:Register OutDated");
        }
        if (loginReturnPack.Code == 406){
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Fail to reconnect:Banned");
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Reason:"+loginReturnPack.Data.banReason);
        }
        if (loginReturnPack.Code == 200){
            ClientUtil.printChat(ClientUtil.Level.ERROR,"Reconnect successfully");
            Reflect.token = loginReturnPack.token;
            try {
                Reflect.ircClient = new IRCClient(new URI(Reflect.debug ? Reflect.debugServer : Reflect.releaseServer));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Reflect.ircClient.setConnectionLostTimeout(60000);
            Reflect.ircClient.connect();
        }
        close();
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
