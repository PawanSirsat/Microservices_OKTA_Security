package com.micro.hotel.HotelService.services;

import java.util.List;

import com.micro.hotel.HotelService.entities.Hotel;

public interface HotelService {

	//Create Hotel
	Hotel create (Hotel hotel);
	
	//Get All Hotels
	List<Hotel> getAll();
	
	//Get Hotel By ID
	Hotel get(String id);
	
	//Get Hotel Update
	Hotel update(String id, Hotel updatedHotel);
	
	//Get Hotel Delete
	Hotel delete(String id);
}
