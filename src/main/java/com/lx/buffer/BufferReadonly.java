package com.lx.buffer;

import java.nio.ByteBuffer;

/**
 * @author leon
 */
public class BufferReadonly {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i <byteBuffer.capacity() ; i++) {
            byteBuffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        for (int i = 0; i <byteBuffer.capacity() ; i++) {
            byte b=byteBuffer.get(i);
            b*=10;
            byteBuffer.put(i,b);
        }

        readOnlyBuffer.position(0);
        readOnlyBuffer.limit(byteBuffer.capacity());

        while(readOnlyBuffer.remaining()>0){
            System.out.println(readOnlyBuffer.get());
        }

    }
}
