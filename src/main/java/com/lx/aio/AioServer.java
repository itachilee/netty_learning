package com.lx.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leon
 */
public class AioServer {
    private final int port;

    public AioServer(int port) {
        this.port = port;
        listen();
    }

    public static void main(String[] args) {
        int port = 8088;
        new AioServer(port);
    }

    private void listen() {
        try {
            // 实际中需要手动创建线程
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            System.out.println("port:" + port);
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {


                /**
                 * Invoked when an operation fails.
                 *
                 * @param exc        The exception to indicate why the I/O operation failed
                 * @param attachment
                 */
                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println(exc.toString());
                }

                // 创建缓存区
                final ByteBuffer buffer = ByteBuffer.allocate(1024);

                /**
                 * Invoked when an operation has completed.
                 *
                 * @param result     The result of the I/O operation.
                 * @param attachment
                 */

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("I/O 操作,开始读取...");
                    try {
                        // 重置position为0
                        buffer.clear();
                        // 读取
                        result.read(buffer);
                        // 翻转
                        buffer.flip();
                        // 写入
                        result.write(buffer);
                        buffer.flip();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (Exception e) {

                            System.out.println(e.toString());
                        }
                    }
                    System.out.println("操作完成");
                }

            });
            try {
                // 等待
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
