package com.bootproject.BookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.MovieNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.MovieDao;
import com.bootproject.BookMyShow.entity.Movie;

@Service
public class MovieService {

	@Autowired
	MovieDao dao;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie) {
		
		ResponseStructure<Movie> structure = new ResponseStructure<Movie>();
		structure.setMessage("Movie Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveMovie(movie));
		
		return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int mId){
		
		Movie movie = dao.findMovie(mId);
		if(movie!=null) {
			ResponseStructure<Movie> structure = new ResponseStructure<Movie>();			
			structure.setMessage("Movie found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(movie);
			
			return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movie not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie, int mId){
		
		Movie exmovie = dao.findMovie(mId);
		
		if(exmovie != null) {

			Movie upmovie = dao.updateMovie(movie, mId);
			ResponseStructure<Movie> structure = new ResponseStructure<Movie>();		
			
			structure.setMessage("Movie updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(upmovie);
			
				return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.OK);
			}
			throw new MovieNotFound("Movie not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int mId){
		
		Movie movie = dao.findMovie(mId);
		
		if(movie != null) {
			Movie delmovie = dao.deleteMovie(mId);
				
			ResponseStructure<Movie> structure = new ResponseStructure<Movie>();

			structure.setMessage("Movie deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(delmovie);
				
			return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.OK);
		}
		throw new MovieNotFound("Movie not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie(){
		
		List<Movie> movieList = dao.findAllMovie();

		if(! movieList.isEmpty()) {
			ResponseStructure<List<Movie>> structure = new ResponseStructure<List<Movie>>();
			structure.setMessage("All Movies are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(movieList);
			return new ResponseEntity<ResponseStructure<List<Movie>>>(structure, HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movies are not found");
	}
}
