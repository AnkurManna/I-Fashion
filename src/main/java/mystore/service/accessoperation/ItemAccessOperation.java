package mystore.service.accessoperation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import mystore.domainmodel.algorithms.OrderByPreference;
import mystore.domainmodel.models.*;

import mystore.service.repository.*;

@EnableMongoRepositories(basePackages = "mystore.service.repository")
@Service
public class ItemAccessOperation {
	
	
	
	@Autowired
	private ItemRepository repository;
	
	public ItemAccessOperation()
	{
		
	}
	
	public  void save(Item item,ItemRepository rep)
	{
		//System.out.println(item.getBrand());
		
		rep.save(item);
	}
	
	public  String getAll(HashMap<String,Integer> preference,ItemRepository repo)
	{
		List<Item> all = repo.findAll();
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
