package mystore.service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mystore.domainmodel.models.*;

public interface UserRepository extends MongoRepository<User,String> {
	
	public Optional<User> findByMail(String mail);

}
