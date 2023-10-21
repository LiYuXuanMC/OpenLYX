package al.nya.proxy.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import lombok.Getter;

public class ProxyServer {
    @Getter
    private String remoteIP;
    @Getter
    private int remotePort;
    @Getter
    private int localPort;
    public ProxyServer(String remoteIP, int remotePort, int localPort){
        this.remotePort = remotePort;
        this.remoteIP = remoteIP;
        this.localPort = localPort;
    }
    public void start(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        ProxyServer server = this;
        bootstrap.group(acceptorGroup,clientGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("PacketInitializer", new PacketInitializer());
                        socketChannel.pipeline().addLast("Handler", new ProxyServerHandler(server));

                        socketChannel.pipeline().addLast("PacketPacker",new PacketToBufferEncoder());
                        socketChannel.pipeline().addBefore("PacketPacker","PacketDeinitializer", new PacketDeinitializer());
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.bind(localPort).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e){

        } finally {
            acceptorGroup.shutdownGracefully();
            clientGroup.shutdownGracefully();
        }
    }
    public ProxyClient allocateClient(Channel channel) throws Exception {
        ProxyClient proxyClient = new ProxyClient(remoteIP,remotePort,channel);
        proxyClient.start();
        return proxyClient;
    }
}
