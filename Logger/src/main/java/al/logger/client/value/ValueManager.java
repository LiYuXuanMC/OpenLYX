package al.logger.client.value;

import al.logger.client.features.modules.Module;
import al.logger.client.value.bases.Value;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValueManager {

    public ConcurrentHashMap<Module, Value> values = new ConcurrentHashMap<>();

    public void createValue(Module module, Value value) {
        values.put(module, value);
    }

    public Value getValueByClass(Class<? extends Module> module, Class<? extends Value> clazz) {
        for (Module value : values.keySet()) {
            if (values.get(value).getClass() == clazz && value.getClass() == module) {
                return values.get(value);
            }
        }
        return null;
    }

    public Value getValueByName(Module module, String name) {
        for (Module value : values.keySet()) {
            if (values.get(value).getName().equalsIgnoreCase(name) && value == module) {
                return values.get(value);
            }
        }
        return null;
    }
}
