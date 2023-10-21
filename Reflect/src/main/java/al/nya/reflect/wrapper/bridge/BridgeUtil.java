package al.nya.reflect.wrapper.bridge;

import al.nya.reflect.libraries.reflectasm.*;
import al.nya.reflect.transform.ReflectNative;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiButton;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.gui.IGuiScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BridgeUtil {
    public static Class<?> GuiScreenBridge = null;
    public static Class<?> GuiChatReplace = null;
    public static Class<?> MargeleAntiCheatAccessor = null;
    public static void init(){
        GuiScreenBridge = ReflectNative.defineClass(BridgeUtil.class.getClassLoader(),buildGuiScreenBridge("al.nya.reflect.bridge.GuiScreenBridge"));
        GuiChatReplace = ReflectNative.defineClass(BridgeUtil.class.getClassLoader(),buildGuiChatBridge("al.nya.reflect.bridge.GuiCommandReplace"));
        if (MargeleAntiCheatDetector.isMAC())
        MargeleAntiCheatAccessor = ReflectNative.defineClass(BridgeUtil.class.getClassLoader(),buildMargeleAntiCheatAccessor("al.nya.reflect.bridge.accessor.MargeleAntiCheatAccessor"));
    }
    public static IGuiScreen createGuiScreen(GuiScreenImpl guiScreen){
        try {
            Constructor<?> constructor = GuiScreenBridge.getConstructor(GuiScreenImpl.class);
            return new IGuiScreen(constructor.newInstance(guiScreen));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new NullPointerException("Failed to create GuiScreen");
        }
    }
    public static IGuiScreen createGuiChat(GuiScreenImpl guiScreen){
        try {
            Constructor<?> constructor = GuiChatReplace.getConstructor(GuiScreenImpl.class);
            return new IGuiScreen(constructor.newInstance(guiScreen));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new NullPointerException("Failed to create GuiScreen");
        }
    }

    public static byte[] buildMargeleAntiCheatAccessor(String pkg){
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,pkg.replace(".","/")
                ,null, Object.class.getCanonicalName().replace(".","/"),new String[]{});
        MethodVisitor setCheatVL = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,"setCheatVL","(I)V",null,null);
        setCheatVL.visitVarInsn(Opcodes.ILOAD, 0);
        setCheatVL.visitFieldInsn(Opcodes.PUTSTATIC, Type.getInternalName(MargeleAntiCheatDetector.getMAC()), "g", "I");
        setCheatVL.visitInsn(Opcodes.RETURN);
        //setCheatVL.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(MargeleAntiCheatDetector.getMAC()),"setCheatVL","(I)V",false);
        setCheatVL.visitEnd();
        MethodVisitor getCheatVL = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,"getCheatVL","()I",null,null);
        getCheatVL.visitFieldInsn(Opcodes.GETSTATIC, Type.getInternalName(MargeleAntiCheatDetector.getMAC()), "g", "I");
        getCheatVL.visitInsn(Opcodes.IRETURN);
        //setCheatVL.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(MargeleAntiCheatDetector.getMAC()),"setCheatVL","(I)V",false);
        getCheatVL.visitEnd();
        return cw.toByteArray();
    }

    public static byte[] buildGuiChatBridge(String pkg){
        return buildGuiScreenBridge(GuiChat.GuiChatClass,pkg);
    }
    public static byte[] buildGuiScreenBridge(String pkg){
        return buildGuiScreenBridge(GuiScreen.GuiScreenClass,pkg);
    }
    public static byte[] buildGuiScreenBridge(Class superClass,String pkg){
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        String wrappedScreen = "wrappedScreen";
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,pkg.replace(".","/")
                ,null, superClass.getCanonicalName().replace(".","/"),new String[]{});
        cw.visitField(Opcodes.ACC_PRIVATE,wrappedScreen,"L"+Type.getInternalName(GuiScreenImpl.class)+";",null,null);
        MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>","(L"+Type.getInternalName(GuiScreenImpl.class)+";)V",null,null);
        //    ALOAD 0
        //    INVOKESPECIAL java/lang/Object.<init> ()V
        //    ALOAD 0
        //    ALOAD 1
        //    PUTFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    RETURN
        //    MAXSTACK = 2
        //    MAXLOCALS = 2

        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitMethodInsn(Opcodes.INVOKESPECIAL, superClass.getCanonicalName().replace(".", "/"), "<init>", "()V");
        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitVarInsn(Opcodes.ALOAD, 1);
        init.visitFieldInsn(Opcodes.PUTFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        init.visitTypeInsn(Opcodes.NEW, Type.getInternalName(GuiScreen.class));
        init.visitInsn(Opcodes.DUP);
        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(GuiScreen.class), "<init>", "(Ljava/lang/Object;)V");
        init.visitFieldInsn(Opcodes.PUTFIELD, Type.getInternalName(GuiScreenImpl.class), "renderingObject", "L" + Type.getInternalName(GuiScreen.class) + ";");
        init.visitInsn(Opcodes.RETURN);

        init.visitMaxs(8, 8);
        init.visitEnd();
        MethodVisitor drawScreen = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.drawScreen.getName(), "(IIF)V", null, null);

        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    ILOAD 1
        //    ILOAD 2
        //    FLOAD 3
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.drawScreen (IIF)V
        //    RETURN
        drawScreen.visitVarInsn(Opcodes.ALOAD,0);
        drawScreen.visitFieldInsn(Opcodes.GETFIELD,pkg.replace(".","/"),wrappedScreen,"L"+Type.getInternalName(GuiScreenImpl.class)+";");
        drawScreen.visitVarInsn(Opcodes.ILOAD,1);
        drawScreen.visitVarInsn(Opcodes.ILOAD,2);
        drawScreen.visitVarInsn(Opcodes.FLOAD,3);
        drawScreen.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"drawScreen","(IIF)V");
        drawScreen.visitInsn(Opcodes.RETURN);

        drawScreen.visitMaxs(4,4);
        drawScreen.visitEnd();

        MethodVisitor initGui = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.initGui.getName(),"()V",null,null);
        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.initGui ()V
        //    RETURN
        initGui.visitVarInsn(Opcodes.ALOAD,0);
        initGui.visitFieldInsn(Opcodes.GETFIELD,pkg.replace(".","/"),wrappedScreen,"L"+Type.getInternalName(GuiScreenImpl.class)+";");
        initGui.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"initGui","()V");
        initGui.visitInsn(Opcodes.RETURN);

        initGui.visitMaxs(1,1);
        initGui.visitEnd();
        MethodVisitor onGuiClosed = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.onGuiClosed.getName(),"()V",null,null);
        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.onGuiClosed ()V
        //    RETURN
        onGuiClosed.visitVarInsn(Opcodes.ALOAD,0);
        onGuiClosed.visitFieldInsn(Opcodes.GETFIELD,pkg.replace(".","/"),wrappedScreen,"L"+Type.getInternalName(GuiScreenImpl.class)+";");
        onGuiClosed.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"onGuiClosed","()V");
        onGuiClosed.visitInsn(Opcodes.RETURN);

        onGuiClosed.visitMaxs(1,1);
        onGuiClosed.visitEnd();
        MethodVisitor keyTyped = cw.visitMethod(Opcodes.ACC_PROTECTED,GuiScreen.keyTyped.getName(),"(CI)V",null,null);
        //ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    ILOAD 1
        //    ILOAD 2
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.keyTyped (CI)Z
        //    IFEQ L0
        //    ALOAD 0
        //    ILOAD 1
        //    ILOAD 2
        //    INVOKESPECIAL al/nya/reflect/wrapper/wraps/wrapper/gui/GuiScreen.keyTyped (CI)V
        //   L0
        //   FRAME SAME
        //    RETURN
        Label L0 = new Label();
        keyTyped.visitVarInsn(Opcodes.ALOAD,0);
        keyTyped.visitFieldInsn(Opcodes.GETFIELD,pkg.replace(".","/"),wrappedScreen,"L"+Type.getInternalName(GuiScreenImpl.class)+";");
        keyTyped.visitVarInsn(Opcodes.ILOAD,1);
        keyTyped.visitVarInsn(Opcodes.ILOAD,2);
        keyTyped.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"keyTyped","(CI)Z");
        keyTyped.visitJumpInsn(Opcodes.IFEQ,L0);
        keyTyped.visitVarInsn(Opcodes.ALOAD,0);
        keyTyped.visitVarInsn(Opcodes.ILOAD,1);
        keyTyped.visitVarInsn(Opcodes.ILOAD,2);
        keyTyped.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass),GuiScreen.keyTyped.getName(),"(CI)V");
        keyTyped.visitLabel(L0);
        keyTyped.visitFrame(Opcodes.F_SAME,0,null,0,null);
        keyTyped.visitInsn(Opcodes.RETURN);

        keyTyped.visitMaxs(3,3);
        keyTyped.visitEnd();
        MethodVisitor mouseClicked = cw.visitMethod(Opcodes.ACC_PROTECTED,GuiScreen.mouseClicked.getName(),"(III)V",null,null);
        mouseClicked.visitVarInsn(Opcodes.ALOAD,0);
        mouseClicked.visitFieldInsn(Opcodes.GETFIELD,pkg.replace(".","/"),wrappedScreen,"L"+Type.getInternalName(GuiScreenImpl.class)+";");
        mouseClicked.visitVarInsn(Opcodes.ILOAD,1);
        mouseClicked.visitVarInsn(Opcodes.ILOAD,2);
        mouseClicked.visitVarInsn(Opcodes.ILOAD,3);
        mouseClicked.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"mouseClicked","(III)V");
        mouseClicked.visitInsn(Opcodes.RETURN);

        mouseClicked.visitMaxs(4,4);
        mouseClicked.visitEnd();
        MethodVisitor updateScreen = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.updateScreen.getName(),"()V",null,null);
        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.updateScreen ()V
        //    RETURN
        updateScreen.visitVarInsn(Opcodes.ALOAD, 0);
        updateScreen.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        updateScreen.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "updateScreen", "()V");
        updateScreen.visitInsn(Opcodes.RETURN);

        updateScreen.visitMaxs(1, 1);
        updateScreen.visitEnd();

        MethodVisitor actionPerformed = cw.visitMethod(Opcodes.ACC_PROTECTED, GuiScreen.actionPerformed.getName(), "(L" + Type.getInternalName(GuiButton.GuiButtonClass) + ";)V", null, null);
        actionPerformed.visitVarInsn(Opcodes.ALOAD, 0);
        actionPerformed.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        actionPerformed.visitTypeInsn(Opcodes.NEW, Type.getInternalName(GuiButton.class));
        actionPerformed.visitInsn(Opcodes.DUP);
        actionPerformed.visitVarInsn(Opcodes.ALOAD, 1);
        actionPerformed.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(GuiButton.class), "<init>", "(Ljava/lang/Object;)V");
        actionPerformed.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "actionPerformed", "(L" + Type.getInternalName(GuiButton.class) + ";)V");
        actionPerformed.visitInsn(Opcodes.RETURN);
        actionPerformed.visitEnd();

        actionPerformed.visitMaxs(4, 3);
        return cw.toByteArray();
    }
}
