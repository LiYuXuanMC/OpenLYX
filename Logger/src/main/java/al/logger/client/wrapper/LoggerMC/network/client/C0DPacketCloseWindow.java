package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0DPacketCloseWindow",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class C0DPacketCloseWindow extends Packet {
    @ClassInstance
    public static Class<?> C0DPacketCloseWindowClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {})
    public static Constructor C0DPacketCloseWindowConstructor;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {int.class})
    public static Constructor C0DPacketCloseWindowConstructor_I;
    public C0DPacketCloseWindow(Object obj) {
        super(obj);
    }
    public C0DPacketCloseWindow(){
        this(ReflectUtil.construction(C0DPacketCloseWindowConstructor));
    }
    public C0DPacketCloseWindow(int windowId){
        this(ReflectUtil.construction(C0DPacketCloseWindowConstructor_I));
    }

    public static boolean isC0DPacketCloseWindow(Packet packet) {
        return C0DPacketCloseWindowClass.isInstance(packet.getWrappedObject());
    }
}
