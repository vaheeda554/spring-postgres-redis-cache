package com.example.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redis.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
