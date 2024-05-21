package com.micro.hotel.HotelService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.hotel.HotelService.entities.Hotel;
import com.micro.hotel.HotelService.exception.ResourceNotFoundException;
import com.micro.hotel.HotelService.repositories.HotelRepo;
import com.micro.hotel.HotelService.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepo hotelRepo;
	
	@Override
	public Hotel create(Hotel hotel) {
		String idString = UUID.randomUUID().toString();
		hotel.setId(idString);
		return hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepo.findAll();
	}

	@Override
	public Hotel get(String id) {
		return hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found ") );
	}

	@Override
	public Hotel update(String id, Hotel updatedHotel) {
		System.out.println(updatedHotel);
		System.out.println(id);
	    return hotelRepo.findById(id).map(hotel -> {
	        hotel.setName(updatedHotel.getName());
	        hotel.setLocation(updatedHotel.getLocation());
	        hotel.setAbout(updatedHotel.getAbout());
	        return hotelRepo.save(hotel);
	    }).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+id));
	}
	
	@Override
	public Hotel delete(String id) {
	    Hotel hotelToDelete = hotelRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+id));
	    hotelRepo.delete(hotelToDelete);
	    return hotelToDelete;
	}
	
//	@Override
//	public String delete(String id) {
//	    try {
//	        Hotel hotelToDelete = hotelRepo.findById(id)
//	                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
//	        hotelRepo.delete(hotelToDelete);
//	        return "Delete hotel " + hotelToDelete.getName() + " successful.";
//	    } catch (ResourceNotFoundException ex) {
//	       	 return ex.getMessage();
//	    }
//	}

}
