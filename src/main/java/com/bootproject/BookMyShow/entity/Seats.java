package com.bootproject.BookMyShow.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Seats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;
	@Positive(message = "Should be greater than 0")
	private int sNoOfSeats;

	@ManyToOne(cascade = CascadeType.ALL)
	private Shows show;
	
}
