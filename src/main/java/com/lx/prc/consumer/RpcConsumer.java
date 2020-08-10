package com.lx.prc.consumer;

import com.lx.prc.api.IRpcHelloService;
import com.lx.prc.api.IRpcService;
import com.lx.prc.consumer.proxy.RpcProxy;

/**
 * @author leon
 */
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService rpcHelloService = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHelloService.hello("leon"));

        IRpcService rpcService = RpcProxy.create(IRpcService.class);

        System.out.println("8 - 8 =" + rpcService.sub(8, 8));
        System.out.println("8 + 8 =" + rpcService.add(8, 8));
        System.out.println("8 * 8 =" + rpcService.mult(8, 8));
        System.out.println("8 / 8 =" + rpcService.div(8, 8));
    }
}
