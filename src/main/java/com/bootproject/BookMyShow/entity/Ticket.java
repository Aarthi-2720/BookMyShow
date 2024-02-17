package com.bootproject.BookMyShow.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tId;
	@Positive(message="Should be greater than 0")
	private double tPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Shows show;
}
