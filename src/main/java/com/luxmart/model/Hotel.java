package com.luxmart.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// the hotel class is our aggregate route
@Document(collection ="Hotels") // tells mongoDb that this is a document and should be store in a collection
public class Hotel {
	@Id
	private String id;
	private String name;
	// index to speed the process of filtering in this case by pricePerNight
	@Indexed(direction = IndexDirection.ASCENDING)
	private int pricePerNight;
	private Address address;
	private List<Review> reviews;
	
	
	// protected constructor
	protected Hotel() {
		this.reviews = new ArrayList<>();
		
	}


     // constructor with fields
	public Hotel(String name, int pricePerNight, Address address, List<Review> reviews) {
		this.name = name;
		this.pricePerNight = pricePerNight;
		this.address = address;
		this.reviews = reviews;
	}


	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getPricePerNight() {
		return pricePerNight;
	}


	public Address getAddress() {
		return address;
	}


	public List<Review> getReviews() {
		return reviews;
	}



}
