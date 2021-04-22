package mystore.domainmodel.algorithms;

import java.util.Comparator;
import java.util.HashMap;

import mystore.domainmodel.models.*;
public class OrderByPreference implements Comparator<Item>{
	
	HashMap<String,Integer> mp;
	public OrderByPreference(HashMap<String,Integer> mp)
	{
		this.mp = mp;
		
	}
	public int compare(Item a,Item b)
	{
		return this.mp.get(b.getType()) - this.mp.get(a.getType());
	}
}
