package com.micro.rating.RatingService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.rating.RatingService.entities.Rating;
import com.micro.rating.RatingService.services.RatingService;

@RestController
@RequestMapping("/rating")
@PreAuthorize("isAuthenticated()")    
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Rating>> ratingList()
	{
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
	}
	
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getByuserId(@PathVariable String userId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
	}
	
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/userJson/{userId}")
	public ResponseEntity<List<Rating>> getByuserIdJson(@PathVariable String userId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserIdJson(userId));
	}
	
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getByhotelId(@PathVariable String hotelId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
	}
}
