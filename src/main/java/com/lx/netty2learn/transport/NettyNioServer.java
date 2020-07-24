package com.lx.netty2learn.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * 下面是 Netty NIO 的代码，只是改变了一行代码，就从 OIO 传输 切换到了 NIO
 * Asynchronous networking with Netty
 * 1.创建一个 ServerBootstrap
 * <p>
 * 2.使用 NioEventLoopGroup 允许非阻塞模式（NIO）
 * <p>
 * 3.指定 ChannelInitializer 将给每个接受的连接调用
 * <p>
 * 4.添加的 ChannelInboundHandlerAdapter() 接收事件并进行处理
 * <p>
 * 5.写信息到客户端，并添加 ChannelFutureListener 当一旦消息写入就关闭连接
 * <p>
 * 6.绑定服务器来接受连接
 * <p>
 * 7.释放所有资源
 */
public class NettyNioServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();    //1
            b.group(new NioEventLoopGroup(), new NioEventLoopGroup())   //2
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {    //3
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {    //4
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate())                //5
                                            .addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync();                    //6
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();                    //7
        }
    }
}