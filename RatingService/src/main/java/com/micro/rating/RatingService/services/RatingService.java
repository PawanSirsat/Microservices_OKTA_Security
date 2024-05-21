package com.micro.rating.RatingService.services;

import java.util.List;

import com.micro.rating.RatingService.entities.Rating;

public interface RatingService {

	Rating create (Rating rating);
	
    List<Rating> getRatings();
    
    List<Rating> getRatingByUserId(String userId);
    
    List<Rating> getRatingByUserIdJson(String userId);
 
    List<Rating> getRatingByHotelId(String hotelId);
	
	
}
