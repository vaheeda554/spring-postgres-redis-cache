package com.example.redis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis.entity.Item;
import com.example.redis.repository.ItemRepository;

@Service
@CacheConfig(cacheNames = "items")
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Cacheable("items")
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @CachePut(value = "items", key = "#item.id")
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @CacheEvict(value = "items", key = "#id")
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

	 public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}

