package com.luxmart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxmart.model.Hotel;
import com.luxmart.repository.HotelRepository;

//https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
//https://www.youtube.com/watch?v=Hu-cyytqfp8
@RestController
@RequestMapping("/hotels")
public class HotelController {

	private HotelRepository hotelRepository;

	// connect to the repository
	public HotelController(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	// get a list of all the hotels
	@GetMapping()
	public List<Hotel> getAll() {

		List<Hotel> hotels = this.hotelRepository.findAll();
		return hotels;

	}

	// get a record by id
	@GetMapping("/{id}")
	public Optional<Hotel> getById(@PathVariable("id") String id) {

		Optional<Hotel> hotel = this.hotelRepository.findById(id);
		return hotel;
	}

	// add a record to the database
	@PutMapping()
	public void insert(@RequestBody Hotel hotel) {
		this.hotelRepository.insert(hotel);

	}

	// update a record on the database
	@PostMapping()
	public void update(@RequestBody Hotel hotel) {
		this.hotelRepository.save(hotel);
	}

	// delete a record from the database by id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.hotelRepository.deleteById(id);
	}

	// find a hotel by price
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice) {
		List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);
		return hotels;
	}

	// http://localhost:8080/hotels/address/Alvaro%20Obregon
	// find a hotel by delegacion
	@GetMapping("/address/delegacion/{delegacion}")
	public List<Hotel> getByDelegacion(@PathVariable("delegacion") String delegacion) {
		List<Hotel> hotels = this.hotelRepository.findByDelegacion(delegacion);
		return hotels;
	}

	// http://localhost:8080/hotels/address/Alvaro%20Obregon
	// find a hotel by Colonia
	@GetMapping("/address/colonia/{colonia}")
	public List<Hotel> getByColonia(@PathVariable("colonia") String colonia) {
		List<Hotel> hotels = this.hotelRepository.findByColonia(colonia);
		return hotels;
	}

	// find a hotel by Colonia
	@GetMapping("/address/{postalCode}")
	public List<Hotel> getByPostalCode(@PathVariable("postalCode") String postalCode) {
		List<Hotel> hotels = this.hotelRepository.findByPostalCode(postalCode);
		return hotels;
	}

	/// find by rating and by price per night
	@GetMapping("/recommended")
	public List<Hotel> getRecommended() {
		int maxPrice = 100;
		final int minRating= 7;
		
		List<Hotel> hotels = this.hotelRepository.findRecommended(maxPrice, minRating);
		return hotels;
	}

}
