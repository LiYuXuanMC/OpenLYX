package al.logger.client.wrapper.LoggerMC.model;

import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelBiped", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.model.ModelBiped", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class ModelBiped extends ModelBase {
@ClassInstance    
public static Class<?> ModelBipedClass;
    @WrapField(mcpName = "bipedHeadwear", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "bipedHeadwear", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field bipedHeadwear;
    @WrapField(mcpName = "bipedRightArm", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "bipedRightArm", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field bipedRightArm;
    @WrapField(mcpName = "bipedLeftArm", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "bipedLeftArm", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field bipedLeftArm;
    @WrapField(mcpName = "heldItemRight", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "heldItemRight", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field heldItemRight;
    @WrapMethod(mcpName = "setRotationAngles",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setRotationAngles",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setRotationAngles;
    @WrapField(mcpName = "bipedRightLeg", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field bipedRightLeg;

    @WrapField(mcpName = "bipedLeftLeg", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field bipedLeftLeg;
    @WrapField(mcpName = "aimedBow", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field aimedBow;

    public ModelBiped(Object obj) {
        super(obj);
    }
    public ModelRenderer getBipedHead() {
        return new ModelRenderer(getField(bipedHeadwear));
    }
    public static boolean isModelBiped(Object obj) {
        return ModelBipedClass.isInstance(obj);
    }

    public void setAimedBow(boolean type){
        setField(aimedBow , type);
    }
/*
    public void setIsChild(boolean type){
        setField(isChild , type);
    }


 */
    public ModelRenderer getBipedRightArm(){
        return new ModelRenderer(getField(bipedRightArm));
    }
    public ModelRenderer getBipedLeftArm(){
        return new ModelRenderer(getField(bipedLeftArm));
    }

    public ModelRenderer getBipedRightLeg(){return new ModelRenderer(getField(bipedRightLeg));}

    public ModelRenderer getBipedLeftLeg(){return new ModelRenderer(getField(bipedLeftLeg));}

}
