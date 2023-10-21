package cc.systemv.rave.utils;

import cc.systemv.rave.Rave;
import net.minecraft.client.Minecraft;

public class InstanceAccess {
    protected final static Minecraft mc = Minecraft.getMinecraft();
    protected final static Rave rave = Rave.getInstance();
}
