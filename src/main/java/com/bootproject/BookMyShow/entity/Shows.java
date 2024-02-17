package com.bootproject.BookMyShow.entity;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Shows {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int showId;
	private LocalDate sStartTime;
	private LocalDate sEndTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Movie sMovie;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seats> seats;

}
