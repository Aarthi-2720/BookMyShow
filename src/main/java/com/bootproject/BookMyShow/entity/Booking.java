package com.bootproject.BookMyShow.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bId;
	@Positive(message="Should be greater than 0")
	private int bNoOfTicket;
	@Positive(message="Should be greater than 0")
	private double bprice;
	private LocalDate bdate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> bTicket;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User bUser;
}
