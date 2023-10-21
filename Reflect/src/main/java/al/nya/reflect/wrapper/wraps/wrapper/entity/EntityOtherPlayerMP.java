package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.GameProfile;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.client.entity.EntityOtherPlayerMP", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.entity.EntityOtherPlayerMP", targetMap = Maps.Srg1_12_2)
public class EntityOtherPlayerMP extends AbstractClientPlayer {
    @WrapClass(mcpName = "net.minecraft.client.entity.EntityOtherPlayerMP", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.entity.EntityOtherPlayerMP", targetMap = Maps.Srg1_12_2)
    public static Class<?> EntityOtherPlayerMPClass;
    public static Constructor<?> EntityOtherPlayerMP_Constructor;

    public EntityOtherPlayerMP(Object obj) {
        super(obj);
    }

    public EntityOtherPlayerMP(World worldIn, GameProfile gameProfile) {
        this(ReflectUtil.construction(EntityOtherPlayerMP_Constructor, worldIn.getWrapObject(), gameProfile.getWrapObject()));
    }

    public static boolean isEntityOtherPlayerMP(Object c) {
        return EntityOtherPlayerMPClass.isInstance(c);
    }
}
