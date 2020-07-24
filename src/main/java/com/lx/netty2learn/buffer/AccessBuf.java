package com.lx.netty2learn.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class AccessBuf {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.copiedBuffer("netty buf is aweson!", CharsetUtil.UTF_8);
        ByteBuf slice = buf.slice(0, 21);
        System.out.println(slice.toString(CharsetUtil.UTF_8));

        buf.setByte(1, (byte) 'm');
        assert buf.getByte(1) == slice.getByte(1);
        System.out.println(slice.toString(CharsetUtil.UTF_8));
    }
}
