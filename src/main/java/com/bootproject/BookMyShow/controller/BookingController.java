package com.bootproject.BookMyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.entity.Booking;
import com.bootproject.BookMyShow.services.BookingService;

@RestController
@RequestMapping("booking")
public class BookingController {
	
	@Autowired
	BookingService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking){
		return service.saveBooking(booking);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Booking>> findBooking(@RequestParam int bId){
		return service.findBooking(bId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(@RequestParam int bId){
		return service.deleteBooking(bId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@RequestBody Booking booking, @RequestParam int bId){
		return service.updateBooking(booking, bId);
	}

	@GetMapping("bookinglist")
	public ResponseEntity<ResponseStructure<List<Booking>>> findAllBooking(){
		return service.findAllBooking();
	}
	
	@PutMapping("assignTicket")
	public ResponseEntity<ResponseStructure<Booking>> assingTicketToBooking(@RequestParam int bId){
		return service.assignTicketToBooking(bId);
	}
}
