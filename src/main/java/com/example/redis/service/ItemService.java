package com.example.redis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.redis.entity.Item;
import com.example.redis.redisconfig.RedisAvailabilityService;
import com.example.redis.repository.ItemRepository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;


@Service
@CacheConfig(cacheNames = "items")
public class ItemService {
//    @Autowired
//    private ItemRepository itemRepository;

//    public static final String HASH_KEY="Items";
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private RedisAvailabilityService redisAvailabilityService;
//    @Autowired
////    private HashOperations<String, Long, Item> hashOperations;
//
//    @Cacheable("Items")
//    public List<Item> getAllItems() {
//        try {
//            return redisTemplate.opsForHash().values(HASH_KEY);
//            
//            
//        } catch (Exception e) {
//            return itemRepository.findAll(); // Fallback to database
//        }
//    }
//
////    @Cacheable("Items")
//    public Item getItemById(Long id) {
//    	try {
//    	if (redisAvailabilityService.isRedisAvailable()) {
//        	System.out.println("in get item try");
//            return (Item)redisTemplate.opsForHash().get(HASH_KEY,id);
//        }
//    	
//    	} catch (RedisConnectionFailureException e) {
//    		System.out.println("in get item exception");
//    		
//        }
//    	return itemRepository.findById(id).orElse(null);
//    }
//
////    @CacheEvict(value = "Items", allEntries = true)
//    public Item saveItem(Item item) {
//        Item savedItem = itemRepository.save(item);
//        try {
//        	redisTemplate.opsForHash().put(HASH_KEY,item.getId(),item);
//        }catch(Exception e) {
//        	
//        }
//        
//        
//        return savedItem;
//    }
//

//    @CacheEvict(value = "Items", allEntries = true)
//    public void deleteItem(Long id) {
//    	itemRepository.deleteById(id);
//    	redisTemplate.opsForHash().delete(HASH_KEY,id);
//    }

	@Autowired
	private ItemRepository itemRepository;
	
	private final CacheManager cacheManager;
	
	public ItemService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
	public boolean redisCacheAvailable() {
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

	@Cacheable("items")
	public Item getItemById(Long id) {

		return itemRepository.findById(id).orElse(null);
	}
	
	
//	@Cacheable(value = "items", key = "#id", condition = "#redisAvailable")
//    public Item getItemById(Long id) {
//        // Check if the item is in the cache
//        Item cachedItem = getCachedItem(id);
//        if (cachedItem != null) {
//            return cachedItem;
//        }
//
//        // If not in cache, retrieve from the database
//        Item dbItem = itemRepository.findById(id).orElse(null);
//        if (dbItem != null) {
//            updateCache(id, dbItem);
//        }
//       
//        return dbItem;
//    }
//	private Item getCachedItem(Long id) {
//        Cache cache = cacheManager.getCache("items");
//        if (cache != null) {
//            Cache.ValueWrapper valueWrapper = cache.get(id);
//            if (valueWrapper != null) {
//                return (Item) valueWrapper.get();
//            }
//        }
//        return null;
//    }
//	
//	private void updateCache(Long id, Item item) {
//        Cache cache = cacheManager.getCache("items");
//        if (cache != null) {
//            cache.put(id, item);
//        }
//    }
	
	
	

//   @CachePut(value = "items", key = "#item.id")
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@CacheEvict(value = "items", key = "#id")
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}

	public Item getItemByIdfallback(Long id) {
		if (RedisAvailabilityService.isRedisAvailable()) {
			return getItemById(id);

		} else {
			return itemRepository.findById(id).orElse(null);
		}
	}

	public Item saveItemfallback(Item item) {
		return itemRepository.save(item);
	}

	public void deleteItemfallback(Long id) {
		itemRepository.deleteById(id);
	}

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	
	
}
