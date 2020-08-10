package com.lx.redis;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;

import java.net.InetSocketAddress;

public class Datagram {
    public static void main(String[] args) {
        Bootstrap bootstrap =new Bootstrap();
        bootstrap.group(new OioEventLoopGroup()).channel(OioDatagramChannel.class).handler(new SimpleChannelInboundHandler<DatagramPacket>() {
     ChannelInboundHandler inboundHandler;

            /**
             * <strong>Please keep in mind that this method will be renamed to
             * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
             * <p>
             * Is called for each message of type {@link I}.
             *
             * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
             *            belongs to
             * @param msg the message to handle
             * @throws Exception is thrown if an error occurred
             */
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

            }
        });
        ChannelFuture future =bootstrap.bind(new InetSocketAddress(0));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("success");
                } else {
                    System.out.println("error");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}
