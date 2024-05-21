package com.micro.rating.RatingService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Rating {

	@Id
	String ratingId;
	
	String userId;
	
	String hotelId;
	
	int rating;
	
	String feedback;
	
	@Transient
	User user;
	
	@Transient
	Hotel hotel;
}
