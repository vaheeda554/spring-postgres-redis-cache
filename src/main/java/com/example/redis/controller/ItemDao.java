package com.example.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.redis.entity.Item;


public class ItemDao {
	@Autowired
	private RedisTemplate template;
	public static final String HASH_KEY="Item";
	
	public Item save(Item Item) {
		template.opsForHash().put(HASH_KEY,Item.getId(),Item);
		return Item;
		
	}
	public List<Item> findall(){
		return template.opsForHash().values(HASH_KEY);
		
	}
	public Item findItemById(Long id) {
		System.out.println("called findById from DB");
		return (Item)template.opsForHash().get(HASH_KEY,id);
		
	}
public String deleteItem(int id) {
	 template.opsForHash().delete(HASH_KEY,id);;
		return "Item removed";
		
	}

}
