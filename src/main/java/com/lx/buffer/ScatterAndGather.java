package com.lx.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author leon
 */
public class ScatterAndGather {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8088);
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(64);
        byteBuffers[1] = ByteBuffer.allocate(64);
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += 1;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map(buffer -> "position:" + buffer.position()
                        + ",limit:" + buffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                socketChannel.write(byteBuffers);
                byteWrite += 1;
            }

            // 将所有buffer 复位
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
        }
    }

}
