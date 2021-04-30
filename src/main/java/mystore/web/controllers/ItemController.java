package mystore.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.slf4j.*;

import mystore.domainmodel.models.*;
import mystore.service.accessoperation.ItemAccessOperation;
import mystore.service.repository.*;

@EnableCaching
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
	
	@PutMapping("/admin/item/updateItem/{id}")
	public void update(@RequestBody Item item)
	{
		logger.trace("inupdate");
		repository.save(item);
	}
	@GetMapping("/item/findAllItem/{id}")
	public Optional<Item> getItem(@PathVariable String id)
	{
		return repository.findById(id);
	}
	@CrossOrigin(origins = "https://localhost:3000")
	@DeleteMapping("/admin/item/deleteitem/{id}")
	public String deleteBook(@PathVariable
			String id)
	{
		repository.deleteById(id);
		return "item deleted";
	}
	@CrossOrigin(origins = "https://localhost:3000")
	@GetMapping("/item/searchitem/{type}")
	public List<Item> searchItem(@PathVariable String type,HttpSession session)
	{
		logger.trace("in sarch");
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
	
	void pin(int x)
	{
		System.out.print(x);
	}
	@GetMapping("/push")
	public String Push() throws ParseException
	{
		/*public String brand,type,gender;
	public int price,currentdiscount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	public Date discountduration,arrivaldate;
	boolean newarrival;
		 * */
		
		List<String>brand = new ArrayList<>();
		brand.add("brand1");brand.add("brand2");brand.add("brand3");
		List<String>type = new ArrayList<>();
		type.add("Jeans");type.add("Shirt");type.add("T-shirt");type.add("Blazer");type.add("Hoodie");
		List<String>gender = new ArrayList<>();
		gender.add("M");gender.add("F");
		List<Integer>price = new ArrayList<>();
		price.add(1000);price.add(2000);price.add(3000);
		List<Integer>discount = new ArrayList<>();
		discount.add(0);discount.add(10);discount.add(20);discount.add(30);
		List<Boolean>newarrival = new ArrayList<>();
		newarrival.add(true);newarrival.add(false);
		List<Date>discountduration = new ArrayList<>();
		discountduration.add(new SimpleDateFormat("yyyy-mm-dd").parse("2021-07-17"));
		discountduration.add(new SimpleDateFormat("yyyy-mm-dd").parse("2021-10-17"));
		
		pin(brand.size());
		pin(type.size());
		pin(gender.size());
		pin(price.size());
		pin(discount.size());
		pin(discountduration.size());
		pin(newarrival.size());
		
		for(int i=0;i<brand.size();i++)
		{
			
			for(int j=0;j<type.size();j++)
			{
				for(int k=0;k<gender.size();k++)
				{
					
					for(int l=0;l<price.size();l++)
					{
						for(int m=0;m<discount.size();m++)
						{
							for(int n=0;n<newarrival.size();n++)
							{
								for(int o=0;o<discountduration.size();o++)
								{
									for(int p=0;p<discountduration.size();p++)
									{
										
										Item item = new Item();
										item.setArrivaldate(discountduration.get(p));

										item.setBrand(brand.get(i));
										item.setType(type.get(j));
										item.setGender(gender.get(k));
										item.setPrice(price.get(l));
										item.setCurrentdiscount(discount.get(m));
										item.setNew_arrival(newarrival.get(n));
										item.setDiscount_duration(discountduration.get(o));
										repository.save(item);
										logger.trace("in push");
									}
								}
							}
						}
					}
				}
			}
		}
		return "added";
		
	}
}
