package com.micro.rating.RatingService.services.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.micro.rating.RatingService.entities.Rating;
import com.micro.rating.RatingService.entities.User;
import com.micro.rating.RatingService.repository.RatingRepository;
import com.micro.rating.RatingService.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Rating create(Rating rating) {
		String uidString = UUID.randomUUID().toString();
		rating.setRatingId(uidString);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) 
	{
		return  ratingRepository.findByUserId(userId);
	}
	
	@Override
	public List<Rating> getRatingByUserIdJson(String userId) 
	{	
        User user = restTemplate.getForObject("http://USER-SERVICE/users/" + userId, User.class);
        
		 List<Rating> list = ratingRepository.findByUserId(userId);
	        for (Rating rating : list) {
	        	 com.micro.rating.RatingService.entities.Hotel  hotel
	   	      = restTemplate.getForObject("http://HOTELS-SERVICE/hotels/" + rating.getHotelId(), com.micro.rating.RatingService.entities.Hotel.class);
	            rating.setUser(user);
	            rating.setHotel(hotel);
	        }      	        
		return  list;
	}
	

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
	      com.micro.rating.RatingService.entities.Hotel  hotel
	      = restTemplate.getForObject("http://HOTELS-SERVICE/hotels/" + hotelId, com.micro.rating.RatingService.entities.Hotel.class);

		 List<Rating> list = ratingRepository.findByHotelId(hotelId);
		 
	        for (Rating rating : list) {
	            User user = restTemplate.getForObject("http://USER-SERVICE/users/" + rating.getUserId(), User.class);
	            rating.setUser(user);
	            rating.setHotel(hotel);
	        }
    
		return list;
	}

}
