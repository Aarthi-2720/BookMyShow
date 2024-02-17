package com.bootproject.BookMyShow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	@NotBlank(message = "Theatre name cannot be blank")
	@NotNull(message = "Theatre name cannot be null")
	private String tName;
	@NotBlank(message = "Theatre Location cannot be blank")
	@NotNull(message = "Theatre Location cannot be null")
	private String tLocation;
	@Positive(message = "Should be greater than 0")
	private int tCapacity;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Shows> tShows;

}
