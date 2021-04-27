package mystore.service.repository;


import java.util.List;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mystore.domainmodel.models.*;
/* @Repository provides a database specific encapsulated interface to ease the interaction with persistence layer */
@Repository
public interface ItemRepository extends MongoRepository<Item,String> {

	//List<Item> findAll(Query query, Class<Item> class1);
	
	public List<Item> findByType(String type);
	public List<Item> findByCurrentdiscountGreaterThan(int discount);
	public List<Item> findByGender(String gender);
	public Optional<Item> findById(String id);
	public List<Item>findByNewarrival(boolean flag);
	public void deleteById(String id);
}

//https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
//imp link