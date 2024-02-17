package com.bootproject.BookMyShow.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mId;
	@NotNull(message = "Movie Title cannot be null")
	@NotBlank(message = "Movie Title cannot be blank")
	private String mTitle;
	@NotBlank(message = "Movie genre cannot be blank")
	@NotNull(message = "Movie genre cannot be null")
	private String mGenre;
	@Positive(message = "Should be greater than 0")
	private double mRating;
	private LocalDate mDuration;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Shows mShow;

}
