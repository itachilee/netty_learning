package com.lx.prc.provider;

import com.lx.prc.api.IRpcHelloService;

/**
 * @author leon
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "Hello "+name+"!";
    }
}
