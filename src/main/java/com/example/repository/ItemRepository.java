package com.example.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.Item;

public interface ItemRepository extends MongoRepository<Item,String> {

	//List<Item> findAll(Query query, Class<Item> class1);
	
	public List<Item> findByType(String type);
	public Optional<Item> findById(String id);
	public void deleteById(String id);
}

