package al.nya.proxy.utils;

import io.netty.util.AttributeKey;

public class ChannelAttribute {
    public static final AttributeKey COMPRESSION = AttributeKey.newInstance("COMPRESSION");
    public static final AttributeKey ENCRYPTION = AttributeKey.newInstance("ENCRYPTION");
    public static final AttributeKey CLIENT_BINDING = AttributeKey.newInstance("CLIENT_BINDING");
}
