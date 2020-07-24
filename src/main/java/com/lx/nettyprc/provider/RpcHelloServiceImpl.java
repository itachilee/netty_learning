package com.lx.nettyprc.provider;

import com.lx.nettyprc.api.IRpcHelloService;

/**
 * @author leon
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "Hello "+name+"!";
    }
}
