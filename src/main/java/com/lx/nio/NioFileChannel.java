package com.lx.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author leon
 */
public class NioFileChannel {
    public static void main(String[] args) throws IOException {
        String str ="hello world";
        // 创建一个channel
        FileOutputStream fileOutputStream=new FileOutputStream("D:\\file01.txt");
        // 通过fileOutStream 获取FileChanel
        // 这个fileChannel真实类型时 FileChannelImpl
        FileChannel fileChannel =fileOutputStream.getChannel();
        // 创建字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str 放入byteBuffer
        byteBuffer.put(str.getBytes());
        // buffer反转
        byteBuffer.flip();

        // 将byteBuffer 数据写入到 fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
