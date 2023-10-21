package al.logger.client.features.modules.impls.World;

import al.logger.client.ExceptionHandler;
import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventCloseChannel;
import al.logger.client.event.client.mac.EventMACMouseOver;
import al.logger.client.event.client.mac.EventMACProcessPacket;
import al.logger.client.event.client.mac.EventMACProcessUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Combat.Reach;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.utils.ChatUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityItemFrame;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import al.logger.client.wrapper.LoggerMC.render.EntityRenderer;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.NotNative;
import est.builder.annotations.Clear;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Margele's Anti-Cheat bypass
 */
@Native
@Clear(when = "Release")
public class Collapse extends Module {
    private Object macListener;
    public Collapse() {
        super("Collapse",Category.World);
    }

    @Override
    public void onEnable(){
        Logger.getInstance().getNotificationManager().addNotification(new Notification("[Debug]Start Disable MAC" , Notification.NotificationType.Success));
        try{
            Class MinecraftForge = Class.forName("net.minecraftforge.common.MinecraftForge");
            Class EventBus = Class.forName("net.minecraftforge.fml.common.eventhandler.EventBus");
            EventBus.getDeclaredFields();
            Field EventBusField = MinecraftForge.getDeclaredField("EVENT_BUS");
            EventBusField.setAccessible(true);
            Object eventBus = EventBusField.get(null);
            Field listenersField = null;
            if (EnvironmentDetector.hasAntiCheat(Environment.NPlusAntiCheat)){
                listenersField = getNACListeners();
            }else {
                listenersField = EventBus.getDeclaredField("listeners");
            }
            if (listenersField == null){
                Logger.getInstance().getNotificationManager().addNotification(new Notification("Failed to find listeners field" , Notification.NotificationType.Error));
                return;
            }
            listenersField.setAccessible(true);
            ConcurrentHashMap<Object, ArrayList<Object>> listeners = (ConcurrentHashMap<Object, ArrayList<Object>>) listenersField.get(eventBus);
            AtomicReference<Object> mac = new AtomicReference<>();
            listeners.forEach((CO,L) -> {

                if (CO.getClass() == EnvironmentDetector.getMAC()){
                    mac.set(CO);
                }
            });
            if (mac.get() != null){
                listeners.remove(mac.get());
                listenersField.set(eventBus, listeners);
                System.out.println("Removed MAC");
                Logger.getInstance().getAgent().nativeLog("Removed MAC");
                Logger.getInstance().getNotificationManager().addNotification(new Notification("Removed MAC" , Notification.NotificationType.Success));
                macListener = mac.get();
            }
        }catch (Exception e){
            e.printStackTrace();
            ExceptionHandler.handle(e);
        }
    }
    @Listener
    @NotNative
    public void onCloseChannel(EventCloseChannel event){
        if (event.getReason().getUnformattedText().contains("Margele")) {
            ChatUtils.warning("Margele'sAntiCheat trying kick you");
            //resetCheatVL();
            event.cancel();
        }
    }
    @Listener
    @NotNative
    public void onMACProcessPacket(EventMACProcessPacket event){
        if (C02PacketUseEntity.isC02PacketUseEntity(event.getPacket()) ||
                S08PacketPlayerPosLook.isS08PacketPlayerPosLook(event.getPacket())) {
            event.cancel();
        }

    }
    @Listener
    @NotNative
    public void onMACUpdate(EventMACProcessUpdate event){
        event.cancel();
    }
    @Listener
    @NotNative
    public void onMouseOver(EventMACMouseOver event){
        event.cancel();
        processMouseOver(event.getPartialTicks());
    }
    @NotNative
    private static void processMouseOver(float p_getMouseOver_1_) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        EntityRenderer entityRenderer = mc.getEntityRenderer();
        if (!entity.isNull() && !mc.getTheWorld().isNull()) {
            Reach reach = (Reach) Logger.getInstance().getModuleManager().getModule(Reach.class);
            mc.getMcProfiler().startSection("pick");
            mc.setPointedEntity(new Entity(null));
            double d0 = mc.getPlayerController().getBlockReachDistance();
            if (reach.isEnable()) {
                d0 = reach.getMaxRange();
            }
            mc.setObjectMouseOver(entity.rayTrace(reach.isEnable() ? reach.getBuildRange() : d0, p_getMouseOver_1_));
            double d1 = d0;
            Vec3 vec3 = entity.getPositionEyes(p_getMouseOver_1_);
            boolean flag = false;
            int i = 1;
            if (mc.getPlayerController().extendedReach()) {
                d0 = 6.0;
                d1 = 6.0;
            } else if (d0 > 3.0) {
                flag = !reach.isEnable();
            }

            if (!mc.getObjectMouseOver().isNull()) {
                d1 = mc.getObjectMouseOver().getHitVec().distanceTo(vec3);
            }
            if (reach.isEnable()) {
                d1 = reach.getAttackRange();
            }
            Vec3 vec31 = entity.getLook(p_getMouseOver_1_);
            Vec3 vec32 = vec3.addVector(vec31.getXCoord() * d0, vec31.getYCoord() * d0, vec31.getZCoord() * d0);
            entityRenderer.setPointedEntity(new Entity(null));
            Vec3 vec33 = null;
            float f = 1.0F;
            List<Entity> list;
            list = mc.getTheWorld().getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.getXCoord() * d0, vec31.getYCoord() * d0, vec31.getZCoord() * d0).expand(f, f, f),null);
            list.removeIf(entity1 -> !entity1.canBeCollidedWith());
            /*{
                public boolean apply(net.minecraft.entity.Entity p_apply_1_) {
                return p_apply_1_.canBeCollidedWith();
            }

             */
            double d2 = d1;

            for(int j = 0; j < list.size(); ++j) {
                Entity entity1 = list.get(j);
                float f1 = entity1.getCollisionBorderSize();
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand((double)f1, (double)f1, (double)f1);
                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
                if (axisalignedbb.isVecInside(vec3)) {
                    if (d2 >= 0.0) {
                        entityRenderer.setPointedEntity(entity1);
                        vec33 = movingobjectposition.isNull() ? vec3 : movingobjectposition.getHitVec();
                        d2 = 0.0;
                    }
                } else if (!movingobjectposition.isNull()) {
                    double d3 = vec3.distanceTo(movingobjectposition.getHitVec());
                    if (d3 < d2 || d2 == 0.0) {
                        if (entity1.equals(entity.getRidingEntity()) /*&& !entity.canRiderInteract()*/) {
                            if (d2 == 0.0) {
                                entityRenderer.setPointedEntity(entity1);
                                vec33 = movingobjectposition.getHitVec();
                            }
                        } else {
                            entityRenderer.setPointedEntity(entity1);
                            vec33 = movingobjectposition.getHitVec();
                            d2 = d3;
                        }
                    }
                }
            }

            if (!entityRenderer.getPointedEntity().isNull() && flag && vec3.distanceTo(vec33) > 3.0) {
                entityRenderer.setPointedEntity(new Entity(null));
                mc.setObjectMouseOver(new MovingObjectPosition(MovingObjectType.MISS, vec33, null, new BlockPos(vec33)));
            }

            if (!entityRenderer.getPointedEntity().isNull() && (d2 < d1 || mc.getObjectMouseOver().isNull())) {
                mc.setObjectMouseOver(new MovingObjectPosition(entityRenderer.getPointedEntity(), vec33));
                if (EntityLivingBase.isEntityLivingBase(entityRenderer.getPointedEntity()) || EntityItemFrame.isEntityItemFrame(entityRenderer.getPointedEntity())) {
                    mc.setPointedEntity(entityRenderer.getPointedEntity());
                }
            }
            mc.getMcProfiler().endSection();
        }
    }
    //net.minecraftforge.fml.common.eventhandler.EventBus.NaCnmGmhQ7wGpBC]
    public static Field getNACListeners(){
        try {
            Class EventBus = Class.forName("net.minecraftforge.fml.common.eventhandler.EventBus");
            for (Field declaredField : EventBus.getDeclaredFields()) {
                if (declaredField.getType() == ConcurrentHashMap.class){
                    return declaredField;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ExceptionHandler.handle(e);
            return null;
        }


        return null;

    }
    public static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
}
