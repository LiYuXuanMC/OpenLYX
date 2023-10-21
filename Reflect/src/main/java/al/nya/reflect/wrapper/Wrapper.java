package al.nya.reflect.wrapper;

import al.nya.reflect.Reflect;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.srgreader.Reader;
import al.nya.reflect.libraries.srgreader.map.MapNode;
import al.nya.reflect.libraries.srgreader.map.MethodNode;
import al.nya.reflect.libraries.srgreader.map.NodeType;
import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.utils.render.ActiveRenderInfo;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.*;
import al.nya.reflect.wrapper.wraps.annotation.repeat.*;
import al.nya.reflect.wrapper.wraps.wrapper.*;
import al.nya.reflect.wrapper.wraps.wrapper.block.*;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.Enchantment;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.EnchantmentHelper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.gui.*;
import al.nya.reflect.wrapper.wraps.wrapper.item.*;
import al.nya.reflect.wrapper.wraps.wrapper.model.ModelBase;
import al.nya.reflect.wrapper.wraps.wrapper.model.ModelBiped;
import al.nya.reflect.wrapper.wraps.wrapper.model.ModelRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.ServerData;
import al.nya.reflect.wrapper.wraps.wrapper.network.*;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C05PacketPlayerLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C06PacketPlayerPositionLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BPacketEntityAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16PacketClientStatus;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16State;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08EnumFlags;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionUtils;
import al.nya.reflect.wrapper.wraps.wrapper.render.*;
import al.nya.reflect.wrapper.wraps.wrapper.render.entity.Render;
import al.nya.reflect.wrapper.wraps.wrapper.render.entity.RenderLivingEntity;
import al.nya.reflect.wrapper.wraps.wrapper.shader.Framebuffer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.*;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEventAction;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.SoundEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEventAction;
import al.nya.reflect.wrapper.wraps.wrapper.utils.registry.RegistryNamespaced;
import al.nya.reflect.wrapper.wraps.wrapper.utils.registry.RegistrySimple;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.*;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.ChatComponentText;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import al.nya.reflect.wrapper.wraps.wrapper.world.*;
import lombok.Getter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Wrapper {
    public static String env;
    @Getter
    private static Reader reader;
    private static final List<Class<? extends IWrapper>> wrappers = new ArrayList<Class<? extends IWrapper>>();
    public static void initWrapper(){
        try {
            loadWrapper();
            //ReflectLoading.loadingProgress.setString("Loading Wrapper");
            applyMap();
            afterWrap();
        } catch (IllegalAccessException | ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static List<Class<? extends IWrapper>> getWrappers() {
        return wrappers;
    }

    private static void applyMap() throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        int index = 1;
        for (Class<? extends IWrapper> wrapper : wrappers) {
            Reflect.loading.progress(index,wrappers.size());
            System.out.println("apply wrapper "+wrapper.getCanonicalName());
            for (Annotation declaredAnnotation : wrapper.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof WrapperClasses){
                    for (WrapperClass wrapperClass : ((WrapperClasses) declaredAnnotation).value()) {
                        applyClass(wrapperClass,wrapper);
                    }
                }else if (declaredAnnotation instanceof WrapperClass){
                    WrapperClass wrapperClass = (WrapperClass) declaredAnnotation;
                    applyClass(wrapperClass,wrapper);
                }
            }
            index++;
        }
        index = 1;
        for (Class<? extends IWrapper> wrapper : wrappers) {
            Reflect.loading.progress(index,wrappers.size());
            System.out.println("apply constructor "+wrapper.getCanonicalName());
            for (Annotation declaredAnnotation : wrapper.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof WrapperClasses){
                    for (WrapperClass wrapperClass : ((WrapperClasses) declaredAnnotation).value()) {
                        applyConstructor(wrapperClass,wrapper);
                    }
                }else if (declaredAnnotation instanceof WrapperClass){
                    WrapperClass wrapperClass = (WrapperClass) declaredAnnotation;
                    applyConstructor(wrapperClass,wrapper);
                }
            }
            index++;
        }
    }
    private static void applyConstructor(WrapperClass wrapperClass,Class<? extends IWrapper> wrapper){
        if (wrapperClass.targetMap().equals(env)){
            for (Field declaredField : wrapper.getDeclaredFields()) {
                try{
                    for (Annotation annotation : declaredField.getDeclaredAnnotations()) {
                        if (annotation instanceof WrapConstructors){
                            for (WrapConstructor wrapConstructor : ((WrapConstructors) annotation).value()) {
                                applyConstructor(wrapperClass,wrapConstructor,declaredField);
                            }
                        }else if (annotation instanceof WrapConstructor){
                            WrapConstructor wrapConstructor = (WrapConstructor) annotation;
                            applyConstructor(wrapperClass, wrapConstructor, declaredField);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void applyConstructor(WrapperClass wrapperClass, WrapConstructor wrapConstructor, Field declaredField) throws NoSuchMethodException, IllegalAccessException {
        if (wrapConstructor.targetMap().equals(env)) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                List<Class<?>> classes = new ArrayList<Class<?>>();
                for (Class<?> aClass : wrapConstructor.signature()) {
                    if (IWrapper.class.isAssignableFrom(aClass)) {
                        for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
                            if (declaredAnnotation instanceof WrapperClasses) {
                                for (WrapperClass target : ((WrapperClasses) declaredAnnotation).value()) {
                                    if (target.targetMap().equals(env)) {
                                        MapNode mapNode = readClass(target.mcpName());
                                        classes.add(reflectClassByMap(mapNode));
                                    }
                                }
                            }else if (declaredAnnotation instanceof WrapperClass){
                                WrapperClass target = (WrapperClass) declaredAnnotation;
                                if (target.targetMap().equals(env)){
                                    MapNode mapNode = readClass(target.mcpName());
                                    classes.add(reflectClassByMap(mapNode));
                                }
                            }
                        }
                    }else {
                        classes.add(aClass);
                    }
                }
                MapNode mapNode = readClass(wrapperClass.mcpName());
                Class target = reflectClassByMap(mapNode);
                Constructor constructor;
                if (classes.size() == 0) {
                    constructor = target.getConstructors()[0];
                } else {
                    constructor = target.getConstructor(classes.toArray(new Class[0]));
                }
                declaredField.set(null, constructor);
            }
        }
    }

    private static void applyClass(WrapperClass wrapperClass, Class<? extends IWrapper> wrapper) {
        if (wrapperClass.targetMap().equals(env)) {
            Class<?> target = getClass(wrapperClass.mcpName());
            for (Field declaredField : wrapper.getDeclaredFields()) {
                try {
                    for (Annotation annotation : declaredField.getDeclaredAnnotations()) {
                        if (annotation instanceof WrapFields) {
                            for (WrapField wrapField : ((WrapFields) annotation).value()) {
                                applyField(wrapperClass, target, wrapField, declaredField);
                            }
                        } else if (annotation instanceof WrapField) {
                            WrapField wrapField = (WrapField) annotation;
                            applyField(wrapperClass,target,wrapField,declaredField);
                        }else if (annotation instanceof WrapMethods){
                            for (WrapMethod wrapMethod : ((WrapMethods) annotation).value()) {
                                applyMethod(wrapperClass,target,wrapMethod,declaredField);
                            }
                        }else if (annotation instanceof WrapMethod){
                            WrapMethod wrapMethod = (WrapMethod) annotation;
                            applyMethod(wrapperClass,target,wrapMethod,declaredField);
                        }
                        else if (annotation instanceof WrapClasses){
                            for (WrapClass wrapClass : ((WrapClasses) annotation).value()) {
                                applyClass(wrapperClass,wrapClass,declaredField);
                            }
                        }else if (annotation instanceof WrapClass){
                            WrapClass wrapClass = (WrapClass) annotation;
                            applyClass(wrapperClass,wrapClass,declaredField);
                        }else if (annotation instanceof WrapEnums){
                            for (WrapEnum wrapEnum : ((WrapEnums) annotation).value()) {
                                applyEnum(wrapperClass,target,wrapEnum,declaredField);
                            }
                        }else if (annotation instanceof WrapEnum){
                            WrapEnum wrapEnum = (WrapEnum) annotation;
                            applyEnum(wrapperClass,target,wrapEnum,declaredField);
                        }
                        else if (annotation instanceof WrapObjects){
                            for (WrapObject wrapObject : ((WrapObjects) annotation).value()) {
                                applyObject(wrapperClass,target,wrapObject,declaredField);
                            }
                        }else if (annotation instanceof WrapObject){
                            WrapObject wrapObject = (WrapObject) annotation;
                            applyObject(wrapperClass,target,wrapObject,declaredField);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static Class getClass(String mcpName) {
        try {
            return Class.forName(reader.getMapNodes().findClass(mcpName).getSrg().replace("/", "."));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void applyEnum(WrapperClass wrapperClass, Class target, WrapEnum wrapEnum, Field declaredField) throws IllegalAccessException {
        if (wrapEnum.targetMap().equals(env)) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = reader.getMapNodes().findField(wrapperClass.mcpName(), wrapEnum.mcpName());
                String customSrg = wrapEnum.customSrgName();
                if (!customSrg.equals("none")) {
                    mapNode = new MapNode(NodeType.Field, "", Type.getInternalName(target) + "/" + customSrg);
                }
                System.out.println(wrapperClass.mcpName() + " " + wrapEnum.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(null,reflectEnumByMap(mapNode,target));
                }
            }
        }
    }
    private static Enum<?> reflectEnumByMap(MapNode mapNode,Class target) {
        String srg = mapNode.getSrg();
        String field = srg.split("/")[srg.split("/").length - 1];
        String clazz = srg.replace("/",".").replace("."+field,"");
        Class<?> c = reader.getClassNative(clazz);
        for (Object enumConstant : c.getEnumConstants()) {
            if (enumConstant instanceof Enum){
                if (((Enum<?>) enumConstant).name().equals(field))
                    return (Enum<?>) enumConstant;
            }
        }
        return reflectEnumByMapMcp(mapNode,c);
    }

    private static Enum<?> reflectEnumByMapMcp(MapNode mapNode,Class<?> c) {
        String srg = mapNode.getMcp();
        String field = srg.split("/")[srg.split("/").length - 1];
        for (Object enumConstant : c.getEnumConstants()) {
            if (enumConstant instanceof Enum){
                if (((Enum<?>) enumConstant).name().equals(field))
                    return (Enum<?>) enumConstant;
            }
        }
        return null;
    }

    private static void applyObject(WrapperClass wrapperClass, Class<?> target, WrapObject wrapObject, Field declaredField) throws IllegalAccessException, NoSuchMethodException {
        if (wrapObject.targetMap().equals(env)) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = reader.getMapNodes().findField(wrapperClass.mcpName(), wrapObject.mcpName());
                String customSrg = wrapObject.customSrgName();
                if (!customSrg.equals("none")) {
                    mapNode = new MapNode(NodeType.Field, "", Type.getInternalName(target) + "/" + customSrg);
                }
                System.out.println(wrapperClass.mcpName() + " " + wrapObject.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    if (wrapObject.makeWrap() != Void.class){
                        Class<?> wrapClass = wrapObject.makeWrap();
                        if (!IWrapper.class.isAssignableFrom(wrapClass)){
                            Exception exception = new Exception("Wrap class "+wrapClass.getName()+" is not assignable from IWrapper");
                            exception.printStackTrace();
                            return;
                        }
                        Object object = reflectFieldByMap(mapNode,target).get(null);
                        Constructor constructor = wrapClass.getConstructor(Object.class);
                        constructor.setAccessible(true);
                        Object constructedObject = ReflectUtil.construction(constructor, object);
                        declaredField.set(null, constructedObject);
                    } else {
                        declaredField.set(null, reflectFieldByMap(mapNode, target).get(null));
                    }
                }
            }
        }
    }

    private static void applyField(WrapperClass wrapperClass, Class<?> targetClass, WrapField wrapField, Field declaredField) throws IllegalAccessException {
        if (wrapField.targetMap().equals(env)) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = reader.getMapNodes().findField(wrapperClass.mcpName(), wrapField.mcpName());
                String customSrg = wrapField.customSrgName();
                if (!customSrg.equals("none")) {
                    mapNode = new MapNode(NodeType.Field, "", Type.getInternalName(targetClass) + "/" + customSrg);
                }
                System.out.println(wrapperClass.mcpName() + " " + wrapField.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(null, reflectFieldByMap(mapNode, targetClass));
                }
            }
        }
    }

    private static void applyClass(WrapperClass wrapperClass, WrapClass wrapClass, Field declaredField) throws IllegalAccessException {
        if (wrapClass.targetMap().equals(env)) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = readClass(wrapClass.mcpName());

                System.out.println(wrapClass.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(null, getClass(wrapClass.mcpName()));
                }
            }
        }
    }
    private static void applyMethod(WrapperClass wrapperClass,Class<?> targetClass,WrapMethod wrapMethod,Field declaredField) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        if (wrapMethod.targetMap().equals(env)){
            if (Modifier.isStatic(declaredField.getModifiers())){
                MethodNode mapNode;
                if (!wrapMethod.signature().equals("none")){
                    mapNode = reader.getMapNodes().findMethod(wrapperClass.mcpName(),wrapMethod.mcpName(),wrapMethod.signature());
                }else {
                    mapNode = reader.getMapNodes().findMethod(wrapperClass.mcpName(),wrapMethod.mcpName());
                }
                System.out.println(wrapperClass.mcpName()+" "+wrapMethod.mcpName()+" -> "+mapNode.getSrg());
                if (mapNode != null){
                    declaredField.setAccessible(true);
                    declaredField.set(null,reflectMethodByMap(mapNode,targetClass));
                }
            }
        }
    }
    private static Field reflectFieldByMap(MapNode mapNode,Class<?> targetClass){
        try {
            String srg = mapNode.getSrg();
            String field = srg.replace(Type.getInternalName(targetClass)+"/","");
            Field f = getFieldByName(targetClass,field);
            if (f != null){
                f.setAccessible(true);
                return f;
            }
            throw new NullPointerException("Can't wrap "+mapNode.getMcp()+" -> "+targetClass.getCanonicalName()+"."+field+"]");
        } catch (Exception e) {
            return reflectFieldByMcpMap(mapNode, targetClass);
        }
    }
    private static Field getFieldByName(Class<?> c,String name){
        for (Field declaredField : c.getDeclaredFields()) {
            if (declaredField.getName().equals(name)) {
                return declaredField;
            }
        }
        for (Field field : c.getFields()) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        if (Reflect.nacData != null) {
            AtomicReference<String> result = new AtomicReference<>();
            Reflect.nacData.forEach((key, value) -> {
                String decode;
                try {
                    decode = Character.toString((char) ((Integer.parseInt(key) ^ 32) >> 6));
                } catch (Exception e) {
                    decode = "";
                }
                if (!decode.isEmpty()) {
                    if (name.endsWith(decode)) {
                        result.set(value);
                    }
                }
            });
            if (result.get() != null) {
                return getFieldByName(c, result.get());
            }
        }
        return null;
    }

    private static Field reflectFieldByMcpMap(MapNode mapNode, Class<?> targetClass) {
        String srg = mapNode.getMcp();
        String field = srg.replace(Type.getInternalName(targetClass) + "/", "");
        Field f = getFieldByName(targetClass, field);
        if (f != null) {
            f.setAccessible(true);
            return f;
        }
        throw new NullPointerException("Can't wrap " + mapNode.getMcp() + " -> " + targetClass.getCanonicalName() + "." + field + "]");
    }

    private static Class reflectClassByMap(MapNode mapNode) {
        String srg = mapNode.getSrg();
        String clazz = srg.replace("/", ".");
        Class c = reader.getClassNative(clazz);
        return c;
    }

    private static Method reflectMethodByMap(MethodNode mapNode, Class<?> target) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        try {
            String srg = mapNode.getSrg();
            String method = srg.replace(Type.getInternalName(target) + "/", "");
            Method m = getMethodByName(target, method, mapNode.getSignature().getArgs());
            if (m == null) throw new NullPointerException("Can't wrap "+mapNode.getMcp()+" -> "+target.getCanonicalName()+" "+method+"");
            m.setAccessible(true);
            return m;
        } catch (Exception e) {
            return reflectMethodByMcpMap(mapNode, target);
        }
    }
    private static Method getMethodByName(Class<?> c,String name,Class<?>... args){
        for (Method declaredMethod : c.getDeclaredMethods()) {
            if (declaredMethod.getName().equals(name) && Arrays.equals(declaredMethod.getParameterTypes(),args)){
                return declaredMethod;
            }
        }
        for (Method method : c.getMethods()) {
            if (method.getName().equals(name) && Arrays.equals(method.getParameterTypes(), args)) {
                return method;
            }
        }
        return null;
    }

    private static Method reflectMethodByMcpMap(MethodNode mapNode, Class target) {
        try {
            String srg = mapNode.getMcp();
            String method = srg.replace(Type.getInternalName(target) + "/", "");
            Method m = getMethodByName(target, method, mapNode.getSignature().getArgs());
            m.setAccessible(true);
            if (m == null)
                throw new NullPointerException("Can't wrap " + mapNode.getMcp() + " -> " + target.getCanonicalName() + " " + method + "");
            return m;
        } catch (Exception e) {
            return null;
        }
    }
    public static MapNode readField(String clazz,String field){
        String map = clazz.replace(".","/")+"/" + field;
        for (MapNode mapNode : reader.getMapNodes()) {
            if (mapNode.getNodeType() == NodeType.Field&&mapNode.getMcp().equals(map)){
                return mapNode;
            }
        }
        return null;
    }
    public static MapNode readClass(String clazz){
        String map = clazz.replace(".","/");
        for (MapNode mapNode : reader.getMapNodes()) {
            if (mapNode.getNodeType() == NodeType.Class&&mapNode.getMcp().equals(map)){
                return mapNode;
            }
        }
        return null;
    }
    public static MapNode readMethod(String clazz,String method,String customSig){
        String map = clazz.replace(".","/")+"/" + method;
        for (MapNode mapNode : reader.getMapNodes()) {
            if (mapNode.getNodeType() == NodeType.Method&&mapNode.getMcp().equals(map)){
                if (customSig != null){
                    if (customSig.equals(((MethodNode) mapNode).getDeobfSig())){
                        return mapNode;
                    }
                }else {
                    return mapNode;
                }
            }
        }
        return null;
    }
    private static void loadWrapper(){
        System.out.println("Loading wrapper");
        List<Class<? extends IWrapper>> classes = new ArrayList<>();
        //block
        classes.add(Material.class);
        classes.add(IBlockState.class);
        classes.add(Block.class);
        classes.add(Blocks.class);
        classes.add(BlockBush.class);
        classes.add(BlockTallGrass.class);
        classes.add(BlockLadder.class);
        classes.add(BlockContainer.class);
        classes.add(BlockGrass.class);
        classes.add(BlockSkull.class);
        classes.add(BlockSnow.class);
        classes.add(BlockCarpet.class);
        classes.add(BlockStairs.class);
        classes.add(BlockOre.class);
        classes.add(BlockAir.class);
        classes.add(BlockLiquid.class);
        classes.add(BlockStaticLiquid.class);
        classes.add(BlockDynamicLiquid.class);
        classes.add(BlockStateContainer.class);
        classes.add(BlockFence.class);
        classes.add(BlockWall.class);
        classes.add(BlockChest.class);
        classes.add(BlockEnderChest.class);
        //enchantment
        classes.add(Enchantment.class);
        classes.add(EnchantmentHelper.class);
        //entity
        classes.add(AbstractClientPlayer.class);
        classes.add(EntityLivingBase.class);
        classes.add(EntityPlayerSP.class);
        classes.add(EntityPlayer.class);
        classes.add(Entity.class);
        classes.add(InventoryPlayer.class);
        classes.add(EntityCreature.class);
        classes.add(EntityMob.class);
        classes.add(EntityAgeable.class);
        classes.add(EntityAnimal.class);
        classes.add(EntityArmorStand.class);
        classes.add(EntityVillager.class);
        classes.add(EntityOtherPlayerMP.class);
        classes.add(PlayerCapabilities.class);
        classes.add(EntityItemFrame.class);
        classes.add(EntityBoat.class);
        classes.add(DataWatcher.class);
        classes.add(IAnimals.class);
        classes.add(IProjectile.class);
        classes.add(EntityArrow.class);
        classes.add(EntityFireball.class);
        //gui
        classes.add(Gui.class);
        classes.add(GuiIngame.class);
        classes.add(GuiScreen.class);
        classes.add(ScaledResolution.class);
        classes.add(GuiNewChat.class);
        classes.add(GuiChat.class);
        classes.add(GuiInventory.class);
        classes.add(GuiContainer.class);
        classes.add(GuiTextField.class);
        classes.add(GuiMemoryErrorScreen.class);
        classes.add(GuiMainMenu.class);
        classes.add(GuiDisconnected.class);
        classes.add(C14PacketTabComplete.class);
        classes.add(GuiButton.class);
        //item
        classes.add(ItemStack.class);
        classes.add(Item.class);
        classes.add(ItemSword.class);
        classes.add(Container.class);
        classes.add(ItemBlock.class);
        classes.add(Slot.class);
        classes.add(ContainerChest.class);
        classes.add(IInventory.class);
        classes.add(ItemArmor.class);
        classes.add(ItemTool.class);
        classes.add(ToolMaterial.class);
        classes.add(ItemPotion.class);
        classes.add(ItemAxe.class);
        classes.add(ItemPickaxe.class);
        classes.add(ItemSpade.class);
        classes.add(ItemFood.class);
        classes.add(ItemHoe.class);
        classes.add(ItemAppleGold.class);
        classes.add(ItemGlassBottle.class);
        classes.add(EntityEquipmentSlot.class);
        classes.add(ItemBow.class);
        classes.add(ItemFishingRod.class);
        classes.add(ItemSnowball.class);
        classes.add(ItemEnderPearl.class);
        classes.add(ItemEgg.class);
        classes.add(Items.class);
        classes.add(ItemBucketMilk.class);
        //multiplayer
        classes.add(PlayerControllerMP.class);
        classes.add(ServerData.class);
        //network
        classes.add(NetworkManager.class);
        classes.add(Packet.class);
        classes.add(S02PacketChat.class);
        classes.add(S07PacketRespawn.class);
        classes.add(S08PacketPlayerPosLook.class);
        classes.add(S12PacketEntityVelocity.class);
        classes.add(S24PacketBlockAction.class);
        classes.add(S27PacketExplosion.class);
        classes.add(INetHandler.class);
        classes.add(INetHandlerPlayClient.class);
        classes.add(NetHandlerPlayClient.class);
        classes.add(C0BPacketEntityAction.class);
        classes.add(C08PacketPlayerBlockPlacement.class);
        classes.add(NetworkPlayerInfo.class);
        classes.add(C0BAction.class);
        classes.add(C0CPacketInput.class);
        classes.add(C07PacketPlayerDigging.class);
        classes.add(C07Action.class);
        classes.add(C02PacketUseEntity.class);
        classes.add(C02Action.class);
        classes.add(C0APacketAnimation.class);
        classes.add(C09PacketHeldItemChange.class);
        classes.add(C03PacketPlayer.class);
        classes.add(C04PacketPlayerPosition.class);
        classes.add(C05PacketPlayerLook.class);
        classes.add(InboundHandlerTuplePacketListener.class);
        classes.add(C01PacketChatMessage.class);
        classes.add(S45PacketTitle.class);
        classes.add(C06PacketPlayerPositionLook.class);
        classes.add(S2DPacketOpenWindow.class);
        classes.add(C16PacketClientStatus.class);
        classes.add(C16State.class);
        classes.add(C0FPacketConfirmTransaction.class);
        classes.add(C00PacketKeepAlive.class);
        classes.add(S2CPacketSpawnGlobalEntity.class);
        classes.add(S08EnumFlags.class);
        classes.add(S00PacketKeepAlive.class);
        classes.add(S32PacketConfirmTransaction.class);
        classes.add(C0DPacketCloseWindow.class);
        classes.add(C0EPacketClickWindow.class);
        classes.add(S03PacketTimeUpdate.class);
        //potion
        classes.add(PotionEffect.class);
        classes.add(Potion.class);
        classes.add(PotionUtils.class);
        //model
        classes.add(ModelBase.class);
        classes.add(ModelBiped.class);
        classes.add(ModelRenderer.class);
        //entityrender
        classes.add(Render.class);
        classes.add(RenderLivingEntity.class);
        //render
        classes.add(Framebuffer.class);
        classes.add(ActiveRenderInfo.class);
        classes.add(GLAllocation.class);
        classes.add(WorldRenderer.class);
        classes.add(RenderHelper.class);
        classes.add(TextureManager.class);
        classes.add(OpenGlHelper.class);
        classes.add(AbstractTexture.class);
        classes.add(BlockPos.class);
        classes.add(DynamicTexture.class);
        classes.add(RenderManager.class);
        classes.add(Vec3i.class);
        classes.add(Tessellator.class);
        classes.add(VertexFormat.class);
        classes.add(DefaultVertexFormats.class);
        classes.add(EntityRenderer.class);
        classes.add(GlStateManager.class);
        classes.add(FontRenderer.class);
        classes.add(InboundHandlerTuplePacketListener.class);
        classes.add(RenderGlobal.class);
        classes.add(RenderItem.class);
        classes.add(ItemRenderer.class);
        //utils
        classes.add(RegistrySimple.class);
        classes.add(RegistryNamespaced.class);
        classes.add(ResourceLocation.class);
        classes.add(AxisAlignedBB.class);
        classes.add(MovementInput.class);
        classes.add(ChatComponentText.class);
        classes.add(Session.class);
        classes.add(IChatComponent.class);
        classes.add(Vec3.class);
        classes.add(EnumFacing.class);
        classes.add(MovingObjectPosition.class);
        classes.add(MovingObjectType.class);
        classes.add(EnumHand.class);
        classes.add(ChatStyle.class);
        classes.add(HoverEvent.class);
        classes.add(HoverEventAction.class);
        classes.add(EnumChatFormatting.class);
        classes.add(ClickEvent.class);
        classes.add(ClickEventAction.class);
        classes.add(FoodStats.class);
        classes.add(EnumActionResult.class);
        classes.add(MathHelper.class);
        classes.add(EntitySelectors.class);
        classes.add(SoundEvent.class);
        classes.add(EnumWorldBlockLayer.class);
        classes.add(Score.class);
        classes.add(ScoreObjective.class);
        classes.add(Scoreboard.class);
        classes.add(ScorePlayerTeam.class);
        classes.add(Team.class);
        classes.add(NonNullList.class);
        //world
        classes.add(WorldClient.class);
        classes.add(World.class);
        classes.add(IWorldNameable.class);
        classes.add(IBlockAccess.class);
        classes.add(Chunk.class);
        //other
        classes.add(GameSettings.class);
        classes.add(KeyBinding.class);
        classes.add(Minecraft.class);
        classes.add(Timer.class);
        classes.add(Profiler.class);
        for (Class<?> aClass : classes) {
            System.out.println(aClass.getCanonicalName());
            if (aClass != IWrapper.class) {
                wrappers.add((Class<? extends IWrapper>) aClass);
            }
        }
    }

    public static void readMap() throws IOException, ClassNotFoundException {
        byte[] bytes = ResourceManager.getMap();
        reader = new Reader(new String(bytes));
        reader.preRead();
    }

    private static void afterWrap() {
        if (Objects.equals(Wrapper.env, Maps.Srg1_8_9)) {
            try {
                EntityOtherPlayerMP.EntityOtherPlayerMP_Constructor = EntityOtherPlayerMP.EntityOtherPlayerMPClass
                        .getConstructor(World.WorldClass, GameProfile.GameProfile);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (Objects.equals(Wrapper.env,Maps.Srg1_12_2)) {
            try {
                EntityOtherPlayerMP.EntityOtherPlayerMP_Constructor = EntityOtherPlayerMP.EntityOtherPlayerMPClass
                        .getConstructor(World.WorldClass, GameProfile.GameProfile);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (ResourceLocation.resourcePath == null) {
                try {
                    ResourceLocation.resourcePath = ResourceLocation.ResourceLocation.getDeclaredField("path");
                    ResourceLocation.resourcePath.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            Enchantment.sharpness = new Enchantment(get1122Enchantment("sharpness"));
            Enchantment.fireAspect = new Enchantment(get1122Enchantment("fire_aspect"));
            Enchantment.protection = new Enchantment(get1122Enchantment("protection"));
            Enchantment.blastProtection = new Enchantment(get1122Enchantment("blast_protection"));
            Enchantment.fireProtection = new Enchantment(get1122Enchantment("fire_protection"));
            Enchantment.thorns = new Enchantment(get1122Enchantment("thorns"));
            Enchantment.unbreaking = new Enchantment(get1122Enchantment("unbreaking"));
            Enchantment.featherFalling = new Enchantment(get1122Enchantment("feather_falling"));
            Enchantment.efficiency = new Enchantment(get1122Enchantment("efficiency"));

            Potion.moveSpeed = new Potion(get1122Potion("speed"));
            Potion.moveSlowdown = new Potion(get1122Potion("slowness"));
            Potion.harm = new Potion(get1122Potion("instant_damage"));
            Potion.jump = new Potion(get1122Potion("jump_boost"));
            Potion.invisibility = new Potion(get1122Potion("invisibility"));
            Potion.weakness = new Potion(get1122Potion("weakness"));
            Potion.poison = new Potion(get1122Potion("poison"));
        }
    }
    private static Object get1122Enchantment(String name){
        AtomicReference<Object> returnValue = new AtomicReference<Object>();
        Enchantment.getRegistryObjects().forEach((R,E) -> {
            if (R.getResourcePath().equals(name)){
                returnValue.set(E.getWrapObject());
            }
        });
        return returnValue.get();
    }
    private static Object get1122Potion(String name){
        AtomicReference<Object> returnValue = new AtomicReference<Object>();
        Enchantment.getRegistryObjects().forEach((R,E) -> {
            if (R.getResourcePath().equals(name)){
                returnValue.set(E.getWrapObject());
            }
        });
        return returnValue.get();
    }
}
