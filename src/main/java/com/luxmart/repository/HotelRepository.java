package com.luxmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.luxmart.model.Hotel;
//https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
// QuerydslPredicateExecutor allows to use the new query methods that the query Dsl provides
// query DSl Generate classes for quering mongoDb
@Repository("hotelRepository")
public interface HotelRepository extends MongoRepository<Hotel, String> {//, QuerydslPredicateExecutor<Hotel> {

	// find a hotel by id
	Optional<Hotel> findById(String id);
	
	// filter a hotel by max price
	List<Hotel> findByPricePerNightLessThan(int maxPrice);
	
	// filter hotels by address => delegacion
	@Query(value = "{'address.delegacion':?0}" )
	List<Hotel> findByDelegacion(String delegacion);
	
	
	// filter by address => colonia
	@Query(value = "{'address.colonia':?0}" )	
	List<Hotel> findByColonia(String colonia);
	
	
	// filter by address => postalCode
	@Query(value = "{'address.postalCode':?0}" )	
	List<Hotel> findByPostalCode(String postalCode);
	
	// filter by address => postalCode
		@Query(value = "{'pricePerNight':?0 , 'reviews.rating':?1}" )	
	 	List<Hotel>findRecommended(int maxPrice, int minRating);
}
