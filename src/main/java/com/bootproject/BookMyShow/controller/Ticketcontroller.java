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
import com.bootproject.BookMyShow.entity.Ticket;
import com.bootproject.BookMyShow.services.TicketService;


@RestController
@RequestMapping("ticket")
public class Ticketcontroller {

	@Autowired
	TicketService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestBody Ticket ticket){
		return service.saveTicket(ticket);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(@RequestParam int tId){
		return service.findTicket(tId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(@RequestParam int tId){
		return service.deleteTicket(tId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(@RequestBody Ticket ticket, @RequestParam int tId){
		return service.updateTicket(ticket, tId);
	}
	
	@GetMapping("ticketlist")
	public ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket(){
		return service.findAllTicket();
	}
}
