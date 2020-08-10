package com.lx.redis;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Future;


/**
 * @author leon
 */
public class SimpleInBound {
    private int port;

    public SimpleInBound(int port) {
        this.port = port;
    }


    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new  SimpleChannelInboundHandler<ByteBuf>() {
                        ChannelFuture future;

                        /**
                         * <strong>Please keep in mind that this method will be renamed to
                         * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
                         * <p>
                         * Is called for each message of type {@link }.
                         *
                         * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
                         *            belongs to
                         * @param msg the message to handle
                         * @throws Exception is thrown if an error occurred
                         */
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                            System.out.println(ctx);
                            System.out.println(msg.toString(CharsetUtil.UTF_8));
                        }

                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = b.bind(port).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    {
                        if (future.isSuccess()) {
                            System.out.println("success");
                        } else {
                            System.out.println("error");
                            future.cause().printStackTrace();
                        }
                    }
                }
            });
            System.out.println("Gp rpc registry start listen at " + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new SimpleInBound(8088).start();
    }
}
