package com.lx.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author leon
 */
public class AioClient {
    private AsynchronousSocketChannel client;

    public AioClient() throws IOException {

        client = AsynchronousSocketChannel.open();
    }

    public void conenct(String host, int port) {
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Void>() {
            /**
             * Invoked when an operation has completed.
             *
             * @param result     The result of the I/O operation.
             * @param attachment
             */
            @Override
            public void completed(Void result, Void attachment) {
                try {
                    client.write(ByteBuffer.wrap("this is a test msg".getBytes())).get();
                    System.out.println("send to server!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Invoked when an operation fails.
             *
             * @param exc        The exception to indicate why the I/O operation failed
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println(exc.toString());
            }
        });
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        client.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
            /**
             * Invoked when an operation has completed.
             *
             * @param result     The result of the I/O operation.
             * @param attachment
             */
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("I/O操作完成：" + result);
                System.out.println("获取反馈信息：" + new String(byteBuffer.toString()));
            }

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
        });

        try {
            // wait a minutes
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new AioClient().conenct("localhost", 8088);
    }
}
