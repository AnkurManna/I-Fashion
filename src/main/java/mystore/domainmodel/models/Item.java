package mystore.domainmodel.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString

@Document(collection="Items")
public class Item  {
	@Id
	public String id;
	
	public String brand,type,gender;
	public int price,currentdiscount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	public Date discountduration,arrivaldate;
	boolean newarrival;
	public Item(String brand, String type, String gender, int price, int current_discount, Date discount_duration,
			Date arrivaldate, boolean new_arrival) {
		super();
		this.brand = brand;
		this.type = type;
		this.gender = gender;
		this.price = price;
		this.currentdiscount = current_discount;
		this.discountduration = discount_duration;
		
		this.arrivaldate = arrivaldate;
		this.newarrival = new_arrival;
	}
	
	public int getCurrentdiscount() {
		return currentdiscount;
	}

	public void setCurrentdiscount(int currentdiscount) {
		this.currentdiscount = currentdiscount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public Date getDiscount_duration() {
		return discountduration;
	}

	public void setDiscount_duration(Date discount_duration) {
		this.discountduration = discount_duration;
	}

	public Date getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public boolean isNewarrival() {
		return newarrival;
	}

	public void setNew_arrival(boolean new_arrival) {
		this.newarrival = new_arrival;
	}

	public Item() {}
	
	
	
	
}
