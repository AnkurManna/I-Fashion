package com.example.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.User;

public interface UserRepository extends MongoRepository<User,String> {
	
	public Optional<User> findByMail(String mail);

}
