package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Seats;
import com.bootproject.BookMyShow.repo.SeatsRepo;

@Repository
public class SeatsDao {
	
	@Autowired
	SeatsRepo repo;

	public Seats saveSeats(Seats seats) {
		return repo.save(seats);
	}
	
	public Seats findSeats(int seatId)
	{
		Optional<Seats> opseats = repo.findById(seatId);
		if(opseats.isPresent())
		{
			return opseats.get();
		}
		return null;
	}
	
	public Seats deleteSeats(int seatId)
	{
		Seats seats =findSeats(seatId);
		repo.delete(seats);
		
		return seats;
	}
	
	public Seats updateSeats(Seats seats , int  seatId)
	{
		Seats exseats= findSeats(seatId);
		
		if(exseats!=null)
		{
			seats.setSeatId(seatId);
			return repo.save(seats);
		}
		return null;
	}
	
	public List<Seats> findAllSeats()
	{
		List<Seats> seats = repo.findAll();
		return seats;
	}
}
