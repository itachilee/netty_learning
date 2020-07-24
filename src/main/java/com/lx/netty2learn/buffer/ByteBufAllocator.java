package com.lx.netty2learn.buffer;

public class ByteBufAllocator {

    public static void main(String[] args) {

        int maxValue =Integer.MAX_VALUE;
        int minValue =Integer.MIN_VALUE;
        System.out.println(maxValue);
        int overFlow =maxValue+1;
        assert overFlow == minValue: "addtion overflowed";
        System.out.println(overFlow);

        assert  minValue -1 ==maxValue ;

        System.out.println(minValue-1);
    }
}
