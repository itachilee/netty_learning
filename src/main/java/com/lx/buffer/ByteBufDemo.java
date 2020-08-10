package com.lx.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBufDemo {

    public static void main(String[] args) {
        ByteBuf buf =Unpooled.copiedBuffer ("this is my demo", CharsetUtil.UTF_8);
//        for (int i = 0; i < buf.capacity(); i++) {
//
//            byte b =buf.getByte(i);
//            System.out.println((byte)b);
//        }

        while(buf.isReadable()){System.out.println(buf.readByte());}

        System.out.println(buf.isReadable());
        System.out.println(buf.isWritable());
    }
}
