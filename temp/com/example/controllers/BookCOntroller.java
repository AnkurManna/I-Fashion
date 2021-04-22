package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Book;
import com.example.repository.BookRepository;


@RestController
public class BookCOntroller {

	@Autowired
	BookRepository repository;
	
	@PostMapping("/addbook")
	public String saveBook(@RequestBody Book book)
	{
		repository.save(book);
		System.out.print(book.getId());
		System.out.print(book.getAuthorName());
		return "book added";
	}
	
	@GetMapping("/findallBooks")
	public List<Book> getBooks(){
		
		return repository.findAll();
	}
	
	@GetMapping("/findAllbooks/{id}")
	public Optional<Book> getBook(@PathVariable int id)
	{
		return repository.findById(id);
	}
	
	@DeleteMapping("/delete/{id")
	public String deleteBook(@PathVariable int id)
	{
		repository.deleteById(id);
		return "book deleted";
	}
}
