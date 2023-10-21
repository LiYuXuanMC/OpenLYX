package dev.vector.vm;

public class VMMethod {
    VMMethod() {

    }
    public VMPool allocatePool(int locals) {
        return new VMPool(locals);
    }
    public VMInstruction allocateInstruction(VMPool vmPool) {
        return new VMInstruction(vmPool);
    }
}
