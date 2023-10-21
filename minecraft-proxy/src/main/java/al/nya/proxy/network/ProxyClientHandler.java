package al.nya.proxy.network;

import al.nya.proxy.Logger;
import al.nya.proxy.utils.ChannelAttribute;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class ProxyClientHandler extends ChannelInboundHandlerAdapter {
    private final ProxyClient client;
    public ProxyClientHandler(ProxyClient client) {
        this.client = client;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof PacketBuffer) {
            PacketBuffer buffer = (PacketBuffer) msg;
            Logger.debug("ProxyClient Packet received: " + buffer.array().length +" "+ Arrays.toString(buffer.array()));
            client.serverSidePacketReceived(buffer);
        }
    }
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Logger.info("<!>[ProxyClient"+ctx.channel().localAddress().toString()+"] Connection established");
    }
}
