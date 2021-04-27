package mystore.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.*;

import mystore.domainmodel.models.*;
import mystore.service.accessoperation.ItemAccessOperation;
import mystore.service.repository.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class ItemController {

	@Autowired
	ItemRepository repository;
	
	@Autowired
	ItemAccessOperation op ;

	
	Logger logger  = LoggerFactory.getLogger(ItemController.class);
	//ItemAccessOperation op = new ItemAccessOperation(repository);
	//ItemAccessOperation op = new ItemAccessOperation();
	//explicit new will stop spring to consider as a service
	@GetMapping("/")
	public String welcome()
	{
		logger.trace("In hello world");
		//different levels of logging like trace,info
		//log level for package is defined in application.properties 
		return "Hello World";
	}
	@PostMapping("/item/additem")
	public String saveItem(@RequestBody Item item)
	{
		op.save(item);
		//repository.save(item);
		/*System.out.print(book.getId());
		System.out.print(book.getAuthorName());*/
		return "item added";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/item/findallItems")
	public String getItems(HttpServletRequest req,HttpSession session){
		
		
		HashMap<String,Integer> preference = null;
		preference = (HashMap<String,Integer>) session.getAttribute("preference");
		
		if (preference == null) {
            preference = new HashMap<String,Integer>();
        } 
		return op.getAll(preference,repository,session);
		
	}
	
	@PutMapping("/item/updateItem/{id}")
	public void update(@RequestBody Item item)
	{
		repository.save(item);
	}
	
	@GetMapping("/item/findAllItem/{id}")
	public Optional<Item> getItem(@PathVariable String id)
	{
		return repository.findById(id);
	}
	
	@DeleteMapping("/item/deleteitem/{id}")
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
	
	@SuppressWarnings("unchecked")
	@GetMapping("/item/discounts")
	public String Getdiscount(HttpSession session) 
	{
		HashMap<String,Integer> preference = null;
		preference = (HashMap<String,Integer>) session.getAttribute("preference");
		
		if (preference == null) {
            preference = new HashMap<String,Integer>();
        } 
		
		return op.getCurrentDiscounts(preference);
	
	}
	
	@GetMapping("/item/newarrivals")
	public String NewArrivals(HttpSession session)
	{
		HashMap<String,Integer> preference = null;
		preference = (HashMap<String,Integer>) session.getAttribute("preference");
		
		if (preference == null) {
            preference = new HashMap<String,Integer>();
        } 
		
		return op.getNewArrivals(preference);
	}
}
