package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapObject;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;


@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetMap = Maps.Srg1_12_2)
public class DefaultVertexFormats extends IWrapper {
    @WrapObject(mcpName = "POSITION_TEX_COLOR", targetMap = Maps.Srg1_8_9, makeWrap = VertexFormat.class)
    @WrapObject(mcpName = "POSITION_TEX_COLOR", targetMap = Maps.Srg1_12_2, makeWrap = VertexFormat.class)
    public static VertexFormat POSITION_TEX_COLOR;
    @WrapObject(mcpName = "POSITION", targetMap = Maps.Srg1_8_9, makeWrap = VertexFormat.class)
    @WrapObject(mcpName = "POSITION", targetMap = Maps.Srg1_12_2, makeWrap = VertexFormat.class)
    public static VertexFormat POSITION;
    @WrapObject(mcpName = "POSITION_COLOR", targetMap = Maps.Srg1_8_9, makeWrap = VertexFormat.class)
    @WrapObject(mcpName = "POSITION_COLOR", targetMap = Maps.Srg1_12_2, makeWrap = VertexFormat.class)
    public static VertexFormat POSITION_COLOR;

    public DefaultVertexFormats(Object obj) {
        super(obj);
    }
}
