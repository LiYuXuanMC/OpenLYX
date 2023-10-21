package al.nya.reflect.utils.client;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.modules.Ghost.Reach;
import al.nya.reflect.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NeteaseDetector {
    public static boolean isFilter() {
        try {
            Class.forName("com.netease.mc.mod.filter.FilterWrapper");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Class getFilter() {
        try {
            return Class.forName("com.netease.mc.mod.filter.FilterWrapper");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class getFilterHelper() {
        try {
            return Class.forName("com.netease.mc.mod.filter.FilterHelper");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeChatFilterWords() {
        Class helper = getFilterHelper();
        try {
            Field chatFilterRegularExpList = helper.getDeclaredField("chatFilterRegularExpList");
            chatFilterRegularExpList.setAccessible(true);
            try {
                List<String> chatFilterRegularExpListValue = (List) chatFilterRegularExpList.get(null);
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : chatFilterRegularExpListValue) {
                    stringBuilder.append(s).append("\n");
                }
                try {
                    FileUtil.writeFile(new File(Reflect.ReflectDir + "/chatFilterRegularExpList.txt"), stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
