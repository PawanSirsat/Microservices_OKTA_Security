package com.micro.hotel.HotelService.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.micro.hotel.HotelService.entities.Hotel;

public interface HotelRepo extends JpaRepositoryImplementation<Hotel, String>{

}
