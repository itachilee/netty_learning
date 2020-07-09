package com.lx.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author leon
 */
public class NioFileChannel {
    public static void main(String[] args) throws FileNotFoundException {
        String str ="hello world";
        // 创建一个channel
        FileOutputStream fileOutputStream=new FileOutputStream("D:\\file01.txt");
    }
}
