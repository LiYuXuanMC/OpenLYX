package al.nya.reflect.wrapper.wraps.wrapper.network.S08;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.network.play.server.S08PacketPlayerPosLook$EnumFlags",targetMap = Maps.Srg1_8_9)
public class S08EnumFlags extends EnumWrapper {
    @WrapEnum(mcpName = "X",targetMap = Maps.Srg1_8_9)
    public static Enum X;
    @WrapEnum(mcpName = "Y",targetMap = Maps.Srg1_8_9)
    public static Enum Y;
    @WrapEnum(mcpName = "Z",targetMap = Maps.Srg1_8_9)
    public static Enum Z;
    @WrapEnum(mcpName = "Y_ROT",targetMap = Maps.Srg1_8_9)
    public static Enum Y_ROT;
    @WrapEnum(mcpName = "X_ROT",targetMap = Maps.Srg1_8_9)
    public static Enum X_ROT;
    public S08EnumFlags(Object obj) {
        super(obj);
    }
}
