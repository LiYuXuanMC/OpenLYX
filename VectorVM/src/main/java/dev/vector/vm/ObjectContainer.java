package dev.vector.vm;

public class ObjectContainer {
    private Object value;
    public ObjectContainer(Object initValue) {
        this.value = initValue;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
