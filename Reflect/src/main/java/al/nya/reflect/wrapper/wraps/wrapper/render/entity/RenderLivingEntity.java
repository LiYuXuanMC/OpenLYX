package al.nya.reflect.wrapper.wraps.wrapper.render.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.model.ModelBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RendererLivingEntity",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderLivingBase",targetMap = Maps.Srg1_12_2)
public class RenderLivingEntity extends Render {
    @WrapClass(mcpName = "net.minecraft.client.renderer.entity.RendererLivingEntity",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.entity.RenderLivingBase",targetMap = Maps.Srg1_12_2)
    public static Class<?> RenderLivingEntityClass;
    public RenderLivingEntity(Object obj) {
        super(obj);
    }
    @WrapMethod(mcpName = "doRender",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "doRender",targetMap = Maps.Srg1_8_9)
    public static Method doRender;
    @WrapField(mcpName = "mainModel", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "mainModel", targetMap = Maps.Srg1_8_9)
    public static Field mainModel;
    public ModelBase getMainModel() {
        return new ModelBase(getField(mainModel));
    }
}
