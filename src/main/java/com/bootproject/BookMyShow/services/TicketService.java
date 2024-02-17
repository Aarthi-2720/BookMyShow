package com.bootproject.BookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.TicketNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.TicketDao;
import com.bootproject.BookMyShow.entity.Ticket;

@Service
public class TicketService {

	@Autowired
	TicketDao dao;
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket) {
		
		ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
		structure.setMessage("Ticket Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveTicket(ticket));
		
		return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(int tId){
		
		Ticket ticket = dao.findTicket(tId);
		if(ticket!=null) {
			ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();			
			structure.setMessage("Ticket found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticket);
			
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.FOUND);
		}
		throw new TicketNotFound("Ticket not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int tId){
		
		Ticket exticket = dao.findTicket(tId);
		
		if(exticket != null) {

			Ticket upticket = dao.updateTicket(ticket, tId);
			ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();		
			
			structure.setMessage("Ticket updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(upticket);
			
				return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
			}
			throw new TicketNotFound("Ticket not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(int tId){
		
		Ticket ticket = dao.findTicket(tId);
		
		if(ticket != null) {
			Ticket delticket = dao.deleteTicket(tId);
				
			ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();

			structure.setMessage("Ticket deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(delticket);
				
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
		}
		throw new TicketNotFound("Ticket not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket(){
		
		List<Ticket> ticketList = dao.findAllTicket();

		if(! ticketList.isEmpty()) {
			ResponseStructure<List<Ticket>> structure = new ResponseStructure<List<Ticket>>();
			structure.setMessage("All Tickets are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticketList);
			return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure, HttpStatus.FOUND);
		}
		throw new TicketNotFound("Tickets are not found");
	}
}
