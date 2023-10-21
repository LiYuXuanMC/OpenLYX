package cc.systemv.rave.utils.values;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Value<V> {
    @Getter
    private V value;
    private List<ValueCallback> callbacks = new ArrayList<>();
    public Value(String name,V defaultValue){
        this.value = defaultValue;
    }
    public void setValue(V value){
        onValueChange(this.value,value);
        this.value = value;
    }
    public void onValueChange(V old,V value){

    }
    public abstract String save();
    public abstract void load(String value);
    public void addCallback(ValueCallback callback){
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
}
