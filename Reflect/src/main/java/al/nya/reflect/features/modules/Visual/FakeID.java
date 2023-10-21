package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventText;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.client.ClientUtil;

import java.util.HashMap;
import java.util.Map;

public class FakeID extends Module {
    private static Map<String, String> fakeIDMap = new HashMap<String, String>();

    public FakeID() {
        super("FakeID", "假名称", ModuleType.Visual);
    }

    @EventTarget
    public void onText(EventText text) {
        if (text.getText() != null) {
            fakeIDMap.forEach((key, value) -> {
                text.setText(text.getText().replace(key, value));
            });
        }
    }

    public static void addFakeID(String name, String fakeID) {
        fakeIDMap.put(name, fakeID);
    }

    public static void clearFakeID() {
        fakeIDMap.clear();
    }

    public static void removeFakeID(String name) {
        fakeIDMap.remove(name);
    }

    public static void listFakeID() {
        ClientUtil.printChat("FakeID List (" + fakeIDMap.size() + "):");
        fakeIDMap.forEach((R, F) -> {
            ClientUtil.printChat("§a" + R + " §7-> §2" + F);
        });
    }
}
