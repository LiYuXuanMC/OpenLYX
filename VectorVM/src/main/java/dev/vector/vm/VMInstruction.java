package dev.vector.vm;

import dev.vector.utils.InstructionMapping;

public class VMInstruction {
    private VMPool vmPool;
    private int instruction;
    private ObjectContainer result = new ObjectContainer(null);
    VMInstruction(VMPool vmPool) {
        this.vmPool = vmPool;
    }
    public VMInstruction putObject(Object object){
        this.vmPool.push(object);
        return this;
    }
    public VMInstruction putInstruction(int instruction){
        this.instruction = instruction;
        return this;
    }

    public Object getResult() {
        return result.getValue();
    }

    public VMInstruction execute(){
        switch (this.instruction){
            case InstructionMapping.STORE:
                this.vmPool.store((int) this.vmPool.pop(), this.vmPool.pop());
                break;
            case InstructionMapping.LOAD:
                result.setValue(vmPool.load((int) this.vmPool.pop()));
                break;
        }
        return this;
    }
}
