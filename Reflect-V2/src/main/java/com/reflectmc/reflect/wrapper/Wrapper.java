package com.reflectmc.reflect.wrapper;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.annotation.repeat.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.mapper.Mapper;
import com.reflectmc.reflect.wrapper.mapper.node.ClassNode;
import com.reflectmc.reflect.wrapper.mapper.node.FieldNode;
import com.reflectmc.reflect.wrapper.mapper.node.MethodNode;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.cactus.CactusWrappingInfo;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.Container;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.PlayerControllerMP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.ServerData;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.C08PacketPlayerBlockPlacement;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.C0APacketAnimation;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.S12PacketEntityVelocity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.S27PacketExplosion;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c02.C02Action;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c02.C02PacketUseEntity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03.C03PacketPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03.C04PacketPlayerPosition;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03.C05PacketPlayerLook;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03.C06PacketPlayerPositionLook;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c07.C07Action;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c07.C07PacketPlayerDigging;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c0b.C0BAction;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c0b.C0BPacketEntityAction;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.Potion;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.PotionEffect;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.PotionUtils;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.entity.Render;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.entity.RenderLivingEntity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse.MovingObjectPosition;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse.MovingObjectType;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.ChatComponentText;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.ChatStyle;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.IChatComponent;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.TextComponentBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.*;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wrapper {
    @Getter
    private static Mapper mapper = new Mapper();
    private final static ArrayList<Class<? extends WrapperBase>> wrappers = new ArrayList<>();
    private final static MethodHandles.Lookup lookup = MethodHandles.lookup();
    @Getter
    private final static List<CactusWrappingInfo> cactusWrapping = new ArrayList<>();
    public static void init(){
        mapper.detectEnvironment();
    }
    public static void wrap(){
        mapper.genMap();
        loadWrappers();
        doWrap();
    }
    private static void loadWrappers(){
        wrappers.add(Minecraft.class);
        wrappers.add(GameSettings.class);
        wrappers.add(KeyBinding.class);
        wrappers.add(Profiler.class);
        wrappers.add(Timer.class);
        //Block
        wrappers.add(Block.class);
        wrappers.add(IBlockState.class);
        wrappers.add(Material.class);
        wrappers.add(BlockAir.class);
        wrappers.add(BlockBush.class);
        wrappers.add(BlockCarpet.class);
        wrappers.add(BlockChest.class);
        wrappers.add(BlockContainer.class);
        wrappers.add(BlockDynamicLiquid.class);
        wrappers.add(BlockEnderChest.class);
        wrappers.add(BlockFence.class);
        wrappers.add(BlockGrass.class);
        wrappers.add(BlockLadder.class);
        wrappers.add(BlockLiquid.class);
        wrappers.add(BlockOre.class);
        wrappers.add(Blocks.class);
        wrappers.add(BlockSkull.class);
        wrappers.add(BlockSnow.class);
        wrappers.add(BlockStairs.class);
        wrappers.add(BlockStateContainer.class);
        wrappers.add(BlockStaticLiquid.class);
        wrappers.add(BlockTallGrass.class);
        wrappers.add(BlockWall.class);
        //Entity
        wrappers.add(AbstractClientPlayer.class);
        wrappers.add(Entity.class);
        wrappers.add(EntityLivingBase.class);
        wrappers.add(EntityPlayer.class);
        wrappers.add(EntityPlayerSP.class);
        wrappers.add(InventoryPlayer.class);
        wrappers.add(PlayerCapabilities.class);
        wrappers.add(EntityAgeable.class);
        wrappers.add(EntityAnimal.class);
        wrappers.add(EntityCreature.class);
        wrappers.add(EntityMob.class);
        wrappers.add(EntityVillager.class);
        wrappers.add(IAnimals.class);
        wrappers.add(EntityArmorStand.class);
        //Gui
        wrappers.add(Gui.class);
        wrappers.add(GuiIngame.class);
        wrappers.add(GuiNewChat.class);
        wrappers.add(GuiScreen.class);
        wrappers.add(ScaledResolution.class);
        //Item
        wrappers.add(Container.class);
        wrappers.add(Item.class);
        wrappers.add(Items.class);
        wrappers.add(ItemStack.class);
        wrappers.add(Slot.class);
        wrappers.add(ItemSword.class);
        wrappers.add(ItemBlock.class);
        wrappers.add(ItemBucketMilk.class);
        wrappers.add(ItemFood.class);
        wrappers.add(ItemPotion.class);
        //multiplayer
        wrappers.add(PlayerControllerMP.class);
        wrappers.add(ServerData.class);
        //Network
        wrappers.add(INetHandler.class);
        wrappers.add(INetHandlerPlayClient.class);
        wrappers.add(NetHandlerPlayClient.class);
        wrappers.add(NetworkManager.class);
        wrappers.add(NetworkPlayerInfo.class);
        wrappers.add(Packet.class);
        wrappers.add(S12PacketEntityVelocity.class);
        wrappers.add(S27PacketExplosion.class);
        wrappers.add(C02Action.class);
        wrappers.add(C02PacketUseEntity.class);
        wrappers.add(C07Action.class);
        wrappers.add(C07PacketPlayerDigging.class);
        wrappers.add(C08PacketPlayerBlockPlacement.class);
        wrappers.add(C0APacketAnimation.class);
        wrappers.add(C03PacketPlayer.class);
        wrappers.add(C04PacketPlayerPosition.class);
        wrappers.add(C05PacketPlayerLook.class);
        wrappers.add(C06PacketPlayerPositionLook.class);
        wrappers.add(C0BAction.class);
        wrappers.add(C0BPacketEntityAction.class);
        //Potion
        wrappers.add(Potion.class);
        wrappers.add(PotionEffect.class);
        wrappers.add(PotionUtils.class);
        //Render
        wrappers.add(FontRenderer.class);
        wrappers.add(GLAllocation.class);
        wrappers.add(GlStateManager.class);
        wrappers.add(OpenGlHelper.class);
        wrappers.add(Tessellator.class);
        wrappers.add(WorldRenderer.class);
        wrappers.add(DefaultVertexFormats.class);
        wrappers.add(VertexFormat.class);
        wrappers.add(AbstractTexture.class);
        wrappers.add(DynamicTexture.class);
        wrappers.add(TextureManager.class);
        wrappers.add(EntityRenderer.class);
        wrappers.add(ItemRenderer.class);
        wrappers.add(RenderManager.class);
        wrappers.add(RenderHelper.class);
        wrappers.add(Render.class);
        wrappers.add(RenderLivingEntity.class);
        //Utils
        wrappers.add(ClickEvent.class);
        wrappers.add(ClickEventAction.class);
        wrappers.add(HoverEvent.class);
        wrappers.add(HoverEventAction.class);
        wrappers.add(SoundEvent.class);
        wrappers.add(MovingObjectPosition.class);
        wrappers.add(MovingObjectType.class);
        wrappers.add(Score.class);
        wrappers.add(Scoreboard.class);
        wrappers.add(ScoreObjective.class);
        wrappers.add(ScorePlayerTeam.class);
        wrappers.add(Team.class);
        wrappers.add(ChatComponentText.class);
        wrappers.add(ChatStyle.class);
        wrappers.add(IChatComponent.class);
        wrappers.add(TextComponentBase.class);
        wrappers.add(AxisAlignedBB.class);
        wrappers.add(BlockPos.class);
        wrappers.add(EnumFacing.class);
        wrappers.add(FoodStats.class);
        wrappers.add(MovementInput.class);
        wrappers.add(ResourceLocation.class);
        wrappers.add(Vec3.class);
        wrappers.add(Vec3i.class);
        //World
        wrappers.add(Chunk.class);
        wrappers.add(IBlockAccess.class);
        wrappers.add(IWorldNameable.class);
        wrappers.add(World.class);
        wrappers.add(WorldClient.class);
    }
    private static void doWrap(){
        int index = 0;
        for (Class<? extends WrapperBase> wrapper : wrappers) {
            index++;
            Reflect.getINSTANCE().getInjectorSocket().updateProgress("Wrapping",index,wrappers.size());
            try {
                doWrap(wrapper);
            } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                System.out.println(wrapper.getCanonicalName()+" cannot be wrap");
            }
        }
    }
    private static void doWrap(Class<? extends WrapperBase> wrapper) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> wrappingClass = getWrappingClass(wrapper);
        if (wrappingClass == null){
            System.out.println(wrapper.getCanonicalName()+" cannot be wrap");
            return;
        }
        for (Field declaredField : wrapper.getDeclaredFields()) {
            for (Annotation declaredAnnotation : declaredField.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof ClassInstance){
                    if (declaredField.getType() == Class.class){
                        declaredField.setAccessible(true);
                        declaredField.set(null,wrappingClass);
                        break;
                    }else {
                        System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
                        break;
                    }
                }
                if (declaredAnnotation instanceof WrapField){
                    if (isVersionMatch(((WrapField) declaredAnnotation).targetEnvironment(),mapper.getVersion()))
                        if (declaredField.getType() == Field.class){
                            WrapField wrapField = (WrapField) declaredAnnotation;
                            doWrap(wrapper, wrappingClass, declaredField, wrapField);
                        }else {
                            System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
                        }
                }
                if (declaredAnnotation instanceof WrapFields){
                    WrapFields wrapFields = (WrapFields) declaredAnnotation;
                    for (WrapField wrapField : wrapFields.value()) {
                        if (isVersionMatch(wrapField.targetEnvironment(),mapper.getVersion()))
                            if (declaredField.getType() == Field.class){
                                doWrap(wrapper, wrappingClass, declaredField, wrapField);
                            }else {
                                System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
                            }
                    }
                }
                if (declaredAnnotation instanceof WrapMethod){
                    WrapMethod wrapMethod = (WrapMethod) declaredAnnotation;
                    doWrap(wrapper, wrappingClass, declaredField, wrapMethod);
                }
                if (declaredAnnotation instanceof WrapMethods){
                    WrapMethods wrapMethods = (WrapMethods) declaredAnnotation;
                    for (WrapMethod wrapMethod : wrapMethods.value()) {
                        doWrap(wrapper, wrappingClass, declaredField, wrapMethod);
                    }
                }
                if (declaredAnnotation instanceof WrapEnum){
                    WrapEnum wrapEnum = (WrapEnum) declaredAnnotation;
                    doWrap(wrapper, wrappingClass, declaredField, wrapEnum);
                }
                if (declaredAnnotation instanceof WrapEnums){
                    WrapEnums wrapEnums = (WrapEnums) declaredAnnotation;
                    for (WrapEnum wrapEnum : wrapEnums.value()) {
                        doWrap(wrapper, wrappingClass, declaredField, wrapEnum);
                    }
                }
                if (declaredAnnotation instanceof WrapObject){
                    WrapObject wrapObject = (WrapObject) declaredAnnotation;
                    doWrap(wrapper, wrappingClass, declaredField, wrapObject);
                }
                if (declaredAnnotation instanceof WrapObjects){
                    WrapObjects wrapObjects = (WrapObjects) declaredAnnotation;
                    for (WrapObject wrapObject : wrapObjects.value()) {
                        doWrap(wrapper, wrappingClass, declaredField, wrapObject);
                    }
                }
                if (declaredAnnotation instanceof WrapConstructor){
                    WrapConstructor wrapConstructor = (WrapConstructor) declaredAnnotation;
                    doWarp(wrappingClass, declaredField, wrapConstructor);
                }
                if (declaredAnnotation instanceof WrapConstructors){
                    WrapConstructors wrapConstructors = (WrapConstructors) declaredAnnotation;
                    for (WrapConstructor wrapConstructor : wrapConstructors.value()) {
                        doWarp(wrappingClass, declaredField, wrapConstructor);
                    }
                }
                if (declaredAnnotation instanceof CustomWrapField){
                    CustomWrapField wrapField = (CustomWrapField) declaredAnnotation;
                    doWrap(wrapper,wrappingClass,declaredField,wrapField);
                }
                if (declaredAnnotation instanceof CustomWrapFields){
                    CustomWrapFields wrapFields = (CustomWrapFields) declaredAnnotation;
                    for (CustomWrapField wrapField : wrapFields.value()) {
                        doWrap(wrapper,wrappingClass,declaredField,wrapField);
                    }
                }
            }
        }
    }
    private static void doWrap(Class<? extends WrapperBase> wrapper, Class<?> wrappingClass, Field declaredField, CustomWrapField wrapField) throws IllegalAccessException {
        if (isVersionMatch(wrapField.targetEnvironment(),mapper.getVersion())){
            Field field = findField(wrappingClass,wrapField.customName());
            if (field == null){
                System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
                return;
            }
            declaredField.setAccessible(true);
            declaredField.set(null,field);
        }
        return;
    }
    private static void doWarp(Class<?> wrappingClass, Field declaredField, WrapConstructor wrapConstructor) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        if (isVersionMatch(wrapConstructor.targetEnvironment(),mapper.getVersion())){
            List<Class<?>> args = new ArrayList<>();
            for (Class<?> aClass : wrapConstructor.signature()) {
                if (WrapperBase.class.isAssignableFrom(aClass)){
                    Class<?> wrappingTarget = getWrappingClass((Class<? extends WrapperBase>) aClass);
                    args.add(wrappingTarget);
                }else {
                    args.add(aClass);
                }
            }
            Class<?>[] classes = args.toArray(new Class[0]);
            Constructor<?> constructor = wrappingClass.getConstructor(classes);
            constructor.setAccessible(true);
            declaredField.setAccessible(true);
            declaredField.set(null,constructor);
        }
    }

    private static void doWrap(Class<? extends WrapperBase> wrapper, Class<?> wrappingClass, Field declaredField, WrapObject wrapObject) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (isVersionMatch(wrapObject.targetEnvironment(),mapper.getVersion())){
            FieldNode fieldNode = mapper.findObfField(wrappingClass,wrapObject.deobfName());
            if (fieldNode != null){
                Field field = findField(wrappingClass,fieldNode.getObfFieldName());
                if (field == null){
                    field = findField(wrappingClass,fieldNode.getDeobfFieldName());
                }
                Object object = field.get(null);
                if (WrapperBase.class.isAssignableFrom(declaredField.getType())){
                    Class<?> wrapperClass = declaredField.getType();
                    Constructor<?> constructor = wrapperClass.getConstructor(Object.class);
                    constructor.setAccessible(true);
                    Object wrapping = constructor.newInstance(object);
                    declaredField.setAccessible(true);
                    declaredField.set(null,wrapping);
                }else {
                    declaredField.setAccessible(true);
                    declaredField.set(null,object);
                }
            }else {
                System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
            }
        }
    }

    private static void doWrap(Class<? extends WrapperBase> wrapper, Class<?> wrappingClass, Field declaredField, WrapEnum wrapEnum) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (isVersionMatch(wrapEnum.targetEnvironment(),mapper.getVersion())){
            FieldNode fieldNode = mapper.findObfField(wrappingClass,wrapEnum.deobfName());
            if (fieldNode == null){
                System.out.println(wrappingClass.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
                return;
            }
            Enum<?> e = null;
            for (Object enumConstant : wrappingClass.getEnumConstants()) {
                Enum enumC = (Enum) enumConstant;
                if (enumC.name().equals(fieldNode.getObfFieldName())){
                    e = enumC;
                }
            }
            if (e == null){
                for (Object enumConstant : wrappingClass.getEnumConstants()) {
                    Enum enumC = (Enum) enumConstant;
                    if (enumC.name().equals(fieldNode.getDeobfFieldName())){
                        e = enumC;
                    }
                }
            }
            if (e != null){
                Constructor constructor = declaredField.getType().getConstructor(Object.class);
                constructor.setAccessible(true);
                Object object = constructor.newInstance(e);
                declaredField.setAccessible(true);
                declaredField.set(null,object);
            }else {
                System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
            }
        }
    }

    private static void doWrap(Class<? extends WrapperBase> wrapper, Class<?> wrappingClass, Field declaredField, WrapMethod wrapMethod) throws IllegalAccessException {
        if (isVersionMatch(wrapMethod.targetEnvironment(),mapper.getVersion())){
            if (declaredField.getType() == Method.class){
                Annotation[] annotations = declaredField.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof CactusWrapping){
                        cactusWrapping.add(new CactusWrappingInfo(declaredField,wrappingClass,wrapMethod));
                    }
                }
                Method methodHandle = findMethod(wrappingClass,wrapMethod.deobfName()
                        ,wrapMethod.signature().equals("none") ? null : wrapMethod.signature());
                if (methodHandle == null){
                    methodHandle = findMethodMCP(wrappingClass,wrapMethod.deobfName()
                            ,wrapMethod.signature().equals("none") ? null : wrapMethod.signature());
                }
                if (methodHandle != null){
                    declaredField.setAccessible(true);
                    declaredField.set(null,methodHandle);
                }else {
                    System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
                }
            }else {
                System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
            }
        }
    }

    private static Method findMethod(Class<?> obfClass,String deobfMethodName,String signature){
        List<MethodNode> methodNodes = mapper.findObfMethods(obfClass, deobfMethodName);
        if (methodNodes.size() == 1){
            MethodNode methodNode = methodNodes.get(0);
            return getMethod(obfClass, methodNode);
        }else if (methodNodes.size() > 1){
            if (signature == null) throw new RuntimeException(obfClass.getCanonicalName()+" "+deobfMethodName+" must refer a signature");
            MethodNode methodNode = null;
            for (MethodNode methodNodea : methodNodes) {
                if (methodNodea.getDeobfSignature().getSignature().equals(signature)){
                    methodNode = methodNodea;
                }
            }
            if (methodNode == null){
                return null;
            }
            return getMethod(obfClass, methodNode);
        }else return null;
    }
    private static Method findMethodMCP(Class<?> obfClass,String deobfMethodName,String signature){
        List<MethodNode> methodNodes = mapper.findObfMethods(obfClass, deobfMethodName);
        if (methodNodes.size() == 1){
            MethodNode methodNode = methodNodes.get(0);
            return getMethodMCP(obfClass, methodNode);
        }else if (methodNodes.size() > 1){
            if (signature == null) throw new RuntimeException(obfClass.getCanonicalName()+" "+deobfMethodName+" must refer a signature");
            MethodNode methodNode = null;
            for (MethodNode methodNodea : methodNodes) {
                if (methodNodea.getDeobfSignature().getSignature().equals(signature)){
                    methodNode = methodNodea;
                }
            }
            if (methodNode == null){
                return null;
            }
            return getMethodMCP(obfClass, methodNode);
        }else return null;
    }

    private static Method getMethod(Class<?> obfClass, MethodNode methodNode){
        Method method;
        try {
            method = obfClass.getDeclaredMethod(methodNode.getObfMethodName(),methodNode.getObfSignature().getParameter());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
        method.setAccessible(true);
        return method;
    }
    private static Method getMethodMCP(Class<?> obfClass, MethodNode methodNode){
        Method method;
        try {
            methodNode.getDeobfSignature().parse();
            method = obfClass.getDeclaredMethod(methodNode.getDeobfMethodName(),methodNode.getDeobfSignature().getParameter());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
        method.setAccessible(true);
        return method;
    }

    private static void doWrap(Class<? extends WrapperBase> wrapper, Class<?> wrappingClass, Field declaredField, WrapField wrapField) throws IllegalAccessException {
        FieldNode fieldNode = mapper.findObfField(wrappingClass,wrapField.deobfName());
        if (fieldNode == null){
            System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
            return;
        }
        Field field = findField(wrappingClass,fieldNode.getObfFieldName());
        if (field == null){
            //Find mcp
            field = findField(wrappingClass,fieldNode.getDeobfFieldName());
        }
        if (field == null){
            System.out.println(wrapper.getCanonicalName()+" "+declaredField.getName()+" cannot be wrap");
            return;
        }
        declaredField.setAccessible(true);
        declaredField.set(null,field);
        return;
    }

    private static Field findField(Class<?> wrappingClass,String name){
        for (Field declaredField : wrappingClass.getDeclaredFields()) {
            if (declaredField.getName().equals(name)){
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        for (Field field : wrappingClass.getFields()) {
            if (field.getName().equals(name)){
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }
    private static boolean isVersionMatch(Environment[] in,Environment match){
        for (Environment environment : in) {
            if (environment == match) return true;
        }
        return false;
    }
    private static Class<?> getWrappingClass(Class<? extends WrapperBase> wrapper) throws ClassNotFoundException {
        for (Annotation declaredAnnotation : wrapper.getDeclaredAnnotations()) {
            if (declaredAnnotation instanceof WrapperClass){
                WrapperClass wrapperClass = (WrapperClass) declaredAnnotation;
                for (Environment environment : wrapperClass.targetEnvironment()) {
                    if (environment == mapper.getVersion()){
                        ClassNode cn = mapper.findObfClass(wrapperClass.deobfName());
                        if (cn != null){
                            Class<?> cls = Class.forName(cn.getObfClassName());
                            if (cls == null){
                                //Find mcp
                                cls = Class.forName(wrapperClass.deobfName());
                            }
                            return cls;
                        }
                    }
                }
            }
            if (declaredAnnotation instanceof WrapperClasses){
                for (WrapperClass wrapperClass : ((WrapperClasses) declaredAnnotation).value()) {
                    for (Environment environment : wrapperClass.targetEnvironment()) {
                        if (environment == mapper.getVersion()){
                            ClassNode cn = mapper.findObfClass(wrapperClass.deobfName());
                            if (cn != null){
                                Class<?> cls = Class.forName(cn.getObfClassName());
                                if (cls == null){
                                    //Find mcp
                                    cls = Class.forName(wrapperClass.deobfName());
                                }
                                return cls;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
