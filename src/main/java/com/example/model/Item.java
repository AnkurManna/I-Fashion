package com.example.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString

@Document(collection="Items")
public class Item  {
	@Id
	public String id;
	
	public String brand,type,gender;
	public int price,current_discount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	public Date discount_duration,arrivaldate;
	boolean new_arrival;
	public Item(String brand, String type, String gender, int price, int current_discount, Date discount_duration,
			Date arrivaldate, boolean new_arrival) {
		super();
		this.brand = brand;
		this.type = type;
		this.gender = gender;
		this.price = price;
		this.current_discount = current_discount;
		this.discount_duration = discount_duration;
		this.arrivaldate = arrivaldate;
		this.new_arrival = new_arrival;
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

	public int getCurrent_discount() {
		return current_discount;
	}

	public void setCurrent_discount(int current_discount) {
		this.current_discount = current_discount;
	}

	public Date getDiscount_duration() {
		return discount_duration;
	}

	public void setDiscount_duration(Date discount_duration) {
		this.discount_duration = discount_duration;
	}

	public Date getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public boolean isNew_arrival() {
		return new_arrival;
	}

	public void setNew_arrival(boolean new_arrival) {
		this.new_arrival = new_arrival;
	}

	public Item() {}
	
	
	
	
}
