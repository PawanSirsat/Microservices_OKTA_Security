package com.lcwd.user.service.services.impl;
import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;

import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.external.services.RatingService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private RatingService ratingService;
    
    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //Save User
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    //Get All Users
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //Get User By ID
    @Override
    public User getUser(String userId) 
    {
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new ResourceNotFoundException("User with given id is not found on server !! : " + userId)); 
        return user;
    }
    
    //Get User By ID
    @Override
    public User getUserJson(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> 
        new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
      
        logger.info("User Found : {} ",userId);
        //-----------Rest Template-------
//        Rating[] list = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
      Rating[] list = ratingService.getRating(user.getUserId());

        List<Rating> ratingList = Arrays.asList(list); // Convert array to list
        for (Rating rating : ratingList) {
  
        //Hotel hotel = restTemplate.getForObject("http://HOTELS-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
        //-----------Feign Client--------  
        	Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }
        
        user.setRating(ratingList);
        
        logger.info("Received user ratings: {}", ratingList.toString()); // Logging the list directly
        
        return user;
    }
    

}
