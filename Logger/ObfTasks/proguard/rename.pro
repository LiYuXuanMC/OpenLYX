-injars ..\..\.\build\libs\Logger.jar
-outjars ..\..\Logger-rename.jar

-libraryjars ..\..\libs
-libraryjars ..\libraries
-libraryjars ..\libraries\maven

-dontshrink
-dontoptimize
-printmapping mapping.txt
-obfuscationdictionary '..\..\dictionary.txt'
-classobfuscationdictionary '..\..\dictionary.txt'
-repackageclasses al.logger
-keepattributes *Annotation*,Signature
-renamesourcefileattribute SourceFile
-verbose



-keepclassmembers,allowshrinking class al.logger.client.wrapper.environment.Environment {
    <fields>;
}

# Keep - Applications. Keep all application classes, along with their 'main' methods.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Also keep - Database drivers. Keep all implementations of java.sql.Driver.
-keep class * extends java.sql.Driver

# Also keep - Swing UI L&F. Keep all extensions of javax.swing.plaf.ComponentUI,
# along with the special 'createUI' method.
-keep class * extends javax.swing.plaf.ComponentUI {
    public static javax.swing.plaf.ComponentUI createUI(javax.swing.JComponent);
}

# Keep - _class method names. Keep all .class method names. This may be
# useful for libraries that will be obfuscated again with different obfuscators.
-keepclassmembers,allowshrinking class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String,boolean);
}

-keepclassmembers,allowshrinking class * {
    @proguard.annotation.KeepName
    <fields>;
    @proguard.annotation.KeepName
    <methods>;
}

-keepclassmembers,allowshrinking @proguard.annotation.KeepClassMemberNames class * {
    <fields>;
    <methods>;
}

-keepclassmembers,allowshrinking @proguard.annotation.KeepPublicClassMemberNames class * {
    public <fields>;
    public <methods>;
}

-keepclassmembers,allowshrinking @proguard.annotation.KeepPublicProtectedClassMemberNames class * {
    public protected <fields>;
    public protected <methods>;
}

-keep,allowshrinking @proguard.annotation.KeepName class *
