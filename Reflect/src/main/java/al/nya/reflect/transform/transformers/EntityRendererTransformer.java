package al.nya.reflect.transform.transformers;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.Event;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.libraries.reflectasm.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.Profiler;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import al.nya.reflect.wrapper.wraps.wrapper.render.EntityRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import by.radioegor146.nativeobfuscator.Native;

import java.util.List;

public class EntityRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityRenderer.EntityRendererClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityRenderer.getMouseOver.getName()) && method.desc.equals("(F)V") && MargeleAntiCheatDetector.isMAC()){
                InsnList hookInsn = new InsnList();
                LabelNode L0 = new LabelNode();
                hookInsn.add(new VarInsnNode(FLOAD,1));
                hookInsn.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class),"getMouseOverHook", "(F)Z"));
                hookInsn.add(new JumpInsnNode(IFEQ,L0));
                hookInsn.add(new InsnNode(RETURN));
                hookInsn.add(L0);
                method.instructions.insert(hookInsn);
            }
            /*if (method.name.equals(EntityRenderer.getMouseOver.getName()) && method.desc.equals("(F)V") && MargeleAntiCheatDetector.isMAC()){
                LocalVariableNode entity = new LocalVariableNode("entity", Type.getDescriptor(Entity.EntityClass), null, new LabelNode(), new LabelNode(),2);
                LocalVariableNode d0 = new LocalVariableNode("blockReachDistance", Type.getDescriptor(double.class), null, new LabelNode(), new LabelNode(),3);
                LocalVariableNode d1 = new LocalVariableNode("d1", Type.getDescriptor(double.class), null, new LabelNode(), new LabelNode(),5);
                LocalVariableNode vec3 = new LocalVariableNode("vec3", Type.getDescriptor(Vec3.Vec3Class), null, new LabelNode(), new LabelNode(),7);
                LocalVariableNode flag = new LocalVariableNode("flag", Type.getDescriptor(boolean.class), null, new LabelNode(), new LabelNode(),8);
                LocalVariableNode i = new LocalVariableNode("i", Type.getDescriptor(int.class), null, new LabelNode(), new LabelNode(),9);
                LocalVariableNode vec31 = new LocalVariableNode("vec31", Type.getDescriptor(Vec3.Vec3Class), null, new LabelNode(), new LabelNode(),10);
                LocalVariableNode vec32 = new LocalVariableNode("vec32", Type.getDescriptor(Vec3.Vec3Class), null, new LabelNode(), new LabelNode(),11);
                LocalVariableNode vec33 = new LocalVariableNode("vec33", Type.getDescriptor(Vec3.Vec3Class), null, new LabelNode(), new LabelNode(),12);
                LocalVariableNode f = new LocalVariableNode("f", Type.getDescriptor(float.class), null, new LabelNode(), new LabelNode(),13);
                LocalVariableNode list = new LocalVariableNode("list", Type.getDescriptor(List.class), null, new LabelNode(), new LabelNode(),14);
                LocalVariableNode d2 = new LocalVariableNode("d2", Type.getDescriptor(double.class), null, new LabelNode(), new LabelNode(),15);
                LocalVariableNode j = new LocalVariableNode("j", Type.getDescriptor(int.class), null, new LabelNode(), new LabelNode(),17);

                LabelNode L0 = new LabelNode();
                LabelNode L1 = new LabelNode();
                LabelNode L2 = new LabelNode();
                LabelNode L3 = new LabelNode();
                //Fix getMouseOver
                InsnList fixInsn = new InsnList();
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.getRenderViewEntity.getName(),Type.getMethodDescriptor(Type.getType(Entity.EntityClass))));
                fixInsn.add(new VarInsnNode(ASTORE,entity.index));
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new JumpInsnNode(IFNULL,L0));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.theWorld.getName(),Type.getDescriptor(WorldClient.WorldClientClass)));
                fixInsn.add(new JumpInsnNode(IFNULL,L0));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.mcProfiler.getName(),Type.getDescriptor(Profiler.ProfilerClass)));
                fixInsn.add(new LdcInsnNode("pick"));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Profiler.ProfilerClass),Profiler.startSection.getName(),Type.getMethodDescriptor(Type.getType(Void.TYPE),Type.getType(String.class))));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new InsnNode(ACONST_NULL));
                fixInsn.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.pointedEntity.getName(),Type.getDescriptor(Entity.EntityClass)));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.playerController.getName(),Type.getDescriptor(PlayerControllerMP.PlayerControllerMPClass)));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(PlayerControllerMP.PlayerControllerMPClass),PlayerControllerMP.getBlockReachDistance.getName(),Type.getMethodDescriptor(Type.getType(float.class))));
                fixInsn.add(new InsnNode(F2D));
                fixInsn.add(new VarInsnNode(DSTORE,d0.index));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new VarInsnNode(FLOAD,1));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Entity.EntityClass),Entity.rayTrace.getName(),Type.getMethodDescriptor(Type.getType(MovingObjectPosition.MovingObjectPositionClass),Type.getType(double.class),Type.getType(double.class),Type.getType(float.class))));
                fixInsn.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.objectMouseOver.getName(),Type.getDescriptor(MovingObjectPosition.MovingObjectPositionClass)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new VarInsnNode(DSTORE,d1.index));
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new VarInsnNode(FLOAD,1));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Entity.EntityClass),Entity.getPositionEyes.getName(),Type.getMethodDescriptor(Type.getType(Vec3.Vec3Class),Type.getType(float.class))));
                fixInsn.add(new VarInsnNode(ASTORE,vec3.index));
                fixInsn.add(new InsnNode(ICONST_0));
                fixInsn.add(new VarInsnNode(ISTORE,flag.index));
                fixInsn.add(new InsnNode(ICONST_3));
                fixInsn.add(new VarInsnNode(ISTORE,i.index));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.playerController.getName(),Type.getDescriptor(PlayerControllerMP.PlayerControllerMPClass)));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(PlayerControllerMP.PlayerControllerMPClass),PlayerControllerMP.extendedReach.getName(),Type.getMethodDescriptor(Type.getType(boolean.class))));
                fixInsn.add(new JumpInsnNode(IFNE,L1));
                fixInsn.add(new LdcInsnNode(6.0));
                fixInsn.add(new VarInsnNode(DSTORE,d0.index));
                fixInsn.add(new LdcInsnNode(6.0));
                fixInsn.add(new VarInsnNode(DSTORE,d1.index));
                fixInsn.add(new JumpInsnNode(GOTO,L2));
                fixInsn.add(L1);
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new LdcInsnNode(3.0));
                fixInsn.add(new InsnNode(DCMPL));
                fixInsn.add(new JumpInsnNode(IFLE,L2));
                fixInsn.add(new InsnNode(ICONST_1));
                fixInsn.add(new VarInsnNode(ISTORE,flag.index));
                fixInsn.add(L2);
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.objectMouseOver.getName(),Type.getDescriptor(MovingObjectPosition.MovingObjectPositionClass)));
                fixInsn.add(new JumpInsnNode(IFNULL,L3));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.objectMouseOver.getName(),Type.getDescriptor(MovingObjectPosition.MovingObjectPositionClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass),MovingObjectPosition.hitVec.getName(),Type.getDescriptor(Vec3.Vec3Class)));
                fixInsn.add(new VarInsnNode(ALOAD,vec3.index));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Vec3.Vec3Class),Vec3.distanceTo.getName(),Type.getMethodDescriptor(Type.getType(double.class),Type.getType(Vec3.Vec3Class))));
                fixInsn.add(new VarInsnNode(DSTORE,d0.index));
                fixInsn.add(L3);
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new VarInsnNode(FLOAD,1));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Entity.EntityClass),Entity.getLook.getName(),Type.getMethodDescriptor(Type.getType(Vec3.Vec3Class),Type.getType(float.class))));
                fixInsn.add(new VarInsnNode(ASTORE,vec31.index));
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new VarInsnNode(ALOAD,vec31.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Vec3.Vec3Class),Vec3.xCoord.getName(),Type.getDescriptor(double.class)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new InsnNode(DMUL));
                fixInsn.add(new VarInsnNode(ALOAD,vec31.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Vec3.Vec3Class),Vec3.yCoord.getName(),Type.getDescriptor(double.class)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new InsnNode(DMUL));
                fixInsn.add(new VarInsnNode(ALOAD,vec31.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Vec3.Vec3Class),Vec3.zCoord.getName(),Type.getDescriptor(double.class)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new InsnNode(DMUL));
                fixInsn.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Vec3.Vec3Class),Vec3.addVector.getName(),Type.getMethodDescriptor(Type.getType(Vec3.Vec3Class),Type.getType(double.class),Type.getType(double.class),Type.getType(double.class))));
                fixInsn.add(new VarInsnNode(ASTORE,vec32.index));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new InsnNode(ACONST_NULL));
                fixInsn.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.pointedEntity.getName(),Type.getDescriptor(Entity.EntityClass)));
                fixInsn.add(new InsnNode(ACONST_NULL));
                fixInsn.add(new VarInsnNode(ASTORE,vec33.index));
                fixInsn.add(new InsnNode(FCONST_1));
                fixInsn.add(new VarInsnNode(FSTORE,f.index));
                fixInsn.add(new VarInsnNode(ALOAD,0));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),Type.getDescriptor(Minecraft.MinecraftClass)));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.theWorld.getName(),Type.getDescriptor(WorldClient.WorldClientClass)));
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new VarInsnNode(ALOAD,entity.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.getEntityBoundingBox.getName(),Type.getDescriptor(AxisAlignedBB.AxisAlignedBBClass)));
                fixInsn.add(new VarInsnNode(ALOAD,vec3.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Vec3.Vec3Class),Vec3.xCoord.getName(),Type.getDescriptor(double.class)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new InsnNode(DMUL));
                fixInsn.add(new VarInsnNode(ALOAD,vec3.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Vec3.Vec3Class),Vec3.yCoord.getName(),Type.getDescriptor(double.class)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new InsnNode(DMUL));
                fixInsn.add(new VarInsnNode(ALOAD,vec3.index));
                fixInsn.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Vec3.Vec3Class),Vec3.zCoord.getName(),Type.getDescriptor(double.class)));
                fixInsn.add(new VarInsnNode(DLOAD,d0.index));
                fixInsn.add(new InsnNode(DMUL));
            }

             */
            if (method.name.equals(EntityRenderer.renderWorldPass.getName()) && method.desc.equals("(IFJ)V")){
                System.out.println("transform renderWorldPass");
                InsnList render3D = new InsnList();
                InsnList insnList = new InsnList();
                //GETSTATIC al/nya/reflect/Reflect.Instance : Lal/nya/reflect/Reflect;
                //GETFIELD al/nya/reflect/Reflect.eventBus : Lal/nya/reflect/events/EventBus;
                //NEW al/nya/reflect/events/events/EventRender3D
                //DUP
                //FLOAD 1
                //INVOKESPECIAL al/nya/reflect/events/events/EventRender3D.<init> (F)V
                //INVOKEVIRTUAL al/nya/reflect/events/EventBus.callEvent (Lal/nya/reflect/events/events/Event;)V
                render3D.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(Reflect.class),"Instance","L"+Type.getInternalName(Reflect.class)+";"));
                render3D.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Reflect.class),"eventBus","L"+Type.getInternalName(EventBus.class)+";"));
                render3D.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventRender3D.class)));
                render3D.add(new InsnNode(Opcodes.DUP));
                render3D.add(new VarInsnNode(Opcodes.FLOAD,2));
                render3D.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,Type.getInternalName(EventRender3D.class),"<init>","(F)V"));
                render3D.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventBus.class),"callEvent","(L"+Type.getInternalName(Event.class)+";)V"));
                boolean transformed = false;
                for (AbstractInsnNode instruction : method.instructions) {
                    insnList.add(instruction);
                    if (instruction instanceof MethodInsnNode && (!transformed)){
                        if (((MethodInsnNode) instruction).owner.equals(Type.getInternalName(GlStateManager.GlStateManagerClass))
                                && ((MethodInsnNode) instruction).name.equals(GlStateManager.alphaFunc.getName())&&((MethodInsnNode) instruction).desc.equals("(IF)V")){
                            insnList.add(render3D);
                            transformed = true;
                        }
                    }
                }
                method.instructions = insnList;
                method.maxLocals++;
            }
            if (method.name.equals(EntityRenderer.getMouseOver.getName())&& method.desc.equals("(F)V") && !MargeleAntiCheatDetector.isMAC()){
                System.out.println("transform getMouseOver");
                InsnList insnList = method.instructions;
                MethodInsnNode extendedReach = null;
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (((MethodInsnNode) abstractInsnNode).name.equals(PlayerControllerMP.extendedReach.getName()) &&
                                ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(PlayerControllerMP.PlayerControllerMPClass))
                                && ((MethodInsnNode) abstractInsnNode).desc.equals("()Z")){
                            extendedReach = (MethodInsnNode) abstractInsnNode;
                        }
                    }
                }
                if (extendedReach != null) {
                    AbstractInsnNode jumpInsnNode = extendedReach.getNext();
                    while (jumpInsnNode.getOpcode() != Opcodes.IFLE){
                        jumpInsnNode = jumpInsnNode.getNext();
                    }
                    AbstractInsnNode ICONST = jumpInsnNode.getNext();
                    while (ICONST.getOpcode() != Opcodes.ICONST_1){
                        ICONST = ICONST.getNext();
                    }
                    insnList.remove(ICONST);
                    MethodInsnNode reach = new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"canReach","()Z");
                    insnList.insert(jumpInsnNode,reach);
                    method.instructions = insnList;
                }
                FieldInsnNode objectMouseOver = null;
                for (AbstractInsnNode insnNode : insnList) {
                    if (insnNode instanceof FieldInsnNode){
                        if (insnNode.getOpcode() == PUTFIELD && ((FieldInsnNode) insnNode).name.equals(Minecraft.objectMouseOver.getName())
                                && ((FieldInsnNode) insnNode).owner.equals(Type.getInternalName(Minecraft.MinecraftClass))){
                            objectMouseOver = (FieldInsnNode) insnNode;
                            break;
                        }
                    }
                }
                if (objectMouseOver != null){
                    //    ALOAD 0
                    //    GETFIELD net/minecraft/client/renderer/EntityRenderer.field_78531_r : Lnet/minecraft/client/Minecraft;
                    //    ALOAD 2
                    //    DLOAD 3
                    //    FLOAD 1
                    //    INVOKEVIRTUAL net/minecraft/entity/Entity.func_174822_a (DF)Lnet/minecraft/util/MovingObjectPosition;
                    //    PUTFIELD net/minecraft/client/Minecraft.field_71476_x : Lnet/minecraft/util/MovingObjectPosition;
                    InsnList hookRayTrace = new InsnList();
                    hookRayTrace.add(new VarInsnNode(ALOAD,0));
                    hookRayTrace.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),"L"+Type.getInternalName(Minecraft.MinecraftClass)+";"));
                    hookRayTrace.add(new VarInsnNode(ALOAD,2));
                    hookRayTrace.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(EventBus.class),"getBlockReach","()F"));
                    hookRayTrace.add(new InsnNode(F2D));
                    hookRayTrace.add(new VarInsnNode(FLOAD,1));
                    hookRayTrace.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Entity.EntityClass),Entity.rayTrace.getName(),"(DF)L"+Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass)+";"));
                    hookRayTrace.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.objectMouseOver.getName(),"L"+Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass)+";"));
                    method.instructions.insert(objectMouseOver,hookRayTrace);
                    AbstractInsnNode dload = objectMouseOver.getNext();
                    while (!(dload instanceof VarInsnNode)){
                        dload = dload.getNext();
                    }
                    dload = objectMouseOver.getNext();
                    while (!(dload instanceof VarInsnNode)){
                        dload = dload.getNext();
                    }
                }
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
