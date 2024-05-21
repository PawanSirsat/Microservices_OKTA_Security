package com.micro.hotel.HotelService.entities;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	private String id;
	
	private String name;
	
	private String location;
	
	private String about;
	
}
