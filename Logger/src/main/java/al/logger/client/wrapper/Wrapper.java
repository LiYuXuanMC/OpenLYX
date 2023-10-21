package al.logger.client.wrapper;

import al.logger.client.Logger;
import al.logger.client.utils.ReflectUtil;
import al.logger.client.wrapper.LoggerMC.*;
import al.logger.client.wrapper.LoggerMC.block.*;
import al.logger.client.wrapper.LoggerMC.block.state.BlockState;
import al.logger.client.wrapper.LoggerMC.enchantment.Enchantment;
import al.logger.client.wrapper.LoggerMC.enchantment.EnchantmentHelper;
import al.logger.client.wrapper.LoggerMC.entity.*;
import al.logger.client.wrapper.LoggerMC.entity.player.EnumPlayerModelParts;
import al.logger.client.wrapper.LoggerMC.gui.*;
import al.logger.client.wrapper.LoggerMC.item.*;
import al.logger.client.wrapper.LoggerMC.item.Container;
import al.logger.client.wrapper.LoggerMC.model.ModelBase;
import al.logger.client.wrapper.LoggerMC.model.ModelBiped;
import al.logger.client.wrapper.LoggerMC.model.ModelPlayer;
import al.logger.client.wrapper.LoggerMC.model.ModelRenderer;
import al.logger.client.wrapper.LoggerMC.multiplayer.PlayerControllerMP;
import al.logger.client.wrapper.LoggerMC.multiplayer.ServerData;
import al.logger.client.wrapper.LoggerMC.network.*;
import al.logger.client.wrapper.LoggerMC.network.client.*;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C05PacketPlayerLook;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C06PacketPlayerPositionLook;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07PacketPlayerDigging;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.client.C16.C16PacketClientStatus;
import al.logger.client.wrapper.LoggerMC.network.client.C16.C16State;
import al.logger.client.wrapper.LoggerMC.network.server.*;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08EnumFlags;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import al.logger.client.wrapper.LoggerMC.network.server.S38.AddPlayerData;
import al.logger.client.wrapper.LoggerMC.network.server.S38.S38Action;
import al.logger.client.wrapper.LoggerMC.network.server.S38.S38PacketPlayerListItem;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.potion.PotionEffect;
import al.logger.client.wrapper.LoggerMC.potion.PotionUtils;
import al.logger.client.wrapper.LoggerMC.render.*;
import al.logger.client.wrapper.LoggerMC.render.entity.Render;
import al.logger.client.wrapper.LoggerMC.render.entity.RenderPlayer;
import al.logger.client.wrapper.LoggerMC.render.entity.RendererLivingEntity;
import al.logger.client.wrapper.LoggerMC.resource.*;
import al.logger.client.wrapper.LoggerMC.shader.Framebuffer;
import al.logger.client.wrapper.LoggerMC.utils.*;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;
import al.logger.client.wrapper.LoggerMC.utils.event.HoverEvent;
import al.logger.client.wrapper.LoggerMC.utils.event.HoverEventAction;
import al.logger.client.wrapper.LoggerMC.utils.event.SoundEvent;
import al.logger.client.wrapper.LoggerMC.utils.event.click.ClickEvent;
import al.logger.client.wrapper.LoggerMC.utils.event.click.ClickEventAction;
import al.logger.client.wrapper.LoggerMC.utils.registry.RegistryNamespaced;
import al.logger.client.wrapper.LoggerMC.utils.registry.RegistrySimple;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.*;
import al.logger.client.wrapper.LoggerMC.utils.text.ChatComponentText;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;
import al.logger.client.wrapper.LoggerMC.world.*;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.annotations.repeat.*;
import al.logger.client.wrapper.cactus.CactusWrappingInfo;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.client.wrapper.map.*;
import al.logger.client.wrapper.map.utils.Signature;
import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Native
public class Wrapper {
    @Getter
    private static final List<Class<? extends IWrapper>> wrappers = new ArrayList<>();
    private static MethodHandles.Lookup lookup = MethodHandles.lookup();
    @Getter
    private final static List<CactusWrappingInfo> cactusWrapping = new ArrayList<>();
    public static void init(){
        Logger.getInstance().getMapParser().parse();
        wrappers.add(Material.class);
        wrappers.add(IBlockState.class);
        wrappers.add(Block.class);
        wrappers.add(Blocks.class);
        wrappers.add(BlockBush.class);
        wrappers.add(BlockTallGrass.class);
        wrappers.add(BlockLadder.class);
        wrappers.add(BlockContainer.class);
        wrappers.add(BlockGrass.class);
        wrappers.add(BlockSkull.class);
        wrappers.add(BlockSnow.class);
        wrappers.add(BlockCarpet.class);
        wrappers.add(BlockStairs.class);
        wrappers.add(BlockOre.class);
        wrappers.add(BlockAir.class);
        wrappers.add(BlockLiquid.class);
        wrappers.add(BlockStaticLiquid.class);
        wrappers.add(BlockDynamicLiquid.class);
        wrappers.add(BlockStateContainer.class);
        wrappers.add(BlockFence.class);
        wrappers.add(BlockWall.class);
        wrappers.add(BlockChest.class);
        wrappers.add(BlockEnderChest.class);
        wrappers.add(BlockState.class);
        wrappers.add(BlockIce.class);
        wrappers.add(BlockPackedIce.class);
        wrappers.add(BlockSlime.class);
        //enchantment
        wrappers.add(Enchantment.class);
        wrappers.add(EnchantmentHelper.class);
        //entity
        wrappers.add(AbstractClientPlayer.class);
        wrappers.add(EntityLivingBase.class);
        wrappers.add(EntityPlayerSP.class);
        wrappers.add(EntityPlayer.class);
        wrappers.add(Entity.class);
        wrappers.add(InventoryPlayer.class);
        wrappers.add(EntityCreature.class);
        wrappers.add(EntityMob.class);
        wrappers.add(EntityAgeable.class);
        wrappers.add(EntityAnimal.class);
        wrappers.add(EntityArmorStand.class);
        wrappers.add(EntityVillager.class);
        wrappers.add(EntityOtherPlayerMP.class);
        wrappers.add(PlayerCapabilities.class);
        wrappers.add(EntityItemFrame.class);
        wrappers.add(EntityBoat.class);
        wrappers.add(DataWatcher.class);
        wrappers.add(IAnimals.class);
        wrappers.add(IProjectile.class);
        wrappers.add(EntityArrow.class);
        wrappers.add(EntityFireball.class);
        wrappers.add(EnumPlayerModelParts.class);
        //gui
        wrappers.add(Gui.class);
        wrappers.add(GuiIngame.class);
        wrappers.add(GuiScreen.class);
        wrappers.add(ScaledResolution.class);
        wrappers.add(GuiNewChat.class);
        wrappers.add(GuiChat.class);
        wrappers.add(GuiInventory.class);
        wrappers.add(GuiContainer.class);
        wrappers.add(GuiTextField.class);
        wrappers.add(GuiMemoryErrorScreen.class);
        wrappers.add(GuiMainMenu.class);
        wrappers.add(GuiDisconnected.class);
        wrappers.add(GuiButton.class);
        //item
        wrappers.add(ItemStack.class);
        wrappers.add(Item.class);
        wrappers.add(ItemSword.class);
        wrappers.add(Container.class);
        wrappers.add(ItemBlock.class);
        wrappers.add(Slot.class);
        wrappers.add(ContainerChest.class);
        wrappers.add(IInventory.class);
        wrappers.add(ItemArmor.class);
        wrappers.add(ItemTool.class);
        wrappers.add(ToolMaterial.class);
        wrappers.add(ItemPotion.class);
        wrappers.add(ItemAxe.class);
        wrappers.add(ItemPickaxe.class);
        wrappers.add(ItemSpade.class);
        wrappers.add(ItemFood.class);
        wrappers.add(ItemHoe.class);
        wrappers.add(ItemAppleGold.class);
        wrappers.add(ItemGlassBottle.class);
        wrappers.add(EntityEquipmentSlot.class);
        wrappers.add(ItemBow.class);
        wrappers.add(ItemFishingRod.class);
        wrappers.add(ItemSnowball.class);
        wrappers.add(ItemEnderPearl.class);
        wrappers.add(ItemEgg.class);
        wrappers.add(Items.class);
        wrappers.add(ItemBucketMilk.class);
        //multiplayer
        wrappers.add(PlayerControllerMP.class);
        wrappers.add(ServerData.class);
        //network
        wrappers.add(NetworkManager.class);
        wrappers.add(Packet.class);
        wrappers.add(S02PacketChat.class);
        wrappers.add(S07PacketRespawn.class);
        wrappers.add(S08PacketPlayerPosLook.class);
        wrappers.add(S12PacketEntityVelocity.class);
        wrappers.add(S24PacketBlockAction.class);
        wrappers.add(S27PacketExplosion.class);
        wrappers.add(INetHandler.class);
        wrappers.add(INetHandlerPlayClient.class);
        wrappers.add(NetHandlerPlayClient.class);
        wrappers.add(C0BPacketEntityAction.class);
        wrappers.add(C08PacketPlayerBlockPlacement.class);
        wrappers.add(NetworkPlayerInfo.class);
        wrappers.add(C0BAction.class);
        wrappers.add(C0CPacketInput.class);
        wrappers.add(C07PacketPlayerDigging.class);
        wrappers.add(C07Action.class);
        wrappers.add(C02PacketUseEntity.class);
        wrappers.add(C02Action.class);
        wrappers.add(C0APacketAnimation.class);
        wrappers.add(C09PacketHeldItemChange.class);
        wrappers.add(C03PacketPlayer.class);
        wrappers.add(C04PacketPlayerPosition.class);
        wrappers.add(C05PacketPlayerLook.class);
        wrappers.add(C01PacketChatMessage.class);
        wrappers.add(S45PacketTitle.class);
        wrappers.add(C06PacketPlayerPositionLook.class);
        wrappers.add(S2DPacketOpenWindow.class);
        wrappers.add(S2EPacketCloseWindow.class);
        wrappers.add(C16PacketClientStatus.class);
        wrappers.add(C16State.class);
        wrappers.add(C0FPacketConfirmTransaction.class);
        wrappers.add(C00PacketKeepAlive.class);
        wrappers.add(S2CPacketSpawnGlobalEntity.class);
        wrappers.add(S08EnumFlags.class);
        wrappers.add(S00PacketKeepAlive.class);
        wrappers.add(S32PacketConfirmTransaction.class);
        wrappers.add(C0DPacketCloseWindow.class);
        wrappers.add(C0EPacketClickWindow.class);
        wrappers.add(S03PacketTimeUpdate.class);
        wrappers.add(C14PacketTabComplete.class);
        wrappers.add(C17PacketCustomPayload.class);
        wrappers.add(S38PacketPlayerListItem.class);
        wrappers.add(S38Action.class);
        wrappers.add(AddPlayerData.class);
        wrappers.add(S41PacketServerDifficulty.class);
        wrappers.add(S09PacketHeldItemChange.class);
        wrappers.add(S0CPacketSpawnPlayer.class);
        wrappers.add(S18PacketEntityTeleport.class);
        //potion
        wrappers.add(PotionEffect.class);
        wrappers.add(Potion.class);
        wrappers.add(PotionUtils.class);
        //model
        wrappers.add(ModelBase.class);
        wrappers.add(ModelBiped.class);
        wrappers.add(ModelRenderer.class);
        wrappers.add(ModelPlayer.class);
        //entityrender
        wrappers.add(Render.class);
        wrappers.add(RendererLivingEntity.class);
        wrappers.add(RenderPlayer.class);
        //render
        wrappers.add(Framebuffer.class);
        //wrappers.add(ActiveRenderInfo.class);
        wrappers.add(GLAllocation.class);
        wrappers.add(WorldRenderer.class);
        wrappers.add(RenderHelper.class);
        wrappers.add(TextureManager.class);
        wrappers.add(OpenGlHelper.class);
        wrappers.add(AbstractTexture.class);
        wrappers.add(DynamicTexture.class);
        wrappers.add(RenderManager.class);
        wrappers.add(Tessellator.class);
        wrappers.add(VertexFormat.class);
        wrappers.add(DefaultVertexFormats.class);
        wrappers.add(EntityRenderer.class);
        wrappers.add(GlStateManager.class);
        wrappers.add(FontRenderer.class);
        wrappers.add(RenderGlobal.class);
        wrappers.add(RenderItem.class);
        wrappers.add(ItemRenderer.class);
        wrappers.add(ActiveRenderInfo.class);
        //utils
        wrappers.add(BlockPos.class);
        wrappers.add(Vec3i.class);
        wrappers.add(RegistrySimple.class);
        wrappers.add(RegistryNamespaced.class);
        wrappers.add(ResourceLocation.class);
        wrappers.add(AxisAlignedBB.class);
        wrappers.add(MovementInput.class);
        wrappers.add(ChatComponentText.class);
        wrappers.add(Session.class);
        wrappers.add(IChatComponent.class);
        wrappers.add(Vec3.class);
        wrappers.add(EnumFacing.class);
        wrappers.add(MovingObjectPosition.class);
        wrappers.add(MovingObjectType.class);
        wrappers.add(EnumHand.class);
        wrappers.add(ChatStyle.class);
        wrappers.add(HoverEvent.class);
        wrappers.add(HoverEventAction.class);
        wrappers.add(EnumChatFormatting.class);
        wrappers.add(ClickEvent.class);
        wrappers.add(ClickEventAction.class);
        wrappers.add(FoodStats.class);
        wrappers.add(EnumActionResult.class);
        wrappers.add(MathHelper.class);
        wrappers.add(EntitySelectors.class);
        wrappers.add(SoundEvent.class);
        wrappers.add(EnumWorldBlockLayer.class);
        wrappers.add(Score.class);
        wrappers.add(ScoreObjective.class);
        wrappers.add(Scoreboard.class);
        wrappers.add(ScorePlayerTeam.class);
        wrappers.add(Team.class);
        wrappers.add(NonNullList.class);
        //world
        wrappers.add(WorldClient.class);
        wrappers.add(World.class);
        wrappers.add(IWorldNameable.class);
        wrappers.add(IBlockAccess.class);
        wrappers.add(Chunk.class);
        wrappers.add(WorldSettings.class);
        wrappers.add(GameType.class);
        //other
        wrappers.add(GameSettings.class);
        wrappers.add(KeyBinding.class);
        wrappers.add(Timer.class);
        wrappers.add(Profiler.class);
        wrappers.add(Minecraft.class);
        wrappers.add(GameProfile.class);
        //resource
        wrappers.add(IResourceManager.class);
        wrappers.add(IReloadableResourceManager.class);
        wrappers.add(SimpleReloadableResourceManager.class);
        wrappers.add(IResource.class);
        wrappers.add(IMetadataSection.class);
    }
    public static void wrap(){
        Logger.getInstance().addProgressBar("Wrapping",wrappers.size());
        int i = 0;
        for (Class<? extends IWrapper> wrapper : wrappers) {
            i++;
            Logger.getInstance().setProgressBar("Wrapping",i);
            for (Annotation declaredAnnotation : wrapper.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof WrapperClass){
                    processWrapperClass(wrapper, (WrapperClass) declaredAnnotation);
                }else if (declaredAnnotation instanceof WrapperClasses){
                    for (WrapperClass wrapperClass : ((WrapperClasses) declaredAnnotation).value()) {
                        processWrapperClass(wrapper, wrapperClass);
                    }
                }
            }
        }
        Logger.getInstance().removeProgressBar("Wrapping");
        after();
    }
    private static void processWrapperClass(Class<? extends IWrapper> wrapper,WrapperClass annotation){
        if (!isTargetMap(annotation.targetMap(), EnvironmentDetector.getMinecraft())){
            return;
        }
        Class<?> targetClass = getTargetClass(annotation);
        if (targetClass == null){
            System.out.println("Cannot warp "+wrapper.getCanonicalName());
            return;
        }
        for (Field declaredField : wrapper.getDeclaredFields()) {
            if (!Modifier.isStatic(declaredField.getModifiers())){
                continue;
            }
            if (!declaredField.isAccessible()){
                declaredField.setAccessible(true);
            }
            for (Annotation declaredAnnotation : declaredField.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof ClassInstance){
                    processClassInstance(wrapper,annotation,targetClass,declaredField);
                } else if (declaredAnnotation instanceof WrapField){
                    wrapField(wrapper,annotation,targetClass,(WrapField) declaredAnnotation,declaredField);
                } else if (declaredAnnotation instanceof WrapFields){
                    for (WrapField wrapField : ((WrapFields) declaredAnnotation).value()) {
                        wrapField(wrapper,annotation,targetClass,wrapField,declaredField);
                    }
                } else if (declaredAnnotation instanceof WrapMethod){
                    wrapMethod(wrapper,annotation,targetClass, (WrapMethod) declaredAnnotation,declaredField);
                } else if (declaredAnnotation instanceof WrapMethods){
                    for (WrapMethod wrapMethod : ((WrapMethods) declaredAnnotation).value()) {
                        wrapMethod(wrapper,annotation,targetClass, wrapMethod,declaredField);
                    }
                } else if (declaredAnnotation instanceof WrapObject){
                    wrapObject(wrapper,annotation,targetClass,(WrapObject) declaredAnnotation,declaredField);
                } else if (declaredAnnotation instanceof WrapObjects){
                    for (WrapObject wrapObject : ((WrapObjects) declaredAnnotation).value()) {
                        wrapObject(wrapper,annotation,targetClass,wrapObject,declaredField);
                    }
                } else if (declaredAnnotation instanceof WrapEnum){
                    wrapEnum(wrapper,annotation,targetClass, (WrapEnum) declaredAnnotation,declaredField);
                } else if (declaredAnnotation instanceof WrapEnums){
                    for (WrapEnum wrapEnum : ((WrapEnums) declaredAnnotation).value()) {
                        wrapEnum(wrapper,annotation,targetClass,wrapEnum,declaredField);
                    }
                } else if (declaredAnnotation instanceof WrapConstructor){
                    wrapConstructor(wrapper,annotation,targetClass, (WrapConstructor) declaredAnnotation,declaredField);
                } else if (declaredAnnotation instanceof WrapConstructors){
                    for (WrapConstructor wrapConstructor : ((WrapConstructors) declaredAnnotation).value()) {
                        wrapConstructor(wrapper,annotation,targetClass,wrapConstructor,declaredField);
                    }
                }
            }
        }
    }
    @SneakyThrows
    private static void wrapConstructor(Class<? extends IWrapper> wrapper, WrapperClass wrapperClass, Class<?> targetClass, WrapConstructor wrapConstructor, Field targetField){
        if (!isTargetMap(wrapConstructor.targetMap(), EnvironmentDetector.getMinecraft())){
            return;
        }
        List<Class> classes = new ArrayList<>();
        for (Class<?> aClass : wrapConstructor.signature()) {
            if (IWrapper.class.isAssignableFrom(aClass)){
                for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
                    if (declaredAnnotation instanceof WrapperClass){
                        if (isTargetMap(((WrapperClass) declaredAnnotation).targetMap(),EnvironmentDetector.getMinecraft())){
                            classes.add(getTargetClass((WrapperClass) declaredAnnotation));
                            break;

                        }
                    }else if (declaredAnnotation instanceof WrapperClasses){
                        for (WrapperClass wrapperClass1 : ((WrapperClasses) declaredAnnotation).value()) {
                            if (isTargetMap(wrapperClass1.targetMap(),EnvironmentDetector.getMinecraft())){
                                classes.add(getTargetClass(wrapperClass1));
                                break;
                            }
                        }
                    }
                }
            }else {
                classes.add(aClass);
            }
        }
        Constructor<?> ct;
        try {
            ct = targetClass.getDeclaredConstructor(classes.toArray(new Class[0]));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println(wrapper.getCanonicalName()+" "+targetField.getName()+" Cannot wrap");
            return;
        }
        if (!ct.isAccessible()){
            ct.setAccessible(true);
        }
        if (targetField.getType() == MethodHandle.class) {
            MethodHandle mh;
            try {
                mh = lookup.unreflectConstructor(ct);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println(wrapper.getCanonicalName() + " " + targetField.getName() + " Cannot wrap");
                return;
            }
            targetField.set(null, mh);
            return;
        }
        targetField.set(null, ct);
    }
    @SneakyThrows
    private static void wrapEnum(Class<? extends IWrapper> wrapper, WrapperClass wrapperClass, Class<?> targetClass, WrapEnum wrapEnum, Field targetField){
        if (!isTargetMap(wrapEnum.targetMap(), EnvironmentDetector.getMinecraft())){
            return;
        }
        Enum[] enums = (Enum[]) targetClass.getEnumConstants();
        if (!wrapEnum.customSrgName().equals("none")){
            for (Enum anEnum : enums) {
                if (anEnum.name().equals(wrapEnum.customSrgName())){
                    targetField.set(null,anEnum);
                    return;
                }
            }
        }
        for (Map map : Logger.getInstance().getMapParser().getMaps()) {
            if (map instanceof FieldMap){
                if (((FieldMap) map).getMcpClassName().equals(wrapperClass.mcpName()) && ((FieldMap) map).getMcpName().equals(wrapEnum.mcpName())){
                    FieldMap fm = (FieldMap) map;
                    for (Enum anEnum : enums) {
                        if (anEnum.name().equals(fm.getSrgName())){
                            targetField.set(null,anEnum);
                            return;
                        }
                    }
                }
            }
        }
        for (Enum anEnum : enums) {
            if (anEnum.name().equals(wrapEnum.mcpName())){
                targetField.set(null,anEnum);
                return;
            }
        }
        System.out.println(wrapper.getCanonicalName()+" "+targetField.getName()+" Cannot wrap");
    }
    @SneakyThrows
    private static void wrapObject(Class<? extends IWrapper> wrapper, WrapperClass wrapperClass, Class<?> targetClass, WrapObject wrapObject, Field targetField){
        if (!isTargetMap(wrapObject.targetMap(), EnvironmentDetector.getMinecraft())){
            return;
        }
        if (!wrapObject.customSrgName().equals("none")){
            Field field = ReflectUtil.findField(targetClass.getCanonicalName(),wrapObject.customSrgName());
            if (field == null) {
                System.out.println(wrapper.getCanonicalName()+" "+targetField.getName()+" Cannot wrap");
                return;
            }
            field.setAccessible(true);
            Object obj = field.get(null);
            Class<?> wrappingTargetClass = targetField.getType();
            if (IWrapper.class.isAssignableFrom(wrappingTargetClass)){
                //Target is wrapper
                Constructor constructor = wrappingTargetClass.getConstructor(Object.class);
                Object wrapObj = constructor.newInstance(obj);
                targetField.set(null,wrapObj);
            }else {
                targetField.set(null,obj);
            }
            return;
        }
        for (Map map : Logger.getInstance().getMapParser().getMaps()) {
            if (map instanceof FieldMap){
                if (((FieldMap) map).getMcpName().equals(wrapObject.mcpName()) && ((FieldMap) map).getMcpClassName().equals(wrapperClass.mcpName())){
                    FieldMap fm = (FieldMap) map;
                    Field field = ReflectUtil.findField(fm.getSrgClassName(),fm.getSrgName());
                    if (field == null)
                        break;
                    field.setAccessible(true);
                    Object obj = field.get(null);
                    Class<?> wrappingTargetClass = targetField.getType();
                    if (IWrapper.class.isAssignableFrom(wrappingTargetClass)){
                        //Target is wrapper
                        Constructor constructor = wrappingTargetClass.getConstructor(Object.class);
                        Object wrapObj = constructor.newInstance(obj);
                        targetField.set(null,wrapObj);
                        return;
                    }else {
                        targetField.set(null,obj);
                        return;
                    }
                }
            }
        }
        Field field = ReflectUtil.findField(wrapperClass.mcpName(),wrapObject.mcpName());
        if (field == null) {
            System.out.println(wrapper.getCanonicalName()+" "+targetField.getName()+" Cannot wrap");
            return;
        }
        field.setAccessible(true);
        Object obj = field.get(null);
        Class<?> wrappingTargetClass = targetField.getType();
        if (IWrapper.class.isAssignableFrom(wrappingTargetClass)){
            //Target is wrapper
            Constructor constructor = wrappingTargetClass.getConstructor(Object.class);
            Object wrapObj = constructor.newInstance(obj);
            targetField.set(null,wrapObj);
        }else {
            targetField.set(null,obj);
        }
    }
    @SneakyThrows
    private static void wrapMethod(Class<? extends IWrapper> wrapper, WrapperClass wrapperClass, Class<?> targetClass, WrapMethod wrapMethod, Field targetField){
        if (!isTargetMap(wrapMethod.targetMap(), EnvironmentDetector.getMinecraft())){
            return;
        }
        Annotation[] annotations = targetField.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof CactusWrapping){
                cactusWrapping.add(new CactusWrappingInfo(targetField,targetClass,wrapMethod));
            }
        }
        if (!wrapMethod.customSrgName().equals("none")){
            //Using custom target
            if (wrapMethod.signature().equals("none")){
                System.out.println(wrapper.getCanonicalName()+" "+targetField.getName() +" need signature");
                return;
            }
            Signature signature = new Signature(wrapMethod.signature());
            signature.parse();
            Method method = ReflectUtil.findMethod(targetClass.getCanonicalName(),wrapMethod.customSrgName(),signature.getArgs());
            if (method == null){
                System.out.println(wrapper.getCanonicalName()+" "+targetField.getName() +" cannot find target(custom)");
            }
            method.setAccessible(true);
            if (targetField.getType() == MethodHandle.class){
                MethodHandle mh = lookup.unreflect(method);
                targetField.set(null,mh);
                return;
            }
            targetField.set(null,method);
            return;
        }
        for (Map map : Logger.getInstance().getMapParser().getMaps()) {
            if (map instanceof MethodMap){
                if (((MethodMap) map).getMcpClassName().equals(wrapperClass.mcpName())){
                    if (((MethodMap) map).getMcpMethodName().equals(wrapMethod.mcpName())){
                        if (!wrapMethod.signature().equals("none")){
                            if (!((MethodMap) map).getMcpSignature().getSignature().equals(wrapMethod.signature())){
                                continue;
                            }
                        }
                        Signature signature = ((MethodMap) map).getSrgSignature();
                        signature.parse();
                        Method method = ReflectUtil.findMethod(((MethodMap) map).getSrgClassName(),((MethodMap) map).getSrgMethodName(),signature.getArgs());
                        if (method == null){
                            signature = ((MethodMap) map).getMcpSignature();
                            signature.parse();
                            method = ReflectUtil.findMethod(wrapperClass.mcpName(),wrapMethod.mcpName(),signature.getArgs());
                            if (method == null){
                                System.out.println(wrapper.getCanonicalName()+" "+targetField.getName() +" cannot find target(reflect)");
                                return;
                            }
                        }
                        method.setAccessible(true);
                        if (targetField.getType() == MethodHandle.class){
                            MethodHandle mh = lookup.unreflect(method);
                            targetField.set(null,mh);
                            return;
                        }
                        targetField.set(null,method);
                        return;
                    }
                }
            }
        }
        System.out.println(wrapper.getCanonicalName()+" "+targetField.getName() +" cannot find target(map)");
    }
    @SneakyThrows
    private static void processClassInstance(Class<? extends IWrapper> wrapper, WrapperClass wrapperClass, Class<?> targetClass, Field targetField){
        targetField.set(null,targetClass);
    }
    @SneakyThrows
    private static void wrapField(Class<? extends IWrapper> wrapper, WrapperClass wrapperClass, Class<?> targetClass, WrapField wrapField, Field targetField){
        if (!isTargetMap(wrapField.targetMap(), EnvironmentDetector.getMinecraft())){
            return;
        }
        if (!wrapField.customSrgName().equals("none")){
            Field field = ReflectUtil.findField(targetClass.getCanonicalName(),wrapField.customSrgName());
            if (field == null) {
                System.out.println(wrapper.getCanonicalName()+" "+targetField.getName()+" Cannot wrap");
                return;
            }
            field.setAccessible(true);
            targetField.set(null,field);
            return;
        }
        for (Map map : Logger.getInstance().getMapParser().getMaps()) {
            if (map instanceof FieldMap){
                if (((FieldMap) map).getMcpName().equals(wrapField.mcpName()) && ((FieldMap) map).getMcpClassName().equals(wrapperClass.mcpName().replace("$","."))){
                    FieldMap fm = (FieldMap) map;
                    Field field = ReflectUtil.findField(targetClass,fm.getSrgName());
                    if (field == null)
                        continue;
                    field.setAccessible(true);
                    targetField.set(null,field);
                    return;
                }
            }
        }
        //Find by MCP
        Field field = ReflectUtil.findField(wrapperClass.mcpName(),wrapField.mcpName());
        if (field == null){
            System.out.println(wrapper.getCanonicalName()+" "+targetField.getName()+" Cannot wrap");
            return;
        }
        field.setAccessible(true);
        targetField.set(null,field);
    }
    @SneakyThrows
    private static Class<?> getTargetClass(WrapperClass target){
        for (Map map : Logger.getInstance().getMapParser().getMaps()) {
            if (map instanceof ClassMap){
                if (target.mcpName().equals(((ClassMap) map).getMcpName())){
                    try {
                        return Class.forName(((ClassMap) map).getSrgName());
                    } catch (ClassNotFoundException e) {
                        break;
                    }
                }
            }
        }
        //Find by MCP
        return Class.forName(target.mcpName());
    }
    public static boolean isTargetMap(Environment[] target,Environment thisEnv){
        for (Environment environment : target) {
            if (environment == thisEnv || environment == Environment.Any){
                return true;
            }
        }
        return false;
    }
    private static void after(){
        /*if (EnvironmentDetector.hasAntiCheat(Environment.EnsembleAntiCheat)){
            Minecraft.theMinecraft = ReflectUtil.findField(Minecraft.MinecraftClass.getCanonicalName(),"zzzzzzzz");
            Minecraft.theMinecraft.setAccessible(true);
            Minecraft.fontRendererObj = ReflectUtil.findField(Minecraft.MinecraftClass.getCanonicalName(),"aaaaaaaa");
            Minecraft.fontRendererObj.setAccessible(true);
        }

         */
    }
}
