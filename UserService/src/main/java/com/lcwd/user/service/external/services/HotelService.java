package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entities.Hotel;

@FeignClient(name = "HOTELS-SERVICE")
public interface HotelService 
{
	@GetMapping("/hotels/{hotelId}")
     Hotel getHotel(@PathVariable String hotelId);
}
