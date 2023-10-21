package dev.qingwan.crater.test;

import dev.qingwan.crater.annotation.CraterVM;
import dev.qingwan.crater.vm.ExampleVirtualMachine;

//+FieldVirtualize Put fields into vm
@CraterVM(args = "")
public class CraterTest extends CraterTestBase{
    private String a;
    private ExampleVirtualMachine vm;
    public CraterTest(int a) {
        this(a,new Integer(1));
        vm = new ExampleVirtualMachine();
    }
    public CraterTest(int a,int b){
        super(add(a,b));
    }

    public String getA() {
        return a;
    }

    public static int add(int a, int b) {
        return a + b;
    }
    public static int sub(int a, int b) {
        return a - b;
    }
    public static int mul(int a, int b) {
        return a * b;
    }
    public static int div(int a, int b) {
        return a / b;
    }
    public static int mod(int a, int b) {
        return a % b;
    }
    public static int and(int a, int b) {
        return a & b;
    }
    public static int or(int a, int b) {
        return a | b;
    }
    public static int xor(int a, int b) {
        return a ^ b;
    }
    public static int shl(int a, int b) {
        return a << b;
    }
    public static int shr(int a, int b) {
        return a >> b;
    }
    public static int ushr(int a, int b) {
        return a >>> b;
    }
    public static int neg(int a) {
        return -a;
    }
    public static int not(int a) {
        return ~a;
    }
}
