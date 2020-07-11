package com.lx.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author leon
 */
public class BufferSlice {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i <byteBuffer.capacity() ; i++) {
            byteBuffer.put((byte)i);
        }

        // 创建子缓冲区

        byteBuffer.position(3);
        byteBuffer.limit(7);

        ByteBuffer slice =byteBuffer.slice();

        // 改变子缓冲区的内容

        for (int i = 0; i <slice.capacity() ; i++) {
            byte b = slice.get(i);
            b*=10;
            slice.put(i,b);
        }
//        public final Buffer clear() {
//            position = 0;
//            limit = capacity;
//            mark = -1;
//            return this;
//        }
        byteBuffer.clear();
        while (byteBuffer.remaining()>0){
            System.out.println(byteBuffer.get());
        }
    }
}
