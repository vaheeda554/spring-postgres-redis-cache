package com.example.redis.redisconfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {

	  @Bean
	    public RedisCacheConfiguration cacheConfiguration() {
	        return RedisCacheConfiguration.defaultCacheConfig()
	                .serializeValuesWith(RedisSerializationContext.SerializationPair
	                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
	    }
	
//	@Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        boolean redisAvailable = isRedisAvailable(redisConnectionFactory);
//
//        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        if (redisAvailable) {
//        	System.out.println("Redis available");
//            return RedisCacheManager.builder(redisConnectionFactory)
//                    .cacheDefaults(cacheConfig)
//                    .build();
//        } else {
//            // Create an in-memory cache manager if Redis is not available
//        	System.out.println("Redis not available");
//            return new ConcurrentMapCacheManager();
//        }
//    }
//
//    private boolean isRedisAvailable(RedisConnectionFactory redisConnectionFactory) {
//    	Jedis jedis = null;
//
//		try {
//			jedis = new Jedis("localhost", 6379);
//			jedis.ping();
//			System.out.println("Redis is running");
//			return true;
//
//		} catch (JedisConnectionException e) {
//			System.out.println("Redis not working");
//			return false;
//
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
//	}
    
}

