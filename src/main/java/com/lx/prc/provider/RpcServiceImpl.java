package com.lx.prc.provider;

import com.lx.prc.api.IRpcService;

/**
 * @author leon
 */
public class RpcServiceImpl implements IRpcService {
    /**
     * 加
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 减
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    /**
     * 乘
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    /**
     * 除
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
