package al.nya.reflect.wrapper.wraps.wrapper.model;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelBiped", targetMap = Maps.Srg1_12_2)
@WrapperClass(mcpName = "net.minecraft.client.model.ModelBiped", targetMap = Maps.Srg1_8_9)
public class ModelBiped extends ModelBase {
    @WrapClass(mcpName = "net.minecraft.client.model.ModelBiped", targetMap = Maps.Srg1_12_2)
    @WrapClass(mcpName = "net.minecraft.client.model.ModelBiped", targetMap = Maps.Srg1_8_9)
    public static Class<?> ModelBipedClass;
    @WrapField(mcpName = "bipedHeadwear", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "bipedHeadwear", targetMap = Maps.Srg1_8_9)
    public static Field bipedHeadwear;
    public ModelBiped(Object obj) {
        super(obj);
    }
    public ModelRenderer getBipedHead() {
        return new ModelRenderer(getField(bipedHeadwear));
    }
    public static boolean isModelBiped(Object obj) {
        return ModelBipedClass.isInstance(obj);
    }

}
