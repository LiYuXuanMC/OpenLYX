package al.logger.client.value.bases;


import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Value<V> {
    private String name;
    @Getter
    private String displayName;
    private V value;
    @Getter
    private List<ValueCallback> callbacks = new CopyOnWriteArrayList<>();

    public Value(String name, V value) {
        this.name = name;
        this.displayName = name;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        onValueChange(this.value,value);
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public abstract String save();

    public abstract void load(String v);
    public void addCallBack(ValueCallback callback){
        callbacks.add(callback);
    }
    public boolean callBack() {
        if(callbacks.size() == 0){
            return true;
        }
        for (ValueCallback callback : callbacks) {
            if (!callback.changeVisibility())
                return false;
        }
        return true;
    }

    public void onValueChange(V old,V newValue) {

    }


}
