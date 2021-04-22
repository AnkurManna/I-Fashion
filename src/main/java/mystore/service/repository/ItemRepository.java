package mystore.service.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mystore.domainmodel.models.*;
@Repository
public interface ItemRepository extends MongoRepository<Item,String> {

	//List<Item> findAll(Query query, Class<Item> class1);
	
	public List<Item> findByType(String type);
	public Optional<Item> findById(String id);
	public void deleteById(String id);
}

