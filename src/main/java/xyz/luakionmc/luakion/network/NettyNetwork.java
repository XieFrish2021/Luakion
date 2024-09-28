package xyz.luakionmc.luakion.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import xyz.luakionmc.luakion.protocol.EnumConnectionStatus;
import xyz.luakionmc.luakion.protocol.handshake.listener.HandshakeListenerImpl;

import java.net.InetSocketAddress;

public class NettyNetwork implements INettyNetwork {
    private final EventLoopGroup loopGroup = new NioEventLoopGroup(r -> {
        Thread thread = Thread.ofVirtual().unstarted(r);
        thread.setName("Netty IO #" + thread.threadId());
        return thread;
    });
    private final ServerBootstrap bootstrap;

    private final String host;
    private final int port;

    public NettyNetwork(String host, int port) {
        this.host = host;
        this.port = port;

        this.bootstrap = new ServerBootstrap()
                .group(loopGroup)
                .childOption(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new NettyInitializer());
    }

    @Override
    public void runServer() {
        synchronized (this) {
            ChannelFuture bind =
                    this.bootstrap.bind(new InetSocketAddress(host, port));

            bind.channel().closeFuture().syncUninterruptibly();
        }
    }

    @Override
    public void stopServer() {
        if (!this.loopGroup.isTerminated()) {
            this.loopGroup.shutdownGracefully();
        }
    }
}
