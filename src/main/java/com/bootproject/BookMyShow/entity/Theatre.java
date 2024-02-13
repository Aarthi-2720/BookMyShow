package com.bootproject.BookMyShow.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tId;
	private String tName;
	private String tLocation;
	private int tCapacity;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Show tShows;
	@OneToMany(cascade = CascadeType.ALL)
	private Seats tSeats;

}
