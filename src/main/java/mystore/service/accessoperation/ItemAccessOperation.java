package mystore.service.accessoperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import mystore.domainmodel.algorithms.OrderByPreference;
import mystore.domainmodel.models.*;

import mystore.service.repository.*;
import mystore.web.controllers.ItemController;

@EnableMongoRepositories(basePackages = "mystore.service.repository")
@Service
public class ItemAccessOperation {
	
	
	
	@Autowired
	ItemRepository repository;
	
	Logger logger  = LoggerFactory.getLogger(ItemController.class);
	
	public ItemAccessOperation()
	{
		
	}
	
	public  void save(Item item)
	{
		//System.out.println(item.getBrand());
		
		repository.save(item);
	}
	List<Item> Util(HashMap<String,Integer> preference,List<Item>all)
	{
		logger.info("in util message");
		System.out.print("here in log");
		for(int i=0;i<all.size();i++)
		{
			if(!preference.containsKey(all.get(i).getType()))
			{
				preference.put(all.get(i).getType(), 0);
			}
			
		}
		
		
		
		Collections.sort(all, new OrderByPreference(preference));
		return all;
	}
	public  String getAll(HashMap<String,Integer> preference,ItemRepository repo,HttpSession session)
	{
		List<Item> men = repo.findByGender("M");
		List<Item> women = repo.findByGender("F");
		men = Util(preference,men);
		women = Util(preference,women);
		List<Item> all
        = new ArrayList<Item>();

    // using addAll( ) method to concatenate the lists
 

		if(session.getAttribute("gender").equals("M"))
		{
			all.addAll(men);
			all.addAll(women);
		}
		else
		{
			all.addAll(women);
			all.addAll(men);
		}
			
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
	
	public  String getNewArrivals(HashMap<String,Integer> preference)
	{
		List<Item> all = repository.findByNewarrival(true);
		for(int i=0;i<all.size();i++)
		{
			if(!preference.containsKey(all.get(i).getType()))
			{
				preference.put(all.get(i).getType(), 0);
			}
			
		}
		ObjectMapper obj = new ObjectMapper();
		Collections.sort(all, new OrderByPreference(preference));
		String str = null;
		try {
			str = obj.writeValueAsString(all);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	//repository.findByCurrentdiscountGreaterThan(0);
	
	public  String getCurrentDiscounts(HashMap<String,Integer> preference)
	{
		List<Item> all = repository.findByCurrentdiscountGreaterThan(0);;
		for(int i=0;i<all.size();i++)
		{
			if(!preference.containsKey(all.get(i).getType()))
			{
				preference.put(all.get(i).getType(), 0);
			}
			
		}
		ObjectMapper obj = new ObjectMapper();
		Collections.sort(all, new OrderByPreference(preference));
		String str = null;
		try {
			str = obj.writeValueAsString(all);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	
}
