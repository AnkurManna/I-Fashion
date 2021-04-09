package com.example.comparator;

import java.util.Comparator;
import java.util.HashMap;

import com.example.model.Item;

public class OrderByPreference implements Comparator<Item>{
	
	HashMap<String,Integer> mp;
	OrderByPreference(HashMap<String,Integer> mp)
	{
		this.mp = mp;
		
	}
	public int compare(Item a,Item b)
	{
		return this.mp.get(a.getType()) - this.mp.get(b.getType());
	}
}
