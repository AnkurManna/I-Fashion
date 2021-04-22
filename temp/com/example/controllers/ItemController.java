package com.example.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.comparator.OrderByPreference;
import com.example.model.Item;
import com.example.repository.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ItemController {

	@Autowired
	ItemRepository repository;
	
	@PostMapping("/item/additem")
	public String saveItem(@RequestBody Item item)
	{
		repository.save(item);
		/*System.out.print(book.getId());
		System.out.print(book.getAuthorName());*/
		return "item added";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/item/findallItems")
	public ResponseEntity<String> getItems(HttpServletRequest req,HttpSession session){
		List<Item> all = repository.findAll();
		ObjectMapper obj = new ObjectMapper();
		
		HashMap<String,Integer> preference = null;
		preference = (HashMap<String,Integer>) session.getAttribute("preference");
		//OrderByPreference pref = new OrderByPreference(preference);
		if (preference == null) {
            preference = new HashMap<String,Integer>();
        } 
		for(int i=0;i<all.size();i++)
		{
			if(!preference.containsKey(all.get(i).getType()))
			{
				preference.put(all.get(i).getType(), 0);
			}
			
		}
		
		Collections.sort(all, new OrderByPreference(preference));
		String str = null;
		try {
			str = obj.writeValueAsString(all);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(str);
	}
	
	@GetMapping("/item/findAllItem/{id}")
	public Optional<Item> getItem(@PathVariable String id)
	{
		return repository.findById(id);
	}
	
	@DeleteMapping("/item/itemdelete/{id}")
	public String deleteBook(@PathVariable
			String id)
	{
		repository.deleteById(id);
		return "item deleted";
	}
	
	@GetMapping("/item/searchitem/{type}")
	public List<Item> searchItem(@PathVariable String type,HttpSession session)
	{
		List<Item> res = repository.findByType(type);
		@SuppressWarnings("unchecked")
		HashMap<String,Integer> preference = (HashMap<String,Integer>) session.getAttribute("preference");

        if (preference == null) {
            preference = new HashMap<String,Integer>();
        } 
        if(preference.containsKey(type))
        {
        	preference.put(type, preference.get(type)+1);
        }
        else
        {
        	preference.put(type, 1);
        }
        System.out.println("HashMap: "
                + preference);

        session.setAttribute("preference", preference);
		return res;
	}
}
