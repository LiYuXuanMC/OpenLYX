package al.logger.client;

import al.logger.client.utils.LoggerUser;
import al.logger.libs.java_websocket.client.WebSocketClient;
import al.logger.libs.java_websocket.handshake.ServerHandshake;
import by.radioegor146.nativeobfuscator.Native;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;

@Native
public class InjectorSocket extends WebSocketClient {
    public InjectorSocket(){
        super(URI.create("ws://127.0.0.1:10234"));
        this.setConnectionLostTimeout(0);
    }

    public void addProgressBar(String name, int max){
        JsonObject object = new JsonObject();
        object.addProperty("header","addProgressBar");
        object.addProperty("title",name);
        object.addProperty("max",max);
        object.addProperty("value",0);
        sendData(object.toString());
    }
    public void setProgressBar(String name, int value){
        JsonObject object = new JsonObject();
        object.addProperty("header","setProgressBar");
        object.addProperty("title",name);
        object.addProperty("value",value);
        sendData(object.toString());
    }
    public void removeProgressBar(String name){
        JsonObject object = new JsonObject();
        object.addProperty("header","removeProgressBar");
        object.addProperty("title",name);
        sendData(object.toString());
    }
    public void injectFailed(String reason){
        JsonObject object = new JsonObject();
        object.addProperty("header","failed");
        object.addProperty("reason",reason);
        sendData(object.toString());
    }
    public void injectSucceed(){
        JsonObject object = new JsonObject();
        object.addProperty("header","success");
        sendData(object.toString());
    }
    private void sendData(String payload){
        //System.out.println("Send "+payload);
        send(payload);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to Logger Server");
        JsonObject object = new JsonObject();
        object.addProperty("header","getInfo");
        sendData(object.toString());
    }

    @Override
    public void onMessage(String message) {
        JsonObject jsonObject = new Gson().fromJson(message,JsonObject.class);
        if (jsonObject.has("header")){
            String header = jsonObject.get("header").getAsString();
            if (header.equals("userInfo")){
                String username = jsonObject.get("username").getAsString();
                String password = jsonObject.get("password").getAsString();
                Logger.getInstance().setLoggerUser(new LoggerUser(username,password));
                addProgressBar("Loading", 1);
                Logger.getInstance().Logger_Verify();
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Socket closed");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println(ex.toString());
        if (ex.toString().contains("java.net.ConnectException: Connection refused: connect")){
            LoggerWS.Crash();
        }
        ex.printStackTrace();
    }
}
