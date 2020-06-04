package com.ssm;

import redis.clients.jedis.Jedis;

public class TestRedis {
    public static void main(String[] args)
    {
        Jedis jedis = new Jedis("192.168.186.130",6379);
        jedis.set("test","100");
        System.out.println(jedis.ping());
    }
}
