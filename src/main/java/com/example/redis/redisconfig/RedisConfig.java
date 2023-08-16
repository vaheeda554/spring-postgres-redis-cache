package com.example.redis.redisconfig;
import java.time.Duration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration

public class RedisConfig {
	
	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // Use the custom serializer for values
        template.setValueSerializer(new CustomJsonRedisSerializer<>());
        template.setHashValueSerializer(new CustomJsonRedisSerializer<>());

        return template;
    }
    
//    @Bean
//    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .disableCachingNullValues()
//                .entryTtl(Duration.ofHours(1));
//
//       return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
//                        .cacheDefaults(redisCacheConfiguration).build();
//
//    }
	
	
//	@Bean
//	public JedisConnectionFactory ConnectionFactory() {
//		RedisStandaloneConfiguration configuration= new RedisStandaloneConfiguration();
//		configuration.setHostName("localhost");
//		configuration.setPort(6379);
//		return new JedisConnectionFactory(configuration);
//	}
//	
//	@Bean
//	@Primary
//	public RedisTemplate<String,Object> redisTemplate(){
//		RedisTemplate<String,Object> template=new RedisTemplate<>();
//		template.setConnectionFactory(ConnectionFactory());
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setHashValueSerializer(new StringRedisSerializer());
//		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
//		template.setValueSerializer(new JdkSerializationRedisSerializer());
//		template.setEnableTransactionSupport(true);
//		template.afterPropertiesSet();
//		return template;
//		
//	}
//	
//	@Bean
//	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
//	                                      ResourceLoader resourceLoader) {
//	    RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
//	            .builder(redisConnectionFactory)
//	            .cacheDefaults(determineConfiguration(resourceLoader.getClassLoader()));
//	    List<String> cacheNames = this.cacheProperties.getCacheNames();
//	    if (!cacheNames.isEmpty()) {
//	        builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
//	    }
//	    return builder.build();
//	}
//
//	private org.springframework.data.redis.cache.RedisCacheConfiguration determineConfiguration(
//	        ClassLoader classLoader) {
//	    if (this.redisCacheConfiguration != null) {
//	        return this.redisCacheConfiguration;
//	    }
//	    CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
//	    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//
//	    ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
//	            .modulesToInstall( new SimpleModule().addSerializer( new NullValueSerializer(null)) )
//	            .failOnEmptyBeans( false )
//	            .build();
//	    mapper.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//
//	    GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer( mapper );
//
//	    //get the mapper b/c they registered some internal modules
//	    config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));;
//
//	    if (redisProperties.getTimeToLive() != null) {
//	        config = config.entryTtl(redisProperties.getTimeToLive());
//	    }
//	    if (redisProperties.getKeyPrefix() != null) {
//	        config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
//	        config = config.computePrefixWith(cacheName -> redisProperties.getKeyPrefix() + cacheName + "::");
//	    }
//	    if (!redisProperties.isCacheNullValues()) {
//	        config = config.disableCachingNullValues();
//	    }
//	    if (!redisProperties.isUseKeyPrefix()) {
//	        config = config.disableKeyPrefix();
//	    }
//	    return config;
//	}
}

