package com.lx.redis;

import redis.clients.jedis.Jedis;

public class RedisJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("connected");
        System.out.println(jedis.ping());
        jedis.sadd("song", "believe me");
        jedis.sadd("song", "it's my life");
        jedis.smove("song","mysong","it's my life");
        System.out.println(jedis.smembers("mysong"));
        System.out.println(jedis.sinter("mysong","song"));
    }
}
