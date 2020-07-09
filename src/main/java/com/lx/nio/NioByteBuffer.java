package com.lx.nio;

import java.nio.ByteBuffer;

/**
 * @author leon
 */
public class NioByteBuffer {
    public static void main(String[] args) {
//        bufferSerial();
        readOnlyBuffer();
    }

    public static void bufferSerial() {
        ByteBuffer allocate = ByteBuffer.allocate(64);
        // 类型化放入数据

        allocate.putInt(10);
        allocate.putLong(9L);
        allocate.putChar('8');
        allocate.putShort((short) 7);
        // 反转 取出数据
        allocate.flip();
        // 取出数据时需要按照相对应的顺序进行取出
        // 类型c的struct
        // 如不按照顺序取出可能会类型不对应造成类型转换异常：
        // 小类型转大类型会抛出异常
    }

    public static void readOnlyBuffer() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }
        // read
        byteBuffer.flip();

        // get a readOnly buffer

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        // read
        while (readOnlyBuffer.hasRemaining()) {
            // buffer's position automatically plus 1 by get()
            System.out.println(readOnlyBuffer.get());
        }
//java.nio.ReadOnlyBufferException
        // can't write a readonly buffer
//        readOnlyBuffer.put((byte)12);
    }
}
