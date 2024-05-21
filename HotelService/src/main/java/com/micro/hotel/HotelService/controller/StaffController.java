package com.micro.hotel.HotelService.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@GetMapping
	public ResponseEntity<List<String>> getStaff()
	{
		List<String> list = Arrays.asList("pawan","Tushar","Atul");
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
