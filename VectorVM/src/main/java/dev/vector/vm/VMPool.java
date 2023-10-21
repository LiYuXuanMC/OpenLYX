package dev.vector.vm;

import java.util.ArrayList;
import java.util.List;

public class VMPool {
    private final List<ObjectContainer> stacks;
    private final ObjectContainer[] locals;
    VMPool(int locals){
        this.stacks = new ArrayList<>();
        this.locals = new ObjectContainer[locals];
        for (int i = 0; i < locals; i++) {
            this.locals[i] = new ObjectContainer(null);
        }
    }
    public void push(Object object){
        this.stacks.add(new ObjectContainer(object));
    }
    public Object pop(){
        ObjectContainer objectContainer = this.stacks.get(this.stacks.size()-1);
        this.stacks.remove(this.stacks.size()-1);
        return objectContainer.getValue();
    }
    void store(int index, Object object){
        this.locals[index].setValue(object);
    }
    Object load(int index){
        return this.locals[index].getValue();
    }
}
