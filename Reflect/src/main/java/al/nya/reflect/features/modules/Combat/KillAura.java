package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.Reflect;
import al.nya.reflect.config.FriendManager;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.BedNuker;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.RotationUtils;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.utils.render.AnimationUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemSword;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.C08PacketPlayerBlockPlacement;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.render.EntityRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * KillAura
 * Skid from SkidLine Client
 */
public class KillAura extends Module {
    //@OptionInfo(description = "Attack time.", name = "Attack", priority = -2)
    public ModeValue attackMode = new ModeValue("AttackMode",AttackMode.Pre, AttackMode.values());
    //@OptionInfo(description = "Attack mode.", name = "Mode", priority = 0)
    public ModeValue mode = new ModeValue("Mode",Mode.Single,Mode.values());
    //@OptionInfo(description = "Priority mode.", name = "Priority", priority = 1)
    public ModeValue priority = new ModeValue("Priority",Priority.Range,Priority.values());
    //@OptionInfo(description = "Block mode.", name = "Block Mode", priority = 2)
    public ModeValue block = new ModeValue("Block",BlockMode.Hypixel,BlockMode.values());
    //@OptionInfo(description = "Max click per second.", name = "Max Cps", priority = 3)
    public DoubleValue cpsMax = new DoubleValue("MaxCPS",20,1,13,"0.0");
    //@OptionInfo(description = "Min click per second.", name = "Min Cps", priority = 4)
    public DoubleValue cpsMin = new DoubleValue("MinCPS",20,1,13,"0.0");
    //@OptionInfo(description = "Attack range.", name = "Range", priority = 5)
    public DoubleValue range = new DoubleValue("Range",6,3,4,"0.0");
    //@OptionInfo(description = "Block range.", name = "Block Range", priority = 6)
    public DoubleValue blockrange = new DoubleValue("BlockRange",10,3,5.8,"0.00"){
        @Override
        public boolean show(){
            return block.getValue() != BlockMode.None;
        }
    };
    //@OptionInfo(description = "Switch target delay.", name = "Switch Delay", priority = 7)
    public DoubleValue switchDelay = new DoubleValue("SwitchDelay",10,3,5.8,"0.00");
    //@OptionInfo(description = "Max rotation speed.", name = "Max TurnSpeed", priority = 8)
    public DoubleValue maxTurn = new DoubleValue("MaxRotationSpeed",180.0, 5.0, 140.0, "0.0");
    //@OptionInfo(description = "Min rotation speed.", name = "Min TurnSpeed", priority = 9)
    public DoubleValue minTurn = new DoubleValue("MinRotationSpeed",180.0, 5.0, 120.0, "0.0");
    //@OptionInfo(description = "Auto aim.", name = "Auto Aim", priority = 10)
    public OptionValue aim = new OptionValue("AutoAim", false);
    public OptionValue noRotation = new OptionValue("NoRotation", false);
    //@OptionInfo(description = "Attack players.", name = "Player", priority = -1)
    public OptionValue playerValue = new OptionValue("Player", true);
    //@OptionInfo(description = "Attack animal.", name = "Animal", priority = -1)
    public OptionValue animalValue = new OptionValue("Animal", false);
    //@OptionInfo(description = "Attack mobs.", name = "Mob", priority = -1)
    public OptionValue mobValue = new OptionValue("Mob", false);
    //@OptionInfo(description = "Attack invisible.", name = "Invisible", priority = -1)
    public OptionValue invisibleValue = new OptionValue("Invisible", true);
    private static final ArrayList<EntityLivingBase> targets = new ArrayList<>();
    private final OptionValue throughWallsValue = new OptionValue("ThroughWalls", true);
    public ModeValue ESP = new ModeValue("ESP", ESPMode.None, ESPMode.values());
    private final TimerUtil switchTimer = new TimerUtil(), attackTimer = new TimerUtil();
    public static EntityLivingBase curTarget, blockTarget;
    private int index, attacks = 0;
    public static boolean isBlocking;
    private double espAnimation = 0;
    private boolean isUp = false;
    private final OptionValue autoDisable = new OptionValue("AutoDisable", true);
    public float cYaw, cPitch;



