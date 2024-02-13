package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Show;
import com.bootproject.BookMyShow.entity.Theatre;
import com.bootproject.BookMyShow.repo.TheatreRepo;

@Repository
public class TheatreDao {
	
	@Autowired
	TheatreRepo repo;
	
	public Theatre saveTheatre(Theatre theatre) {
		return repo.save(theatre);
	}
	
	public Theatre findTheatre(int tId)
	{
		Optional<Theatre> opTheatre= repo.findById(tId);
		if(opTheatre.isPresent())
		{
			return opTheatre.get();
		}
		return null;
	}
	
	public Theatre deleteTheatre(int tId)
	{
		Theatre theatre =findTheatre(tId);
		repo.delete(theatre);
		
		return theatre;
	}
	
	public Theatre updateTheatre(Theatre theatre , int  tId)
	{
		Theatre extheatre= findTheatre(tId);
		
		if(extheatre!=null)
		{
			theatre.setTId(tId);
			return repo.save(theatre);
		}
		return null;
	}
	
	public List<Theatre> findAllTheatre()
	{
		List<Theatre> theatre = repo.findAll();
		return theatre;
	}


}
