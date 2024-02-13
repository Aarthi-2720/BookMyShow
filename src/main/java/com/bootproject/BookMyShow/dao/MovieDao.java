package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Movie;
import com.bootproject.BookMyShow.repo.MovieRepo;

@Repository
public class MovieDao {
	
	@Autowired
	MovieRepo repo;
	
	public Movie saveMovie(Movie movie) {
		return repo.save(movie);
	}
	
	public Movie findMovie(int mId)
	{
		Optional<Movie> opMovie = repo.findById(mId);
		if(opMovie.isPresent())
		{
			return opMovie.get();
		}
		return null;
	}
	
	public Movie deleteMovie(int mId)
	{
		Movie movie =findMovie(mId);
		repo.delete(movie);
		
		return movie;
	}
	
	public Movie updateMovie(Movie movie , int  mId)
	{
		Movie exMovie= findMovie(mId);
		
		if(exMovie!=null)
		{
			movie.setMId(mId);
			return repo.save(movie);
		}
		return null;
	}
	
	public List<Movie> findAllMovie()
	{
		List<Movie> movie = repo.findAll();
		return movie;
	}

}