    public KillAura() {
        super("KillAura", "杀戮光环", ModuleType.Combat);
        addValues(mode, attackMode, priority, block, cpsMax, cpsMin, range, blockrange, switchDelay, maxTurn, minTurn, aim, noRotation, playerValue, animalValue, mobValue, invisibleValue, ESP, throughWallsValue, autoDisable);
    }
    @Override
    public void onEnable() {
        switchTimer.reset();
        isBlocking = false;
        curTarget = null;
        targets.clear();
        blockTarget = null;
        index = 0;
        attacks = 0;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer == null)return;
        cYaw = thePlayer.getRotationYaw();
        cPitch = thePlayer.getRotationPitch();
        if (cpsMax.getValue().doubleValue() < cpsMin.getValue().doubleValue()) {
            ClientUtil.printChat("&cConfig Error >> &fMin CPS > Max CPS");
        }

        if (maxTurn.getValue().doubleValue() < minTurn.getValue().doubleValue()) {
            ClientUtil.printChat("&cConfig Error >> &fMin Turn > Max Turn");
        }
    }
    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.getThePlayer().isNull())return;
        if (isBlocking) unblock();

        curTarget = null;
        targets.clear();
        blockTarget = null;
        index = 0;
    }
    @EventTarget
    public void onWorldLoad(final EventWorldLoad worldLoad){
        NotificationPublisher.queue( "AutoDisable", "Disabled KillAura",3000,NotificationType.SUCCESS);
        if (autoDisable.getValue()) setEnable(false);
    }
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (BedNuker.isNuking) return;
        if (ModuleManager.getModule(Scaffold.class).isEnable()) return;
        curTarget = null;
        blockTarget = null;
        targets.clear();
        this.getEntity();

        if (mode.getValue() == Mode.Switch) {

            if (switchTimer.hasPassed(switchDelay.getValue().intValue() * 100L) && targets.size() > 1) {
                ++index;
                switchTimer.reset();
            }

            if (index >= targets.size())
                index = 0;

            this.sortTargets();
        }

        if (mode.getValue() == Mode.Single)
            index = 0;

        if (!targets.isEmpty())
            curTarget = targets.get(index);
        if (aim.getValue()&&curTarget != null) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            thePlayer.setRotationYaw(cYaw);
            thePlayer.setRotationPitch(cPitch);
        }
    }
    @EventTarget
    public void render3D(final EventRender3D xaski) {
        if (curTarget == null || curTarget.isNull()) return;
        if (ESP.getValue() == ESPMode.Jello) {
            double lastTickPosX = curTarget.getLastTickPosX();
            double lastTickPosY = curTarget.getLastTickPosY();
            double lastTickPosZ = curTarget.getLastTickPosZ();
            RenderManager rm = mc.getRenderManager();
            float partialTicks = mc.getTimer().getRenderPartialTicks();
            final double x1 = lastTickPosX + (curTarget.getPosX() - lastTickPosX) * partialTicks - rm.getViewerPosX();
            final double y1 = lastTickPosY + (curTarget.getPosY() - lastTickPosY) * partialTicks - rm.getViewerPosY();
            final double z1 = lastTickPosZ + (curTarget.getPosZ() - lastTickPosZ) * partialTicks - rm.getViewerPosZ();
            if (espAnimation > curTarget.getEyeHeight() + 0.4 || espAnimation < 0.0) {
                isUp = !isUp;
            }
            if (isUp) {
                double auraHitAnimationsSetup = espAnimation;
                double n = 3.0;
                espAnimation = auraHitAnimationsSetup + n / Minecraft.getDebugFPS();
            }
            else {
                double auraHitAnimationsSetup2 = espAnimation;
                double n2 = 3.0;
                espAnimation = auraHitAnimationsSetup2 - n2 / Minecraft.getDebugFPS();
            }
            if (isUp) {
                for (int i = 0; i < 100; ++i) {
                    this.Jelloesp(curTarget, x1, y1 + espAnimation - i * 0.005, z1, 1f);
                }
            }
            else {
                for (int i = 0; i < 100; ++i) {
                    this.Jelloesp(curTarget, x1, y1 + espAnimation + i * 0.005, z1, 1f);
                }
            }
        } else if (ESP.getValue() == ESPMode.FDPJello) {
            EntityRenderer entityRenderer = mc.getEntityRenderer();
            double everyTime = 1500;
            double drawTime = (System.currentTimeMillis() % everyTime);
            boolean drawMode = drawTime > (everyTime / 2);
            double drawPercent = drawTime / (everyTime / 2);
            // true when goes up
            if (!drawMode) {
                drawPercent = 1 - drawPercent;
            } else {
                drawPercent -= 1;
            }

            drawPercent = MathHelper.easeInOutQuad(drawPercent, 2);

            entityRenderer.disableLightmap();
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glEnable(GL11.GL_BLEND);
//            if (Main.settingsManager.getSettingByName(Main.featureDirector.getModule(TargetESP.class), "DepthTest").getValBoolean())
//                GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glShadeModel(7425);
            entityRenderer.disableLightmap();

            double radius = curTarget.getWidth();
            double height = curTarget.getHeight() + 0.1;
            double x = curTarget.getLastTickPosX() + (curTarget.getPosX() - curTarget.getLastTickPosX()) * xaski.getPartialTicks() - mc.getRenderManager().getViewerPosX();
            double y = (curTarget.getLastTickPosY() + (curTarget.getPosY() - curTarget.getLastTickPosY()) * xaski.getPartialTicks() - mc.getRenderManager().getViewerPosY()) + height * drawPercent;
            double z = curTarget.getLastTickPosZ() + (curTarget.getPosZ() - curTarget.getLastTickPosZ()) * xaski.getPartialTicks() - mc.getRenderManager().getViewerPosZ();
            double eased = (height / 3) * ((drawPercent > 0.5) ? 1 - drawPercent : drawPercent) * ((drawMode) ? -1 : 1);

            for (int lox = 0; lox < 360; lox += 5) {
                Color color = Reflect.ColorStyle;
                double x1 = x - Math.sin(lox * Math.PI / 180F) * radius;
                double z1 = z + Math.cos(lox * Math.PI / 180F) * radius;
                double x2 = x - Math.sin((lox - 5) * Math.PI / 180F) * radius;
                double z2 = z + Math.cos((lox - 5) * Math.PI / 180F) * radius;

                GL11.glBegin(GL11.GL_QUADS);
                RenderUtil.glColor(color, 0f);
                GL11.glVertex3d(x1, y + eased, z1);
                GL11.glVertex3d(x2, y + eased, z2);
                RenderUtil.glColor(color, 255);
                GL11.glVertex3d(x2, y, z2);
                GL11.glVertex3d(x1, y, z1);
                GL11.glEnd();

                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3d(x2, y, z2);
                GL11.glVertex3d(x1, y, z1);
                GL11.glEnd();
            }

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glShadeModel(7424);
            GL11.glColor4f(1f, 1f, 1f, 1f);
//            if (Main.settingsManager.getSettingByName(Main.featureDirector.getModule(TargetESP.class), "DepthTest").getValBoolean())
//                GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }
    }
    public void Jelloesp(final Entity player, final double x, final double y, final double z, final float alpha) {
        Cylinder c = new Cylinder();
        GL11.glPushMatrix();
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GlStateManager.translate(x, y, z);
        GlStateManager.color(1.0f, 1.0f, 1.0f, alpha);
        GlStateManager.rotate(180.0f, 90.0f, 0.0f, 2.0f);
        GlStateManager.rotate(180.0f, 0.0f, 90.0f, 90.0f);
        c.setDrawStyle(100011);
        c.draw(0.8f, 0.8f, -0.0f, 360, 0);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }
    @EventTarget
    public void onPre(final EventPreUpdate preUpdate){
        if (BedNuker.isNuking) return;
        if(ModuleManager.getModule(Scaffold.class).isEnable())return;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (curTarget != null && !noRotation.getValue()) { // ROTATION
            double step;
            if (minTurn.getValue().doubleValue() == maxTurn.getValue().doubleValue())
                step = maxTurn.getValue().doubleValue();
            else
                step = MathUtil.getRandom(minTurn.getValue().doubleValue(), maxTurn.getValue().doubleValue());

            double x = curTarget.getPosX() + (curTarget.getPosX() - curTarget.getPrevPosX());
            double z = curTarget.getPosZ() + (curTarget.getPosZ() - curTarget.getPrevPosZ());
            double y = (curTarget.getPosY()) + (curTarget.getEyeHeight() - 2);
            float yaw = RotationUtils.getYawDifference(cYaw, x, y, z);
            float pitch = RotationUtils.getPitchDifference(cPitch, x, y, z);
            float neededYaw = (float) ((Math.min(Math.abs(yaw-1), step))
                    * (yaw < 0 ? -1 : 1));
            float neededPitch = (float) ((Math.min(Math.abs(pitch), step / 2))
                    * (pitch < 0 ? -1 : 1));
            cYaw += neededYaw;
            cPitch += neededPitch;
            preUpdate.setYaw(cYaw-5);
            preUpdate.setPitch(cPitch-3);
        }

        if (blockTarget != null) {
            if (block.getValue() == BlockMode.NCP) {
                if (!canBlock() && isBlocking) {
                    unblock();
                }
                if (canBlock() && isBlocking) {
                    unblock();
                }
            }
        } else {
            if (canBlock() && isBlocking) {
                unblock();
            }
        }
        attackAtPre(preUpdate);
    }
    @EventTarget
    public void onPost(final EventPostUpdate postUpdate){
        if (BedNuker.isNuking) return;
        if(ModuleManager.getModule(Scaffold.class).isEnable())return;
        attackAtPost(postUpdate);
        if (blockTarget != null && canBlock() && !isBlocking) block();
    }
    @EventTarget
    public void onLoop(final EventLoop event) {
        if (BedNuker.isNuking) return;
        if (ModuleManager.getModule(Scaffold.class).isEnable()) return;
        if (blockTarget != null && canBlock() && block.getValue() == BlockMode.Hypixel) block();
        if (curTarget == null) {
            attacks = 1;
            attackTimer.reset();
        }
        double aps;
        if (cpsMax.getValue().doubleValue() == cpsMin.getValue().doubleValue())
            aps = cpsMax.getValue().doubleValue();
        else
            aps = MathUtil.getRandom(cpsMin.getValue().doubleValue(), cpsMax.getValue().doubleValue());
        long delay = Math.round((double) 1000 / aps);
        if (attackTimer.hasPassed(delay)) {
            attacks++;
            attackTimer.reset();
        }

    }

    private void handleAttack() {
        if (curTarget == null) return;
        while (attacks > 0) {
            if (mode.getValue() == Mode.Multi) targets.forEach(this::attack);
            else attack(curTarget);
            attacks--;
        }
    }
    public void attackAtPre(final EventPreUpdate evt){
        if (curTarget == null) return;
        if (attackMode.getValue() == AttackMode.Pre){
            handleAttack();
        }
    }
    public void attackAtPost(final EventPostUpdate evt){
        if (curTarget == null) return;
        if (attackMode.getValue() == AttackMode.Post){
            handleAttack();
        }
    }
    private void attack(final EntityLivingBase target) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (canBlock() && block.getValue() != BlockMode.Always && block.getValue() != BlockMode.Hypixel && (isBlocking || thePlayer.isBlocking())) {
            unblock();
        }
        thePlayer.swingItem();
        thePlayer.getSendQueue().addToSendQueue(new C02PacketUseEntity(target, C02Action.ATTACK));


        /*if (EnchantmentHelper.getModifierForCreature(thePlayer.getHeldItem(), target.getCreatureAttribute()) > 0) {
            //    	mc.thePlayer.onCriticalHit(target);
            thePlayer.onEnchantmentCritical(target);
        }

        if (thePlayer.fallDistance > 0 && !thePlayer.isOnGround() && !thePlayer.isOnLadder() && !thePlayer.isInWater() && !thePlayer.isPotionActive(Potion.blindness) && thePlayer.ridingEntity == null) {
            thePlayer.onCriticalHit(target);
            //    mc.thePlayer.onEnchantmentCritical(target);
        }
         */
    }
    public final Vec3 getVectorForRotation(final float pitch, final float yaw)
    {
        float f = MathHelper.cos(-yaw * 0.0174532925F - (float)Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.01745329234F - (float)Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.01745346292F);
        float f3 = MathHelper.sin(-pitch * 0.017456292F);
        return new Vec3(f1 * f2, f3, (double) (f * f2) + 2);
    }
    private void block() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        InventoryPlayer inv = thePlayer.getInventory();
        thePlayer.getSendQueue().addToSendQueue(new C08PacketPlayerBlockPlacement(inv.getCurrentItem()));
        thePlayer.setItemInUseCount(inv.getCurrentItem().getMaxItemUseDuration());
        isBlocking = true;
    }

    private void unblock() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        thePlayer.getSendQueue().addToSendQueue(new C07PacketPlayerDigging(C07Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
        thePlayer.setItemInUseCount(0);
        isBlocking = false;
    }

    public boolean canBlock() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        InventoryPlayer inv = thePlayer.getInventory();
        ItemStack curtItem = inv.getCurrentItem();
        return block.getValue() != BlockMode.None && Objects.nonNull(curtItem.getWrapObject()) && ItemSword.isItemSword(curtItem.getItem());
    }
    private void getEntity() {
        /*for (Entity entity : mc.theWorld.getLoadedEntityList()) {
            if (block.isMode("None")) break;
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingBase = (EntityLivingBase) entity;
                if (isValid(livingBase, blockrange.getValue().doubleValue())) {
                    blockTarget = livingBase;
                    break;
                }
            }
        }

        for (Entity entity : mc.theWorld.getLoadedEntityList()) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingBase = (EntityLivingBase) entity;
                if (!isValid(livingBase, range.getValue().doubleValue())) continue;
                targets.add(livingBase);
            }
        }
         */
        boolean blockTargetD = block.getValue() == BlockMode.None;
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (EntityLivingBase.isEntityLivingBase(entity)){
                EntityLivingBase livingBase = new EntityLivingBase(entity.getWrapObject());
                if (isValid(livingBase,range.getValue().doubleValue())){
                    targets.add(livingBase);
                    if (!blockTargetD){
                        blockTarget = livingBase;
                        blockTargetD = true;
                    }
                }
            }
        }
    }
    private boolean isValid(EntityLivingBase entity, double range) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (AntiBot.isEntityBot(new Entity(entity.getWrapObject())))
            return false;
        if (thePlayer.isPlayerSleeping()
                || thePlayer.isDead()
                || thePlayer.getHealth() <= 0
                || thePlayer.getDistanceToEntity(entity) > range
                || entity.isDead() || entity.getHealth() <= 0
                || EntityArmorStand.isEntityArmorStand(entity)
                || EntityPlayerSP.isEntityPlayerSP(entity)
                || Teams.isTeam(entity)) {
            return false;
        }
        if (!thePlayer.canEntityBeSeen(entity) && !throughWallsValue.getValue())return false;
        if (EntityPlayer.isEntityPlayer(entity)) {
            EntityPlayer player = new EntityPlayer(entity.getWrapObject());

            if (FriendManager.friendsList.contains(player.getName())) return false;

            if (!playerValue.getValue()) return false;

            if (player.isPlayerSleeping()) return false;

            if (player.isPotionActive(Potion.invisibility) && !invisibleValue.getValue()) return false;

        }

        if (EntityAnimal.isEntityAnimal(entity)) return animalValue.getValue();
        if (EntityVillager.isEntityVillager(entity)) return animalValue.getValue();
        if (EntityMob.isEntityMob(entity)) return mobValue.getValue();

        return true;
    }
    private void sortTargets() {
        if (!targets.isEmpty()) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (priority.getValue() == Priority.Range) {
                targets.sort((o1, o2) -> (int) (o1.getDistanceToEntity(thePlayer) - o2.getDistanceToEntity(thePlayer)));
            }else if (priority.getValue() == Priority.Fov) {
                targets.sort(Comparator.comparingDouble(o -> this.getDistanceBetweenAngles(thePlayer.getRotationPitch(), RotationUtils.getHypixelRotations(o)[0])));
            }else if (priority.getValue() == Priority.Angle) {
                targets.sort((o1, o2) -> {
                    float[] rot1 = RotationUtils.getHypixelRotations(o1);
                    float[] rot2 = RotationUtils.getHypixelRotations(o2);
                    return (int) (thePlayer.getRotationYaw() - rot1[0] - (thePlayer.getRotationYaw() - rot2[0]));
                });
            }else if (priority.getValue() == Priority.Health) {
                targets.sort((o1, o2) -> (int) (o1.getHealth() - o2.getHealth()));
            }
        }
    }




    // target hud
    private EntityLivingBase prevTarget;
    private float displayPercent = 0f;
    private long lastUpdate = System.currentTimeMillis();
    public float animation = 0;
    //
    @EventTarget
    public void onRender2D(final EventRender2D e) {
        // 杀戮攻击目标信息
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        GL11.glPushMatrix();
        GL11.glTranslated(scaledresolution.getScaledWidth() / 2, scaledresolution.getScaledHeight() / 2 - 15, 0.0);
        long time = System.currentTimeMillis();
        if (curTarget != null && !curTarget.isNull()) {
            prevTarget = curTarget;
        }
        float pct = (time - lastUpdate) / (20 * 50f);
        lastUpdate = System.currentTimeMillis();
        if (curTarget != null && !curTarget.isNull()) {
            if (displayPercent < 1) {
                displayPercent += pct;
            }
            if (displayPercent > 1) {
                displayPercent = 1f;
            }
        } else {
            if (displayPercent > 0) {
                displayPercent -= pct;
            }
            if (displayPercent < 0) {
                displayPercent = 0f;
                prevTarget = null;
            }
        }
        double percent = MathHelper.easeInQuint(1.0 - displayPercent);
        int xAxis = scaledresolution.getScaledWidth() - 250;
        if (prevTarget != null && !prevTarget.isNull()) {

            FontRenderer fontRenderer = this.mc.getFontRenderer();

            GL11.glTranslated(xAxis * percent, 0.0, 0.0);
            float width2 = Math.max(75, fontRenderer.getStringWidth(prevTarget.getName()) + 20);
            String healthStr2 = Math.round(prevTarget.getHealth() * 10) / 10d + " ❤";

            RenderUtil.GuiRender.drawBorderedRect(0, 0, 55 + width2, 47, 0.5f, new Color(0, 0, 0, 140), new Color(0, 0, 0));

            fontRenderer.drawStringWithShadow(prevTarget.getName(), 35, 3f, Color.WHITE.getRGB());

            boolean isNaN = Float.isNaN(prevTarget.getHealth());
            float health = isNaN ? 20 : prevTarget.getHealth();
            float maxHealth = isNaN ? 20 : prevTarget.getMaxHealth();
            float healthPercent = MathHelper.clampValue(health / maxHealth, 0, 1);

            int hue = (int) (healthPercent * 120);
            Color color = Color.getHSBColor(hue / 360f, 0.7f, 1f);

            GlStateManager.pushMatrix();
            GlStateManager.scale(2.0, 2.0, 2.0);
            fontRenderer.drawStringWithShadow(healthStr2, 18, 7.5f, color.getRGB());
            GlStateManager.popMatrix();

            RenderUtil.drawRect(36, 36.5f, 45 + width2, 44.5f, RenderUtil.reAlpha(color.darker().darker().getRGB(), 0.35f));


            float barWidth = (43 + width2 - 2) - 37;
            float drawPercent = 43 + (barWidth / 100) * (healthPercent * 100);

            if (this.animation <= 0) {
                this.animation = drawPercent;
            }

            if (prevTarget.getHurtTime() <= 6) {
                this.animation = AnimationUtils.getAnimationState(this.animation, drawPercent, (float) Math.max(10, (Math.abs(this.animation - drawPercent) * 30) * 0.4));
            }
            RenderUtil.drawRect(36, 36.5f, this.animation + 6, 44.5f, color.darker().darker().getRGB());
            RenderUtil.drawRect(36, 36.5f, this.animation, 44.5f, color.getRGB());
            RenderUtil.drawRect(36, 36.5f, drawPercent, 44.5f, color.getRGB());

            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();

            GlStateManager.resetColor();
            // 3D model of the target
            GlStateManager.disableBlend();
            GlStateManager.color(1, 1, 1, 1);

            RenderUtil.drawEntityOnScreen(17, 46, (int) (42 / prevTarget.getHeight()), 0, 0, prevTarget, 165);

        }
        GL11.glPopMatrix();
    }
    private float getDistanceBetweenAngles(float angle1, float angle2) {
        float agl = Math.abs(angle1 - angle2) % 360.0f;
        if (agl > 180.0f) {
            agl = 0.0f;
        }
        return agl-1;
    }
    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }
    public enum Mode{
        Switch,
        Single,
        Multi
    }
    public enum AttackMode{
        Pre,
        Post
    }
    public enum BlockMode{
        None,
        Always,
        NCP,
        Hypixel,
    }
    public enum Priority{
        Range,
        Fov,
        Angle,
        Health
    }
    public enum ESPMode{
        Jello,
        FDPJello,
        RainBow,
        Box,
        LiquidBounce,
        None
    }
}
