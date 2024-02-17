package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Ticket;
import com.bootproject.BookMyShow.repo.TicketRepo;

@Repository
public class TicketDao {

	@Autowired
	TicketRepo repo;
	
	public Ticket saveTicket(Ticket ticket) {
		return repo.save(ticket);
	}
	
	public Ticket findTicket(int tId)
	{
		Optional<Ticket> opTicket= repo.findById(tId);
		if(opTicket.isPresent())
		{
			return opTicket.get();
		}
		return null;
	}
	
	public Ticket deleteTicket(int tId)
	{
		Ticket ticket =findTicket(tId);
		repo.delete(ticket);
		
		return ticket;
	}
	
	public Ticket updateTicket(Ticket ticket , int  tId)
	{
		Ticket exTicket= findTicket(tId);
		
		if(exTicket!=null)
		{
			ticket.setTId(tId);
			return repo.save(ticket);
		}
		return null;
	}
	
	public List<Ticket> findAllTicket()
	{
		List<Ticket> ticket = repo.findAll();
		return ticket;
	}

}
