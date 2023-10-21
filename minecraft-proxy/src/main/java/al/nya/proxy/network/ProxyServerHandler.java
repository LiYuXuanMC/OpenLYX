package al.nya.proxy.network;

import al.nya.proxy.Logger;
import al.nya.proxy.utils.ChannelAttribute;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;
import java.util.Map;

public class ProxyServerHandler extends ChannelInboundHandlerAdapter {
    private final ProxyServer server;

    public ProxyServerHandler(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Logger.info("<+> New connection from " + ctx.channel().remoteAddress().toString());
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof PacketBuffer) {
            PacketBuffer buffer = (PacketBuffer) msg;
            Logger.debug("Packet received: " + buffer.array().length +" "+ Arrays.toString(buffer.array()));
            ((ProxyClient)(ctx.channel().attr(ChannelAttribute.CLIENT_BINDING).get())).clientSidePacketReceived(buffer);
        }
    }
    public void channelActive(ChannelHandlerContext ctx) {
        Logger.info("<!>["+ctx.channel().remoteAddress().toString()+"] Connection established");
        ProxyClient client = null;
        try {
            client = server.allocateClient(ctx.channel());
        } catch (Exception e) {
            ctx.close();
            Logger.info("<X>["+ctx.channel().remoteAddress().toString()+"] Connection closed");
            e.printStackTrace();
            return;
        }
        ctx.channel().attr(ChannelAttribute.CLIENT_BINDING).set(client);
        Logger.info("<!>["+ctx.channel().remoteAddress().toString()+"] Connection initialized");
    }
    public void channelInactive(ChannelHandlerContext ctx) {
        Logger.info("<->["+ctx.channel().remoteAddress().toString()+"] Connection closed");
        ProxyClient client = (ProxyClient) ctx.channel().attr(ChannelAttribute.CLIENT_BINDING).get();
        if (client != null) {
            client.getClient().close();
        }
    }
}
