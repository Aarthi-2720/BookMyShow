package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Show;
import com.bootproject.BookMyShow.repo.ShowRepo;

@Repository
public class ShowDao {
	
	@Autowired
	ShowRepo repo;
	
	public Show saveShow(Show show) {
		return repo.save(show);
	}
	
	public Show findShow(int showId)
	{
		Optional<Show> opshow = repo.findById(showId);
		if(opshow.isPresent())
		{
			return opshow.get();
		}
		return null;
	}
	
	public Show deleteShow(int showId)
	{
		Show show =findShow(showId);
		repo.delete(show);
		
		return show;
	}
	
	public Show updateShow(Show show , int  showId)
	{
		Show exshow= findShow(showId);
		
		if(exshow!=null)
		{
			show.setShowId(showId);
			return repo.save(show);
		}
		return null;
	}
	
	public List<Show> findAllShow()
	{
		List<Show> show = repo.findAll();
		return show;
	}

}
