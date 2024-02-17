package com.bootproject.BookMyShow.entity;

import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aId;
	@NotNull(message ="Admin name cannot be null")
	@NotBlank(message ="Admin name cannot be null")
	private String aName;
	@Positive(message ="Value must be greater than 0")
	@Min(value  = 6000000000l)
	@Max(value = 9999999999l)
	private long aContact;
	@Email(message = "Add proper email")
	private String aEmail;
	@NotNull(message = "Password cannot be null")
	@NotBlank(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#%$^&+=!]).{8,}$",
	message = "Password must be atleast 8 characters long, contain atleast one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
	private String aPassword;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> aUser;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> aTheatre;
	
	
}
