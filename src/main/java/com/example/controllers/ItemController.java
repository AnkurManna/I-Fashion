package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Item;
import com.example.repository.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ItemController {

	@Autowired
	ItemRepository repository;
	
	@PostMapping("/additem")
	public String saveItem(@RequestBody Item item)
	{
		repository.save(item);
		/*System.out.print(book.getId());
		System.out.print(book.getAuthorName());*/
		return "item added";
	}
	
	@GetMapping("/findallItems")
	public String getItems(){
		List<Item> all = repository.findAll();
		ObjectMapper obj = new ObjectMapper();
		String str = null;
		try {
			str = obj.writeValueAsString(all);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	@GetMapping("/findAllItem/{id}")
	public Optional<Item> getItem(@PathVariable String id)
	{
		return repository.findById(id);
	}
	
	@DeleteMapping("/itemdelete/{id}")
	public String deleteBook(@PathVariable
			String id)
	{
		repository.deleteById(id);
		return "item deleted";
	}
	@GetMapping("/searchitem/{type}")
	public List<Item> searchItem(@PathVariable String type)
	{
		List<Item> res = repository.findByType(type);
		return res;
	}
}