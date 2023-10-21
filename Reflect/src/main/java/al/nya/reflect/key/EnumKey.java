package al.nya.reflect.key;

public enum EnumKey {
    None(-1,"None"),
    ESC(1,"ESC"),
    Q(16,"Q"),
    W(17,"W"),
    E(18,"E"),
    R(19,"R"),
    T(20,"T"),
    Y(21,"Y"),
    U(22,"U"),
    I(23,"I"),
    O(24,"O"),
    P(25,"P"),
    A(30,"A"),
    S(31,"S"),
    D(32,"D"),
    F(33,"F"),
    G(34,"G"),
    H(35,"H"),
    J(36,"J"),
    K(37,"K"),
    L(38,"L"),
    Z(44,"Z"),
    X(45,"X"),
    C(46,"C"),
    V(47,"V"),
    B(48,"B"),
    N(49,"N"),
    M(50,"M"),
    LSHIFT(42,"LShift"),
    RSHIFT(54,"RShift"),
    LCTRL(29,"LCtrl"),
    CTRL(157,"RCtrl"),
    LAlt(56,"LAlt"),
    RAlt(184,"RAlt"),
    Insert(210,"Insert"),
    N1(2,"1"),
    N2(3,"2"),
    N3(4,"3"),
    N4(5,"4"),
    N5(6,"5"),
    N6(7,"6"),
    N7(8,"7"),
    N8(9,"8"),
    N9(10,"9"),
    N0(11,"0"),
    Sub(12,"-"),
    Equal(13,"="),
    BackSpace(14,"BackSpace"),
    Symbol41(41,"~"),
    Symbol39(39,";"),
    Symbol40(40,"'"),
    Symbol51(51,","),
    Symbol52(52,"."),
    LeftBrackets(26,"["),
    RightBrackets(27,"]"),
    LeftSlash(43,"\\"),
    RightSlash(53,"/"),
    LeftWin(219,"Win"),
    Tab(15,"Tab"),
    CapsLock(59,"Caps Lock"),
    Enter(28,"Enter"),
    Space(57,"Space"),
    ;
    private int keyCode;
    private String displayName;
    EnumKey(int keyCode,String displayName){
        this.keyCode = keyCode;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getKeyCode() {
        return keyCode;
    }
    public static EnumKey getKey(int keyCode){
        EnumKey ek = null;
        for (EnumKey value : EnumKey.values()) {
            if (value.keyCode == keyCode) ek = value;
        }
        return ek;
    }
    public static EnumKey getKey(String displayName){
        for (EnumKey value : EnumKey.values()) {
            if (value.displayName.equalsIgnoreCase(displayName)) return value;
        }
        return null;
    }
}
