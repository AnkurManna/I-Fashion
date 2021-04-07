package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Book;

public interface BookRepository extends MongoRepository<Book,Integer> {

}
