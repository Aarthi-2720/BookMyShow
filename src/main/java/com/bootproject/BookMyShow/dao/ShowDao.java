package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bootproject.BookMyShow.entity.Shows;
import com.bootproject.BookMyShow.repo.ShowRepo;

@Repository
public class ShowDao {
	
	@Autowired
	ShowRepo repo;
	
	public Shows saveShow(Shows show) {
		return repo.save(show);
	}
	
	public Shows findShow(int showId)
	{
		Optional<Shows> opshow = repo.findById(showId);
		if(opshow.isPresent())
		{
			return opshow.get();
		}
		return null;
	}
	
	public Shows deleteShow(int showId)
	{
		Shows show =findShow(showId);
		repo.delete(show);
		
		return show;
	}
	
	public Shows updateShow(Shows show , int  showId)
	{
		Shows exshow= findShow(showId);
		
		if(exshow!=null)
		{
			show.setShowId(showId);
			return repo.save(show);
		}
		return null;
	}
	
	public List<Shows> findAllShow()
	{
		List<Shows> show = repo.findAll();
		return show;
	}

}
