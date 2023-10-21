package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.*;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.utils.math.Vector3d;
import al.nya.reflect.utils.pathfinding.PathUtils;
import al.nya.reflect.utils.render.PathRender;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TeleportHit extends Module {
    private EntitySelect select = new EntitySelect(true,false,false,true);
    private OptionValue render = new OptionValue("Render",true);
    private EntityLivingBase targetEntity = new EntityLivingBase(null);
    private boolean shouldHit;
    private List<Vector3d> path = new ArrayList<Vector3d>();
    private long time = 0;
    public TeleportHit() {
        super("TeleportHit",ModuleType.Combat);
        addValues(select.getValues());
        addValue(render);
    }
    @EventTarget
    public void onMotion(EventPreUpdate event) {
        final Entity facedEntity = RaycastUtils.raycastEntity(100D, raycastedEntity -> EntityLivingBase.isEntityLivingBase(raycastedEntity));

        EntityPlayerSP thePlayer = mc.getThePlayer();

        if (thePlayer.isNull())
            return;

        if(mc.getGameSettings().getKeyBindAttack().isKeyDown() && select.check(facedEntity)) {
            if (facedEntity.getDistanceSqToEntity(thePlayer) >= 1D) targetEntity = new EntityLivingBase(facedEntity.getWrapObject());
        }
        if (!targetEntity.isNull()) {
            if (!shouldHit) {
                shouldHit = true;
                return;
            }

            if (thePlayer.getFallDistance() > 0F) {
                final Vec3 rotationVector = RotationUtils.getVectorForRotation(new Rotation(thePlayer.getRotationYaw(), 0F).getValue());
                final double x = thePlayer.getPosX() + rotationVector.getXCoord() * (thePlayer.getDistanceToEntity(targetEntity) - 1.0F);
                final double z = thePlayer.getPosZ() + rotationVector.getZCoord() * (thePlayer.getDistanceToEntity(targetEntity) - 1.0F);
                final double y = targetEntity.getPosY() + 0.25D;

                path = PathUtils.findPath(x, y + 1.0D, z, 4D);
                time = System.currentTimeMillis();
                NetHandlerPlayClient netHandler = mc.getNetHandler();
                path.forEach(pos -> netHandler.addToSendQueue(new C04PacketPlayerPosition(pos.getX(), pos.getY(), pos.getZ(), false)));
                thePlayer.swingItem();
                thePlayer.getSendQueue().addToSendQueue(new C02PacketUseEntity(targetEntity, C02Action.ATTACK));
                thePlayer.onEnchantmentCritical(targetEntity);
                List<Vector3d> reverse = path;
                Collections.reverse(reverse);
                reverse.forEach(pos -> netHandler.addToSendQueue(new C04PacketPlayerPosition(pos.getX(), pos.getY(), pos.getZ(), false)));
                netHandler.addToSendQueue(new C04PacketPlayerPosition(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ(), thePlayer.isOnGround()));
                shouldHit = false;
                targetEntity = new EntityLivingBase(null);
            } else if (thePlayer.isOnGround())
                thePlayer.jump();
        } else
            shouldHit = false;
    }
    @EventTarget
    //Render the path
    public void onRender3d(EventRender3D render3D){
        if(render.getValue()){
            //if time past 5s
            if(System.currentTimeMillis() - time > 2000L){
                path.clear();
                return;
            }
            if (path.isEmpty())return;
            double[][] points = new double[path.size()][3];
            //convert
            for(int i = 0; i < path.size(); i++){
                points[i][0] = path.get(i).getX();
                points[i][1] = path.get(i).getY();
                points[i][2] = path.get(i).getZ();
            }
            //render
            PathRender.renderPath(points,mc.getThePlayer().getEntityBoundingBox());
        }
    }
}
