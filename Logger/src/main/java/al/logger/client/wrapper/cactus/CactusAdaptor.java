package al.logger.client.wrapper.cactus;

import al.logger.client.Logger;
import al.logger.client.transform.transformers.ClassDumpTransformer;
import al.logger.client.utils.FileUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.gui.GuiIngame;
import al.logger.client.wrapper.LoggerMC.render.FontRenderer;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.LoggerMC.GameSettings;
import al.logger.libs.asm.tree.MethodNode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CactusAdaptor {
    private static List<ClassDumpTransformer> transformers = new ArrayList<>();
    public static void deobfuscate() throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        Map<String,byte[]> deobfuscationFiles = FileUtils.processZip(Logger.getInstance().resourceManager.getResourceBytes("DeobfuscationKit.zip"));
        for (CactusWrappingInfo cactusWrappingInfo : Wrapper.getCactusWrapping()) {
            System.out.println("cactusWrappingInfo.getTargetClass().getName() = " + cactusWrappingInfo.getTargetClass().getName());
            if (!classes.contains(cactusWrappingInfo.getTargetClass())){
                classes.add(cactusWrappingInfo.getTargetClass());
                Logger.getInstance().getAgent().nativeLog("Added: "+cactusWrappingInfo.getTargetClass().getCanonicalName());
            }
        }
        for (Class<?> aClass : classes) {
            transformers.add(new ClassDumpTransformer(aClass));
        }
        for (ClassDumpTransformer transformer : transformers) {
            Logger.getInstance().transformManager.addTransformer(transformer);
        }
        Logger.getInstance().transformManager.transform();
        Logger.getInstance().addProgressBar("Fixing",Wrapper.getCactusWrapping().size());
        Logger.getInstance().getAgent().nativeLog("Wrapping");
        int index = 0;
        for (CactusWrappingInfo cactusWrappingInfo : Wrapper.getCactusWrapping()) {
            index ++;
            Logger.getInstance().setProgressBar("Fixing",index);
            if (cactusWrappingInfo.getAnnotation() instanceof WrapMethod){
                WrapMethod method = (WrapMethod) cactusWrappingInfo.getAnnotation();
                Cactus cactus = new Cactus();

                byte[] deobfClassBytes = deobfuscationFiles.get(cactusWrappingInfo.getTargetClass().getSimpleName() + ".deobf.class");
                Logger.getInstance().getAgent().nativeLog(cactusWrappingInfo.getTargetClass().getSimpleName() + ".deobf.class");
                byte[] obfuscateClassBytes = null;
                for (ClassDumpTransformer transformer : transformers) {
                    if (transformer.getTarget() == cactusWrappingInfo.getTargetClass()){
                        obfuscateClassBytes = transformer.getClassBytes();
                        //Logger.getInstance().getAgent().nativeLog(obfuscateClassBytes.toString());
                        break;
                    }
                }
                String deobfConfig = "m "+method.mcpName() +" "+method.signature();
                //Logger.getInstance().getAgent().nativeLog(deobfClassBytes.toString());
                cactus.input(deobfClassBytes,obfuscateClassBytes,deobfConfig);
                Logger.getInstance().getAgent().nativeLog(deobfConfig);
                DeobfuscationResult result = cactus.run();
                if (result.getStatus() == DeobfuscationResult.Status.Fail){
                    Logger.getInstance().getAgent().nativeLog("爆啦1");
                    Logger.getInstance().removeProgressBar("Fixing");
                    throw new Exception("Deobfuscation fail "+method.mcpName() +" "+method.signature() +" "+result.getReason());
                }
                DeobfuscationResult.DeobfuscationItem item = result.getResults().get(0);
                MethodNode methodNode = (MethodNode) item.getOrigin();
                Signature signature = new Signature(methodNode.desc);
                signature.parse();
                Method method1 = cactusWrappingInfo.getTargetClass().getDeclaredMethod(methodNode.name,signature.getParameter());
                method1.setAccessible(true);
                System.out.println(method.mcpName() + " "+method.signature()+" located");
                cactusWrappingInfo.getWrapper().set(null,method1);
            }
        }
        Logger.getInstance().removeProgressBar("Fixing");
    }
    public static void locateVariable(){
        for (Field declaredField : Minecraft.MinecraftClass.getDeclaredFields()) {
            if (declaredField.getType() == GuiIngame.GuiIngameClass){
                declaredField.setAccessible(true);
                Minecraft.ingameGUI = declaredField;
                System.out.println("ingameGui located");
                Logger.getInstance().getAgent().nativeLog("ingameGui located");
            }
            if (declaredField.getType() == GameSettings.GameSettingsClass){
                declaredField.setAccessible(true);
                Minecraft.gameSettings = declaredField;
                System.out.println("gameSettings located");
                Logger.getInstance().getAgent().nativeLog("gameSettings located");
            }
            if (declaredField.getType() == FontRenderer.FontRendererClass){
                if (declaredField.getName().startsWith("Na")){
                    declaredField.setAccessible(true);
                    Minecraft.fontRendererObj = declaredField;
                    System.out.println("fontRendererObj located");
                    Logger.getInstance().getAgent().nativeLog("fontRendererObj located");
                }
            }
        }
        for (Field declaredField : Entity.EntityClass.getDeclaredFields()) {
            if (declaredField.getType() == float.class){
                if (declaredField.getName().length() > 100){
                    declaredField.setAccessible(true);
                    Entity.stepHeight = declaredField;
                    System.out.println("stepHeight located");
                    Logger.getInstance().getAgent().nativeLog("stepHeight located");
                }
            }
        }
    }
}
