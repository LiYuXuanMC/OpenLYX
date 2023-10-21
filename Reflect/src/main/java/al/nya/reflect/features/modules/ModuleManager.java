package al.nya.reflect.features.modules;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.modules.Combat.*;
import al.nya.reflect.features.modules.Ghost.*;
import al.nya.reflect.features.modules.Movement.*;
import al.nya.reflect.features.modules.Player.*;
import al.nya.reflect.features.modules.Visual.*;
import al.nya.reflect.features.modules.Visual.hud.implement.InventoryHUD;
import al.nya.reflect.features.modules.Visual.hud.implement.KeybindsHUD;
import al.nya.reflect.features.modules.Visual.hud.implement.RadarHUD;
import al.nya.reflect.features.modules.Visual.hud.implement.ScoreboardHUD;
import al.nya.reflect.features.modules.World.*;
import al.nya.reflect.features.modules.World.disablers.DisablerMode;
import al.nya.reflect.features.modules.adaptive.v1_12_2.AutoTool1_12_2;
import al.nya.reflect.features.modules.adaptive.v1_12_2.Freecam1_12_2;
import al.nya.reflect.features.modules.minigames.MurderDetector;
import al.nya.reflect.utils.client.DoMCerDetector;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.utils.render.RotationAnimation;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final ArrayList<Module> modules = new ArrayList<>();
    private static ModuleType getModuleByType_LastType = null;
    private static ArrayList<Module> getModuleByType_LastMoulde = null;
    @Getter
    private static CombatManager combatManager;

    public static void init() {
        combatManager = new CombatManager();
        if (Wrapper.env.equals(Maps.Srg1_8_9)) {
            loadModules(new KillAura(), new AutoClicker(), new HUD(), new ClickGui(), new ESP(), new Sprint(), new Reach(), new Velocity(), new Teams());
            loadModules(new AntiBot(), new AimBot(), new Speed(), new SuperKnockBack(), new TargetStrafe(), new RotationAnimation());
            loadModules(new SafeWalk(), new Scaffold(), new ChestStealer(), new SpeedMine(), new AutoTool(), new AutoArmor());
            loadModules(new InvManager(), new NoSlow(), new FullBright(), new NoFall(), new Step(), new HitBoxes(), new NoteBotModule());
            loadModules(new Disabler(), new AirJump(), new WayLine(), new Flight(), new IRC(), new Timer(), new BedNuker());
            loadModules(new NameTags(), new BowAimBot(), new FastBow(), new Projectiles(), new Freeze(), new LongJump(), new NoJumpDelay());
            loadModules(new AutoL(), new SelfRescue(), new NoWeb(), new InvMove(), new TeleportHit(), new Regen(), new UHCHelper());
            loadModules(new Phase(), new Criticals(), new Trigger(), new Strafe(), new AutoGG(), new PointerESP(), new Waypoints());
            loadModules(new XRay(), new BigGod(), new FutaESP(), new PullBack(), new NoVoid(), new Ambience(), new LightningDetect(), new GodBridgeHelper());
            loadModules(new NameProtect(), new StaffAnalyser(), new KeyBindManager(), new BoatFly(), new Chams(), new Tracers(), new FastPlace());
            loadModules(new AutoSupplies(), new Blur(), new SelfDamage(), new ChinaHat(), new AutoLevelUp(), new InputFix(), new MouseoverTips());
            loadModules(new KeepSprint(), new FakeID(), new Freecam(), new Blink(), new MurderDetector(), new Spin(), new FastUse());
        }
        if (Wrapper.env.equals(Maps.Srg1_12_2)) {
            loadModules(new KillAura(), new AutoClicker(), new HUD(), new ClickGui(), new ESP(), new Sprint(), new Reach(), new Velocity(), new Teams());
            loadModules(new AntiBot(), new AimBot(), new Speed(), new SuperKnockBack(), new TargetStrafe(), new RotationAnimation());
            loadModules(new SafeWalk(), new Scaffold(), new ChestStealer(), new SpeedMine(), new AutoTool1_12_2(), new AutoArmor());
            loadModules(new InvManager(), new NoSlow(), new FullBright(), new NoFall(), new Step(), new HitBoxes(), new NoteBotModule());
            loadModules(new Disabler(), new AirJump(), new WayLine(), new Flight(), new IRC(), new Timer(), new BedNuker());
            loadModules(new NameTags(), new BowAimBot(), new FastBow(), new Projectiles(), new Freeze(), new LongJump(), new NoJumpDelay());
            loadModules(new AutoL(), new SelfRescue(), new NoWeb(), new InvMove(), new TeleportHit(), new Regen(), new UHCHelper());
            loadModules(new Phase(), new Criticals(), new Trigger(), new Strafe(), new AutoGG(), new PointerESP(), new Waypoints());
            loadModules(new XRay(), new BigGod(), new FutaESP(), new PullBack(), new NoVoid(), new Ambience(), new LightningDetect(), new GodBridgeHelper());
            loadModules(new NameProtect(), new StaffAnalyser(), new KeyBindManager(), new BoatFly(), new Chams(), new Tracers(), new FastPlace());
            loadModules(new AutoSupplies(), new Blur(), new SelfDamage(), new ChinaHat(), new AutoLevelUp(), new InputFix(), new MouseoverTips());
            loadModules(new KeepSprint(), new FakeID(), new Freecam1_12_2(), new Blink());
        }
        HUD.hudObjects.add(HUD.scoreboardHUD = new ScoreboardHUD());
        HUD.hudObjects.add(new KeybindsHUD());
        HUD.hudObjects.add(new InventoryHUD());
        HUD.hudObjects.add(new RadarHUD());

        //if (Reflect.USER.rank.equals("Dev")) loadModule(new MagicKillaura());
        if (Reflect.debug) loadModule(new Test());
        if (Reflect.debug) loadModule(new FarPlay());
        if (DoMCerDetector.isDomcer()) {
            loadModule(new AntiScreenshot());
            ModuleManager.getModule(AntiScreenshot.class).setEnableNoNotification(true);
            ModuleManager.getModule(BigGod.class).mode.setValue(BigGod.BigHeadType.WangHang);
        }
        if (MargeleAntiCheatDetector.getHyGui() != null) {
            loadModule(new AntiScreenshot());
            ModuleManager.getModule(AntiScreenshot.class).setEnableNoNotification(true);
        }
        if (MargeleAntiCheatDetector.isMAC() && Reflect.USER.isBeta()) {
            ModuleManager.getModule(Disabler.class).mode.setValue(DisablerMode.MAC);
            ModuleManager.getModule(Disabler.class).setEnableNoNotification(true);
        }
    }
    public static void disable(Class<? extends Module> module){
        Module m = getModule(module);
        if (m.isEnable()) m.setEnable(false);
    }
    public static void enable(Class<? extends Module> module){
        Module m = getModule(module);
        if (!m.isEnable()) m.setEnable(true);
    }
    public static void loadModule(Module module){
        modules.add(module);
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }
    public static void loadModules(Module... modules){
        for (Module module : modules) {
            loadModule(module);
        }
    }
    public static <T extends Module> T getModule(Class<T> tClass){
        for (Module module : modules) {
            if (module.getClass() == tClass){
                return (T) module;
            }
        }
        throw new NullPointerException("Could not find module " + tClass.getName());
    }
    public static List<Module> getModuleByType(ModuleType moduleType) {
        if (moduleType == getModuleByType_LastType && getModuleByType_LastMoulde != null) {
            return getModuleByType_LastMoulde;
        }
        ArrayList<Module> m = new ArrayList<>();
        if (moduleType == ModuleType.Favorite) {
            for (Module module : modules) {
                if (module.isFavorite())
                    m.add(module);
            }
        } else
            for (Module module : modules) {
                if (module.getModuleType() == moduleType) {
                    m.add(module);
                }
            }
        getModuleByType_LastType = moduleType;
        getModuleByType_LastMoulde = m;
        return m;
    }
    public static Module getModuleByName(String name){
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)){
                return module;
            }
        }
        throw new NullPointerException("Could not find module " + name);
    }
}
