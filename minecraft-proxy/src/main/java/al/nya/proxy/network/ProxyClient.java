package al.nya.proxy.network;

import al.nya.proxy.Logger;
import al.nya.proxy.network.packets.Packet;
import al.nya.proxy.network.packets.PacketAccess;
import al.nya.proxy.network.packets.PacketBuilder;
import al.nya.proxy.network.packets.handshake.C00Handshaking;
import al.nya.proxy.utils.ChannelAttribute;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;

//ToDo Split state
public class ProxyClient {
    @Getter
    private String remoteIP;
    @Getter
    private int remotePort;
    @Getter
    private Channel target;
    @Getter
    private Channel client;
    @Getter
    public int protocolVersion = 0;
    @Getter
    @Setter
    private EnumConnectionState state = EnumConnectionState.Handshaking;

    public ProxyClient(String remoteIP, int remotePort, Channel target) {
        this.remotePort = remotePort;
        this.remoteIP = remoteIP;
        this.target = target;
    }

    public void start() throws Exception {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ProxyClient client = this;
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("PacketInitializer", new PacketInitializer());
                        ch.pipeline().addLast(new ProxyClientHandler(client));

                        ch.pipeline().addLast("PacketPacker", new PacketToBufferEncoder());
                        ch.pipeline().addBefore("PacketPacker", "PacketDeinitializer", new PacketDeinitializer());
                    }
                });
        ChannelFuture channelFuture = null;
        channelFuture = bootstrap.connect(remoteIP, remotePort).sync();
        this.client = channelFuture.channel();
        channelFuture.channel().closeFuture().addListener(future -> {
            if (future.isSuccess()) {
                eventExecutors.shutdownGracefully();
                new Thread(this::closed).start();
            }
        });
        channelFuture.channel().attr(ChannelAttribute.CLIENT_BINDING).set(client);
    }

    public void serverSidePacketReceived(PacketBuffer msg) {
        msg.markReaderIndex();
        Packet packet = null;

        try {
            int protocolVersion;
            if (state == EnumConnectionState.Login) {
                protocolVersion = this.protocolVersion;
            } else if (state == EnumConnectionState.Play) {
                protocolVersion = EnumConnectionState.Play.i;
            } else {
                protocolVersion = 0;
            }
            packet = PacketBuilder.constructAndProcessPacket(msg, protocolVersion, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packet != null) {
            if (packetProcessed(packet))
                return;
        } else {
            //ToDo: Custom processor
        }

        msg.resetReaderIndex();
        target.writeAndFlush(msg);
    }

    public void clientSidePacketReceived(PacketBuffer msg) {

        if (protocolVersion == 0) {
            try {
                protocolVersion = PacketBuilder.processProtocolVersion(msg);
            } catch (RuntimeException e) {
                e.printStackTrace();
                client.disconnect();
                return;
            }
            Logger.info("<!>[ProxyClient" + client.localAddress().toString() + "] Protocol version: " + protocolVersion);
        }
        msg.markReaderIndex();
        Packet packet = null;
        if (state == EnumConnectionState.Login) {
            try {
                packet = PacketBuilder.constructAndProcessPacket(msg, protocolVersion, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.state == EnumConnectionState.Play) {
            try {
                packet = PacketBuilder.constructAndProcessPacket(msg, EnumConnectionState.Play.i, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (msg.readVarIntFromBuffer() == 0x00 && msg.readableBytes() == 0) {
                //Status Request
                if (processRequestStatus()) {
                    return;
                }
            } else {
                msg.resetReaderIndex();
                try {
                    packet = PacketBuilder.constructAndProcessPacket(msg, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (packet != null) {
            if (packetProcessed(packet))
                return;
        } else {
            //ToDo: Custom processor
        }

        msg.resetReaderIndex();
        client.writeAndFlush(msg);
    }

    public void closed() {
        Logger.info("<->[ProxyClient" + client.localAddress().toString() + "] Closed");
        protocolVersion = 0;
        state = EnumConnectionState.Handshaking;
    }

    public boolean processRequestStatus() {
        //ToDo: Custom status
        return false;
    }

    public boolean packetProcessed(Packet packet) {
        Logger.debug("<->[ProxyClient" + client.localAddress().toString() + "] Packet: " + packet.getClass().getSimpleName());
        return PacketAccess.packetList.stream()
                .filter(packetClass -> packetClass.isAssignableFrom(packet.getClass()))
                .findFirst()
                .map(packetClass -> {
                    Packet sendPacket = packet.processPacket(this);
                    switch (packet.getDirection(this)) {
                        case CLIENTBOUND:
                            target.writeAndFlush(sendPacket);
                            break;
                        case SERVERBOUND:
                            client.writeAndFlush(sendPacket);
                            break;
                        default:
                            throw new RuntimeException("Unknown packet direction");
                    }
                    return true;
                })
                .orElse(false);
    }
}
