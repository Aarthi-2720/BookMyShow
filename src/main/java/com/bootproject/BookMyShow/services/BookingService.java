package com.bootproject.BookMyShow.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.BookingNotFound;
import com.bootproject.BookMyShow.Exception.TicketNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.BookingDao;
import com.bootproject.BookMyShow.dao.TicketDao;
import com.bootproject.BookMyShow.entity.Booking;
import com.bootproject.BookMyShow.entity.Ticket;

@Service
public class BookingService {

	@Autowired
	BookingDao dao;
	
	TicketDao tdao = new TicketDao(); 
	
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking) {
			
		ResponseStructure<Booking> structure = new ResponseStructure<Booking>();
		structure.setMessage("Booking Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveBooking(booking));
		
		return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Booking>> findBooking(int bId){
		
		Booking booking = dao.findBooking(bId);
		
		if(booking!=null) {
			ResponseStructure<Booking> structure = new ResponseStructure<Booking>();			
			structure.setMessage("Booking found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(booking);
			
			return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.FOUND);
		}
		throw new BookingNotFound("Booking not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking, int bId){
		
		Booking book = dao.findBooking(bId);
		
		if(book != null) {

			Booking upbooking = dao.updateBooking(booking, bId);
			ResponseStructure<Booking> structure = new ResponseStructure<Booking>();		
			
			structure.setMessage("Booking updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(upbooking);
			
				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.OK);
			}
			throw new BookingNotFound("Booking not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(int bId){
		
		Booking booking = dao.findBooking(bId);
		
		if(booking != null) {
			Booking delbooking = dao.deleteBooking(bId);
				
			ResponseStructure<Booking> structure = new ResponseStructure<Booking>();

			structure.setMessage("Booking deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(delbooking);
				
			return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.OK);
		}
		throw new BookingNotFound("Booking not found with given id");
	}

	public ResponseEntity<ResponseStructure<List<Booking >>> findAllBooking(){
		
		List<Booking> bookingList = dao.findAllBooking();

		if(! bookingList.isEmpty()) {
			ResponseStructure<List<Booking>> structure = new ResponseStructure<List<Booking>>();
			structure.setMessage("All Bookings are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(bookingList);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(structure, HttpStatus.FOUND);
		}
		throw new BookingNotFound("Bookings are not found");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> assignTicketToBooking(int bId){
		
		Booking booking = dao.findBooking(bId);
		if(booking != null) {
			List<Ticket> ticket = tdao.findAllTicket();
			if(!ticket.isEmpty()) {
				booking.setBTicket(ticket);
				Booking upbooking = dao.updateBooking(booking, bId);
				ResponseStructure<Booking> structure = new ResponseStructure<Booking>();
				structure.setMessage("Ticket assigned to booking");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(upbooking);
				
				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.OK);
			}
			throw new TicketNotFound("Ticket List is empty");
		}
		throw new BookingNotFound("Booking not found");
	}
	
}
