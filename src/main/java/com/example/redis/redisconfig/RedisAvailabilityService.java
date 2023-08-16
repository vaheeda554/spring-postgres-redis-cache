package com.example.redis.redisconfig;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Service
public class RedisAvailabilityService {

//    @Autowired
//    private RedisTemplate redisTemplate;

	public static boolean isRedisAvailable() {
		Jedis jedis = null;

		try {
			jedis = new Jedis("localhost", 6379);
			jedis.ping();
			System.out.println("Redis is running");
			return true;

		} catch (JedisConnectionException e) {
			System.out.println("Redis not working");
			return false;

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static void main(String args[]) {
		Jedis jedis = null;

		try {
			jedis = new Jedis("localhost", 6379);
			jedis.ping();
			System.out.println("Redis is running");

		} catch (JedisConnectionException e) {
			System.out.println("Redis not working");

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}
}
