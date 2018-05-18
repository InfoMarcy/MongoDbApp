package com.luxmart;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.luxmart.model.Address;
import com.luxmart.model.Hotel;
import com.luxmart.model.Review;
import com.luxmart.repository.HotelRepository;


@Component
public class AddDataToDb implements CommandLineRunner{
	
	// connect to the repository
	private HotelRepository hotelRepository;
	
	//constructor to load the repository
	public AddDataToDb(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Hotel marriot = new Hotel(
				"Mariot",
				     85,
				     new Address("91", "Moctezuma", "", "Guerrero", "Cuauhtemoc","Ciudad de mexico", "Mexico", "06300"),
				     Arrays.asList(
				    		              new Review("Jhon", 8, false),
				    		              new Review("Daniel", 7, true)
				    		       )

				    
				);
		
		
		Hotel hilton = new Hotel(
				"Hilton",
				     95,
				     new Address("102", "Luis Quintero", "", "Guerrero", "Cuauhtemoc","Ciudad de mexico", "Mexico", "07300"),
				     Arrays.asList(
				    		              new Review("Juana", 5, false)
				    		           
				    		       )

				    
				);
		
		
		Hotel puntacanaHotel = new Hotel(
				"El Hotel Puntacana",
				     116,
				     new Address("123", "Insurgentes sur", "", "Centro", "Alvaro Obregon","Ciudad de mexico", "Mexico", "9100"),
				     new ArrayList<>()
				    
				);

		// drop all hotels
		this.hotelRepository.deleteAll();
		
		// ad ours hotel to the database
		//create a list to hold all the values
		List<Hotel> hotels = Arrays.asList(marriot, hilton, puntacanaHotel); 
		// save and pass the list of hotels to the repository
		this.hotelRepository.saveAll(hotels);
	}
	
}
