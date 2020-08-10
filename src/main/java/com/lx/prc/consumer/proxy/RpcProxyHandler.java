package com.lx.prc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leon
 */
@Slf4j
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {
    private Object reponse;

    public Object getReponse() {
        return reponse;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("mgs");
        reponse = msg;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("client exception is general ");
    }
}
