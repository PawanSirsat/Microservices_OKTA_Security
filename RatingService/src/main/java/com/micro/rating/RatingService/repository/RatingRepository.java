package com.micro.rating.RatingService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.micro.rating.RatingService.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> 
{
	//Customer Finding Methods
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
