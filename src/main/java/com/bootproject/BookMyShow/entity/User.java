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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uId;
	@NotBlank(message = "User name cannot be blank")
	@NotNull(message = "User name cannot be null")
	private String uName;
	@Email(message = "Add proper email")
	private String uEmail;
	@NotBlank(message = "Password cannot be blank")
	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[a-Z])(?=.[@#%$^&+=!]).{8,}$",
	message = "Password must be atleast 8 characters long, contain atleast one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
	private String uPassword;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Booking> uBooking;

}
