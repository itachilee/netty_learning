package com.lx.nio;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author leon
 */
public class NioFileChannelBasic {
    public static void main(String[] args) throws IOException {
//        writeToFile();
//        readFromFile();
    readAndWrite();
    }

    /**
     * 写入文件
     *
     * @throws IOException
     */
    public static void writeToFile() throws IOException {


        String str = "hello world";
        // 创建一个channel
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        // 通过fileOutStream 获取FileChanel
        // 这个fileChannel真实类型时 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
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

    public static void readFromFile() throws IOException {
        File file = new File("test.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        // 通过fileOutStream 获取FileChanel
        FileChannel fileChannel = fileInputStream.getChannel();
        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将通道内的数据读入buffer
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    /**
     * 使用buffer进行读写操作
     *
     * @throws FileNotFoundException
     */
    public static void readAndWrite() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileChannel channel = fileInputStream.getChannel();
        //
        FileOutputStream fileOutputStream = new FileOutputStream("text2.txt");
        FileChannel channel1 = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        // 循环读取
        while (true) {
            // clear 只重置position,实际上没有擦除数据
//            public final Buffer clear() {
//                position = 0;
//                limit = capacity;
//                mark = -1;
//                return this;
//            }
            byteBuffer.clear();
            // 读取buffer
            int read = channel.read(byteBuffer);
            System.out.println("read: "+ read);
            // 读完buffer
            if (read == -1) {
                break;
            }
            // 需要翻转一次buffer 操作position
            byteBuffer.flip();
            channel1.write(byteBuffer);
        }
        // 关闭流
        fileInputStream.close();
        fileOutputStream.close();
    }

}
