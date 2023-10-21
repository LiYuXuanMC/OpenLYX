package al.nya.reflect.features.modules.World.disablers;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.features.modules.Ghost.Reach;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.World.Disabler;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.bridge.BridgeUtil;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityItemFrame;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.render.EntityRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EntitySelectors;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class MargelesAntiCheatDisabler extends DisableSubModule{
    public static Method setVL = null;
    public static Method getVL = null;
    public MargelesAntiCheatDisabler() {
        super(DisablerMode.MAC);

    }
    @Override
    public void enable(){
        try{
            Class MinecraftForge = Class.forName("net.minecraftforge.common.MinecraftForge");
            Class EventBus = Class.forName("net.minecraftforge.fml.common.eventhandler.FMLBusEvent");
            Field EventBusField = MinecraftForge.getDeclaredField("EVENT_BUS");
            EventBusField.setAccessible(true);
            Object eventBus = EventBusField.get(null);
            Field listenersField = EventBus.getDeclaredField("listeners");
            listenersField.setAccessible(true);
            ConcurrentHashMap<Object, ArrayList<Object>> listeners = (ConcurrentHashMap<Object, ArrayList<Object>>) listenersField.get(eventBus);
            AtomicReference<Object> mac = null;
            listeners.forEach((CO,L) -> {
                if (CO.getClass() == MargeleAntiCheatDetector.getMAC()){
                    mac.set(CO);
                }
            });
            if (mac.get() != null){
                listeners.remove(mac.get());
                listenersField.set(eventBus, listeners);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean isEnable(){
        Disabler disabler = ModuleManager.getModule(Disabler.class);
        return disabler.isEnable() && disabler.using.getMode() == DisablerMode.MAC && Reflect.USER.isBeta();
    }
    public static boolean macUpdate(){
        return isEnable();
    }
    public static boolean macProcessSendPacket(Packet packet) {
        if (!isEnable())
            return false;
        return C02PacketUseEntity.isC02PacketUseEntity(packet);
    }
    public static boolean macProcessRevPacket(Packet packet){
        if (!isEnable())
            return false;
        return C02PacketUseEntity.isC02PacketUseEntity(packet);
    }
    public static boolean getMouseOverHook(float partialTicks){
        if (!isEnable())
            return false;
        getMouseOver(partialTicks);
        return true;
    }
    public static void getMouseOver(float partialTicks){
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        EntityRenderer entityRenderer = mc.getEntityRenderer();
        WorldClient theWorld = mc.getTheWorld();
        if (!entity.isNull())
        {
            if (!theWorld.isNull())
            {
                PlayerControllerMP playerController = mc.getPlayerController();
                mc.getMcProfiler().startSection("pick");
                mc.setPointedEntity(new Entity(null)); //Wrap it to null
                double d0 = playerController.getBlockReachDistance();
                mc.setObjectMouseOver(entity.rayTrace(EventBus.getBlockReach(), partialTicks));
                double d1 = d0;
                Vec3 vec3 = entity.getPositionEyes(partialTicks);
                boolean flag = false;
                int i = 3;

                if (playerController.extendedReach())
                {
                    d0 = 6.0D;
                    d1 = 6.0D;
                }
                else
                {
                    if (d0 > 3.0D)
                    {
                        flag = !ModuleManager.getModule(Reach.class).isEnable();
                    }
                }
                MovingObjectPosition objectMouseOver = mc.getObjectMouseOver();
                if (!objectMouseOver.isNull())
                {
                    d1 = objectMouseOver.getHitVec().distanceTo(vec3);
                }

                Vec3 vec31 = entity.getLook(partialTicks);
                Vec3 vec32 = vec3.addVector(vec31.getXCoord() * d0, vec31.getYCoord() * d0, vec31.getZCoord() * d0);
                entityRenderer.setPointedEntity(new Entity(null));
                Vec3 vec33 = null;
                float f = 1.0F;
                List<Entity> list = theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.getXCoord() * d0, vec31.getYCoord() * d0, vec31.getZCoord() * d0).expand(f,f,f),EntitySelectors.getNOT_SPECTATING());
                list.removeIf(entity1 -> !entity1.canBeCollidedWith());
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = list.get(j);
                    float f1 = entity1.getCollisionBorderSize();
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1,f1,f1);
                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

                    if (axisalignedbb.isVecInside(vec3))
                    {
                        if (d2 >= 0.0D)
                        {
                            entityRenderer.setPointedEntity(entity1);
                            vec33 = movingobjectposition.isNull() ? vec3 : movingobjectposition.getHitVec();
                            d2 = 0.0D;
                        }
                    }
                    else if (!movingobjectposition.isNull())
                    {
                        double d3 = vec3.distanceTo(movingobjectposition.getHitVec());

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.equals(entity.getRidingEntity()) && !entity.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    entityRenderer.setPointedEntity(entity1);
                                    vec33 = movingobjectposition.getHitVec();
                                }
                            }
                            else
                            {
                                entityRenderer.setPointedEntity(entity1);
                                vec33 = movingobjectposition.getHitVec();
                                d2 = d3;
                            }
                        }
                    }
                }
                Entity pointedEntity = entityRenderer.getPointedEntity();
                if (!pointedEntity.isNull() && flag && vec3.distanceTo(vec33) > 3.0D)
                {
                    entityRenderer.setPointedEntity(new Entity(null));
                    mc.setObjectMouseOver(new MovingObjectPosition(MovingObjectType.MISS, vec33, null, new BlockPos(vec33)));
                }
                pointedEntity = entityRenderer.getPointedEntity();
                if (!pointedEntity.isNull() && (d2 < d1 || mc.getObjectMouseOver().isNull()))
                {
                    mc.setObjectMouseOver(new MovingObjectPosition(pointedEntity, vec33));

                    if (EntityLivingBase.isEntityLivingBase(pointedEntity) || EntityItemFrame.isEntityItemFrame(pointedEntity))
                    {
                        mc.setPointedEntity(pointedEntity);
                    }
                }

                mc.getMcProfiler().endSection();
            }
        }
    }
    public static boolean onCloseChannel(IChatComponent reason) {
        if (!isEnable())
            return false;
        if (reason.getUnformattedText().contains("Margele")) {
            ClientUtil.printChat(ClientUtil.Level.WARNING,"MargelesAntiCheat trying kick you");
            resetCheatVL();
            return true;
        }
        //Tool.log("closeChannel because %s", reason.getUnformattedText());
        return false;
    }
    public static void resetCheatVL() {
        if (setVL == null){
            try {
                setVL = BridgeUtil.MargeleAntiCheatAccessor.getDeclaredMethod("setCheatVL", int.class);
                setVL.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ReflectUtil.invoke(setVL,null,0);
    }
    public static int getCheatVL() {
        if (getVL == null){
            try {
                getVL = BridgeUtil.MargeleAntiCheatAccessor.getDeclaredMethod("getCheatVL");
                getVL.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (int) ReflectUtil.invoke(getVL,null);
    }
}
