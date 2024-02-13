package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Booking;
import com.bootproject.BookMyShow.repo.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	BookingRepo repo;
	
	public Booking saveBooking(Booking booking) {
		return repo.save(booking);
	}
	
	public Booking findBooking(int bId)
	{
		Optional<Booking> opBooking = repo.findById(bId);
		if(opBooking.isPresent())
		{
			return opBooking.get();
		}
		return null;
	}
	
	public Booking deleteBooking(int bId)
	{
		Booking booking =findBooking(bId);
		repo.delete(booking);
		
		return booking;
	}
	
	public Booking updateBooking(Booking booking , int  bId)
	{
		Booking exbooking= findBooking(bId);
		
		if(exbooking!=null)
		{
			booking.setBId(bId);
			return repo.save(booking);
		}
		return null;
	}
	
	public List<Booking> findAllBooking()
	{
		List<Booking> booking = repo.findAll();
		return booking;
	}
}
