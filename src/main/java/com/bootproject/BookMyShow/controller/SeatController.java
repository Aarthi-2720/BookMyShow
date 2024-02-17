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
import com.bootproject.BookMyShow.entity.Seats;
import com.bootproject.BookMyShow.services.SeatsService;


@RestController
@RequestMapping("seat")
public class SeatController {

	@Autowired
	SeatsService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Seats>> saveSeats(@RequestBody Seats seat){
		return service.saveSeats(seat);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Seats>> findSeats(@RequestParam int sId){
		return service.findSeats(sId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Seats>> deleteSeats(@RequestParam int sId){
		return service.deleteSeats(sId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Seats>> updateSeats(@RequestBody Seats seat, @RequestParam int sId){
		return service.updateSeats(seat, sId);
	}
	
	@GetMapping("seatlist")
	public ResponseEntity<ResponseStructure<List<Seats>>> findAllSeats(){
		return service.findAllSeats();
	}
}
