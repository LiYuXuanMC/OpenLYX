package pub.ensemble.hillo.core;

import net.minecraft.client.Minecraft;
import pub.ensemble.hillo.utils.EACSet;

import java.lang.reflect.ReflectPermission;
import java.security.Permission;

public class EACSMgr extends SecurityManager{

    public static final EACSet<String> eacSet = new EACSet<>();


    @Override
    public void checkPermission(Permission perm, Object context)
    {
        this.checkPermission(perm);
    }
    @Override
    public void checkPermission(Permission perm) {

        //System.out.println(perm.getName());
        //顶尖Attach检查
        if(perm.getName().contains("accessClassInPackage.sun.instrument") || perm.getName().contains("loadLibrary.instrument")){
            System.out.println("小小Attach被我看到了吧！");
            Runtime.getRuntime().halt(0);
            Minecraft.getMinecraft().shutdown();
            Minecraft.getMinecraft().shutdownMinecraftApplet();
        }
        //顶尖反射操作检测
        if (perm instanceof ReflectPermission) {
            ReflectPermission reflectPerm = (ReflectPermission) perm;
            if (reflectPerm.getName().startsWith("new") || reflectPerm.getName().startsWith("suppressAccessChecks")) {
                Class<?>[] context = getClassContext();


            }
        }
        //彻底废除废物注入方式(Base on Java ClassLoader)
        if ("getProtectionDomain".equals(perm.getName()))
        {
            //System.out.println("getProtectionDomain");
            for(Class clazz : this.getClassContext()){

                //sun.instrument.InstrumentationImpl
                //System.out.println("clazz.getName() = " + clazz.getName());
            }

        }
        //防止直接替换
        if ("setSecurityManager".equals(perm.getName()))
        {
            Runtime.getRuntime().halt(0);
            Minecraft.getMinecraft().shutdown();
            Minecraft.getMinecraft().shutdownMinecraftApplet();
            throw new SecurityException("咿呀！你为什么要把我换掉！呜呜呜！不行的哟！");
        }
        //防止垃圾注入
        if("createClassLoader".equals(perm.getName())){
            //System.out.println("咿呀！你咋进我家门了！让我看看你是不是坏叔叔！");
            for(Class clazz : this.getClassContext()){
                if(clazz.getName().contains("sun.instrument.InstrumentationImpl")){
                    System.out.println("找到了坏叔叔的气息！");
                    //Crash
                    Runtime.getRuntime().halt(0);
                    Minecraft.getMinecraft().shutdown();
                    Minecraft.getMinecraft().shutdownMinecraftApplet();
                }
                //sun.instrument.InstrumentationImpl
                //System.out.println("clazz.getName() = " + clazz.getName());
            }
        }
    }
    @Override
    public void checkLink(String lib) {
        //监控Lib加载
        //System.out.println("lib = " + lib);
    }

//    @Override
//    public void checkPackageAccess(String pkg) {
//        System.out.println("pkg = " + pkg);
//        super.checkPackageAccess(pkg);
//
//    }

    //监控Socket网络链接
    @Override
    public void checkConnect(String host, int port) {
        //System.out.println("host = " + host);
        for(Class clazz : this.getClassContext()){
            //System.out.println("clazz.getName() = " + clazz.getName());
        }

    }

    public void addSMgrData(String mark,String pkg){

    }

    public void checkListeners(){



    }
}
