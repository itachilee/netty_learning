package com.lx.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author leon
 */
public class NioFileChannel {
    public static void main(String[] args) throws IOException {
    // new NioFileChannel(). write();
    new NioFileChannel().readFromFile();
    }

    /**
     * 写入文件
     * @throws IOException
     */
    public void writeToFile() throws IOException {


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

    public void readFromFile() throws IOException {
        File file=new File("D:\\file01.txt");
        FileInputStream fileInputStream =new FileInputStream(file);
        // 通过fileOutStream 获取FileChanel
        FileChannel fileChannel=fileInputStream.getChannel();
        // 创建缓冲区
        ByteBuffer byteBuffer =ByteBuffer.allocate((int)file.length());
        // 将通道内的数据读入buffer
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

}
