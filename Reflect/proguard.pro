-injars build\libs\Reflect-Inject-1.0-SNAPSHOT-all.jar
-outjars build\libs\Reflect-Inject-1.0-SNAPSHOT-all-obf.jar

-libraryjars 'C:\Program Files\Zulu\zulu-8\jre\lib\rt.jar'
-libraryjars 'C:\Program Files\Zulu\zulu-8\jre\lib\charsets.jar'
-libraryjars 'C:\Program Files\Zulu\zulu-8\jre\lib\jce.jar'
-libraryjars 'C:\Program Files\Zulu\zulu-8\jre\lib\jfr.jar'
-libraryjars 'C:\Program Files\Zulu\zulu-8\jre\lib\jsse.jar'
-libraryjars 'C:\Program Files\Zulu\zulu-8\jre\lib\resources.jar'
-libraryjars libs\gson-2.8.0.jar
-libraryjars libs\guava-17.0.jar
-libraryjars libs\lwjgl-2.9.4-nightly-20150209.jar
-libraryjars libs\lwjgl-opengl-3.2.2.jar
-libraryjars libs\lwjgl_util-2.9.4-nightly-20150209.jar
-libraryjars libs\netty-all-4.1.25.Final.jar

-dontshrink
-dontoptimize
-printmapping obf.map
-repackageclasses al.nya.ReflectAntiLeak
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod



-keep,allowshrinking class al.nya.reflect.Reflect {
    *** Instance;
    *** eventBus;
}

-keep,allowshrinking class al.nya.reflect.transform.TransformManager {
    *** onTransform(...);
}

-keep,allowshrinking class al.nya.reflect.events.EventBus {
    <methods>;
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.utils.data.ModuleConfig {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.utils.data.UserConfig {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.utils.data.ValueConfig {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.utils.data.UserData {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.CommandDownload {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.CommandIRCChat {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.CommandSendFinish {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.CommandShellCode {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.CommandUpload {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.CommandUpdateUser {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.ControlCommand {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.User {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.socket.KeyCode {
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl {
    <methods>;
    <fields>;
}

-keepcode,allowshrinking class al.nya.reflect.events.events.EventText {
    <methods>;
}

-keepcode,allowshrinking class al.nya.reflect.events.events.Event {
    <methods>;
}

-keepcode,allowshrinking class al.nya.reflect.events.events.EventMove {
    <methods>;
}

-keep,allowshrinking class by.radioegor146.nativeobfuscator.Native

-keepcode,allowshrinking class al.nya.reflect.socket.CommandHeartBeat {
    <fields>;
}

# Keep - Native method names. Keep all native class/method names.
-keepclasseswithmembers,includedescriptorclasses,allowshrinking class * {
    native <methods>;
}
