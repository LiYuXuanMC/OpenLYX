package al.logger.client.features.modules;

import al.logger.client.Logger;
import al.logger.client.LoggerWS;
import al.logger.client.features.modules.impls.Combat.*;
import al.logger.client.features.modules.impls.Misc.MidClickFriend;
import al.logger.client.features.modules.impls.Misc.Music;
import al.logger.client.features.modules.impls.Movement.*;
import al.logger.client.features.modules.impls.Movement.speeds.matrix.Matrix;
import al.logger.client.features.modules.impls.Player.*;
import al.logger.client.features.modules.impls.Visual.*;
import al.logger.client.features.modules.impls.Visual.MotionBlur.MotionBlur;
import al.logger.client.features.modules.impls.World.*;
import al.logger.client.ui.bases.components.InstanceEx;
import al.logger.client.ui.bases.components.NextEx;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.NotNative;
import est.builder.annotations.Clear;
import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Native
public class ModuleManager {
    @Getter
    private List<Module> modules = new ArrayList<>();
    @Getter
    private List<Module> plugin_modules = new ArrayList<>();
    public ModuleManager() {

    }

    public void init(Class<?> aClazz) {

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        if (Logger.getInstance().isMySelfObf()) {
            if (stackTraceElement.isNativeMethod()) {
                if (stackTraceElement.getClassName().equals(LoggerWS.class.getName())) {
                    Exception exp = LoggerWS.queue.poll();
                    if (exp instanceof NextEx) {
                        NextEx tmp = (NextEx) exp;
                        if (tmp.getNote().equals("M")) {
                            if (LoggerWS.queue.size() == 0) {

                            } else {
                                //Crash
                                Field F = null;
                                try {
                                    F = Unsafe.class.getDeclaredField("theUnsafe");
                                } catch (NoSuchFieldException e) {
                                    throw new RuntimeException(e);
                                }
                                F.setAccessible(true);
                                try {
                                    ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                                    ((Unsafe) F.get(null)).putAddress(324232, 23424);
                                    ((Unsafe) F.get(null)).putAddress(423234, 234232);
                                    ((Unsafe) F.get(null)).putAddress(42342, 4324);
                                    ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                                return;
                            }
                        } else {
                            //Crash
                            Field F = null;
                            try {
                                F = Unsafe.class.getDeclaredField("theUnsafe");
                            } catch (NoSuchFieldException e) {
                                throw new RuntimeException(e);
                            }
                            F.setAccessible(true);
                            try {
                                ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                                ((Unsafe) F.get(null)).putAddress(324232, 23424);
                                ((Unsafe) F.get(null)).putAddress(423234, 234232);
                                ((Unsafe) F.get(null)).putAddress(42342, 4324);
                                ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                            return;
                        }
                    }
                } else {
                    //Crash
                    Field F = null;
                    try {
                        F = Unsafe.class.getDeclaredField("theUnsafe");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    F.setAccessible(true);
                    try {
                        ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                        ((Unsafe) F.get(null)).putAddress(324232, 23424);
                        ((Unsafe) F.get(null)).putAddress(423234, 234232);
                        ((Unsafe) F.get(null)).putAddress(42342, 4324);
                        ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            } else {
                //Crash
                Field F = null;
                try {
                    F = Unsafe.class.getDeclaredField("theUnsafe");
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                F.setAccessible(true);
                try {
                    ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                    ((Unsafe) F.get(null)).putAddress(324232, 23424);
                    ((Unsafe) F.get(null)).putAddress(423234, 234232);
                    ((Unsafe) F.get(null)).putAddress(42342, 4324);
                    ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }

        if (Logger.getInstance().getLoggerUser().getUserid() == null) {
            Field F = null;
            try {
                F = Unsafe.class.getDeclaredField("theUnsafe");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            F.setAccessible(true);
            try {
                ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                ((Unsafe) F.get(null)).putAddress(324232, 23424);
                ((Unsafe) F.get(null)).putAddress(423234, 234232);
                ((Unsafe) F.get(null)).putAddress(42342, 4324);
                ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        //Misc
        addModule(new Music());
        addModule(new MidClickFriend());
        //Visual
        addModule(new Toluene());
        addModule(new MusicApp());
        addModule(new Notification());
        addModule(new TemplateGui());
        addModule(new ClickGUI());
        addModule(new RotationAnimation());
        addModule(new TabList());
        addModule(new BlockAnimation());
        addModule(new MotionBlur());
        addModule(new Radar());
        addModule(new Hud());
        addModule(new KeyStrokes());
        addModule(new FPSDisplay());
        addModule(new XYZDisplay());
        addModule(new HeadDisplay());
        addModule(new Chams());
        addModule(new FullBright());
        addModule(new NameProtect());
        addModule(new Ambience());
        addModule(new NoHurtCam());
        addModule(new CameraClip());
        addModule(new ESP());
        addModule(new Loli());
        //World
        addModule(new CommandHandler());
        addModule(new KeyInputHandler());
        addModule(new InputFix());
        addModule(new Scaffold());
        addModule(new Team());
        addModule(new Target());
        addModule(new AntiBot());
        addModule(new Timer());
        addModule(new Freecam());
        addModule(new Disabler());
        addModule(new AuthEntityPlayer());
        //Movement
        addModule(new Sprint());
        addModule(new KeepSprint());
        addModule(new Velocity());
        addModule(new NoSlow());
        addModule(new Flight());
        addModule(new NoJumpDelay());
        addModule(new Strafe());
        addModule(new Speed());
        addModule(new Step());
        addModule(new InventoryMove());
        addModule(new Eagle());
        addModule(new AntiVoid());
        addModule(new LongJump());
        addModule(new TargetStrafe());
        addModule(new Jesus());
        //Player
        addModule(new AutoPlace());
        addModule(new FastUse());
        addModule(new Spin());
        addModule(new FastPlace());
        addModule(new AutoTool());
        addModule(new SpeedMine());
        addModule(new Regen());
        addModule(new AutoArmor());
        addModule(new ChestStealer());
        addModule(new NoFall());
        addModule(new InvManager());
        addModule(new AntiFireBall());
        addModule(new KeepContainer());
        //Combat
        //addModule(new UsersCape());
        addModule(new Reach());
        addModule(new SuperKnockBack());
        addModule(new KillAura());
        addModule(new LegitAura());
        addModule(new AutoClicker());
        addModule(new Criticals());
        addModule(new Trigger());
        addModule(new AimAssist());
        addModule(new BowAimBot());
        addModule(new AutoSword());

        if (Logger.getInstance().getLoggerUser().getPower() == 10 || Logger.getInstance().getLoggerUser().getPower() == 255) {
            registerBetaOnly();
        }

        throw new InstanceEx(Integer.parseInt(Logger.getInstance().getLoggerUser().getInstanceTokenMod().substring(0, 3)), Integer.parseInt(Logger.getInstance().getLoggerUser().getInstanceTokenMod().substring(3, 5)), Logger.getInstance().getLoggerUser().getInstanceTokenMod().substring(5), aClazz);
    }

    @Clear(when = "Release")
    private void registerBetaOnly() {
        addModule(new BoatJump());
        addModule(new Freeze());
        addModule(new Blink());
        if (EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat))
            addModule(new Collapse());
        if (EnvironmentDetector.hasAntiCheat(Environment.EnsembleAntiCheat))
            addModule(new EACDisabler());
    }

    private void addModules(String s) {
        //谨以此段注释 纪念2023年2月 傻逼的千鹤
/*        try {
            modules.add((Module) Class.forName(s).newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    @NotNative
    public void addModule(Module s) {
        modules.add(s);
    }
    @NotNative
    public void addPluginModule(Module s) {
        plugin_modules.add(s);
        modules.add(s);
    }

    @NotNative
    public Module getModule(String arg) {
        for (Module mod : this.getModules()) {
            if (mod.getName().equalsIgnoreCase(arg)) {
                return mod;
            }
        }
        return null;
    }

    @ExportObfuscate(name = "getModule")
    @NotNative
    public Module getModule(Class arg) {
        for (Module mod : this.getModules()) {
            if (mod.getClass().equals(arg)) {
                return mod;
            }
        }
        return null;
    }

    @NotNative
    public Module getByDisplayName(String arg) {
        for (Module mod : this.getModules()) {
            if (mod.getDisplayName().equals(arg)) {
                return mod;
            }
        }
        return null;
    }

    @NotNative
    public ArrayList<Module> getByCategory(Category value) {
        ArrayList<Module> result = new ArrayList<>();
        for (Module mod : this.getModules()) {
            if (mod.getCategory().equals(value)) {
                result.add(mod);
            }
        }
        return result;
    }
}
