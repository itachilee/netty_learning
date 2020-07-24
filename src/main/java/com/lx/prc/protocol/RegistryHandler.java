package com.lx.prc.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryHandler extends ChannelInboundHandlerAdapter {
    /**
     * 保存所有可以的服务
     */
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>(10);


    private List<String> classNames = new ArrayList<>(10);

    public RegistryHandler() {
        scannerClass("com.lx.prc.provider");
        doRegister();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
            result = method.invoke(clazz, request.getValues());
        }
        ctx.writeAndFlush(result);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.fireExceptionCaught(cause);
        ctx.close();
    }

    /**
     * 扫描
     *
     * @param packageName
     */
    public void scannerClass(String packageName) {
        String newPackageName =packageName.replace(".","/");
        URL url = this.getClass().getClassLoader().getResource(newPackageName);
        File dir = new File(url.getFile());

        for (File file : dir.listFiles()) {
            // -r
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    private void doRegister() {
        if (classNames.size() == 0) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
