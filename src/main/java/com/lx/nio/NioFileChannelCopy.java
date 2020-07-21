package com.lx.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author leon
 */
public class NioFileChannelCopy {
    public static void main(String[] args) throws IOException {
//创建相关流
        FileInputStream fileInputStream = new FileInputStream("world.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("new_world.jpg");
// 获取对应流的fileChannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        // 使用transferFrom 进行拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());
        fileInputStream.close();
        fileOutputStream.close();


    }

}
