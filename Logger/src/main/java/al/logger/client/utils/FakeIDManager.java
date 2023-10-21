package al.logger.client.utils;

import est.builder.annotations.Clear;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class FakeIDManager {
    private static Map<String,String> fakeIDs = new ConcurrentHashMap<>();
    @Clear(when = "Release")
    public static void addFakeID(String realID, String fakeID){
        fakeIDs.put(realID,fakeID);
    }
    @Clear(when = "Release")
    public static String getFakeID(String realID){
        return fakeIDs.get(realID);
    }
    @Clear(when = "Release")
    public static Map<String, String> getFakeIDs() {
        return fakeIDs;
    }
    @Clear(when = "Release")
    public static boolean hasFakeID(String realID){
        return fakeIDs.containsKey(realID);
    }
    @Clear(when = "Release")
    public static void removeFakeID(String realID){
        fakeIDs.remove(realID);
    }
    @Clear(when = "Release")
    public static void clearFakeIDs(){
        fakeIDs.clear();
    }
}
