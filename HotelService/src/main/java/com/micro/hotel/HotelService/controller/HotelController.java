package com.micro.hotel.HotelService.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.hotel.HotelService.entities.Hotel;
import com.micro.hotel.HotelService.services.impl.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
@PreAuthorize("isAuthenticated()")    
public class HotelController {

	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	// Create a new hotel.
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelServiceImpl.create(hotel));
	}	
	
	// Get a hotel by ID.
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> gethotel(@PathVariable String hotelId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(hotelServiceImpl.get(hotelId));
	}
	
	// Get all hotels.
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity <List<Hotel>> gethotels()
	{
		return ResponseEntity.status(HttpStatus.OK).body(hotelServiceImpl.getAll());
	}
	
	// Update Hotel
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel updatedHotel)
    {
	    return ResponseEntity.status(HttpStatus.CREATED).body(hotelServiceImpl.update(hotelId, updatedHotel));
	 }
    
    // delete Hotel
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<Hotel> deleteHotel(@PathVariable String hotelId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(hotelServiceImpl.delete(hotelId));
	}
	
}
