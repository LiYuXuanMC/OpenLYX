package al.nya.reflect.value;

import al.nya.reflect.utils.render.Translate;

public abstract class Value<V> {
    private String name;
    private V value;
    public Translate translate;
    public Value(String name,V value){
        this.name = name;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
        onValueChange();
    }

    public String getName() {
        return name;
    }
    public abstract String save();
    public abstract void load(String v);
    public boolean show(){
        return true;
    }
    public void onValueChange(){

    }
}
