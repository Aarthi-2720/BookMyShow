package com.bootproject.BookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.MovieNotFound;
import com.bootproject.BookMyShow.Exception.ShowNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.MovieDao;
import com.bootproject.BookMyShow.dao.ShowDao;
import com.bootproject.BookMyShow.entity.Movie;
import com.bootproject.BookMyShow.entity.Shows;

@Service
public class MovieService {

	@Autowired
	MovieDao dao;
	
	@Autowired
	ShowDao sdao;
	
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
	
	public ResponseEntity<ResponseStructure<Movie>> assignShowToMovie(int mId, int sId){
		
		Movie movie = dao.findMovie(mId);
		if(movie != null) {
			Shows show = sdao.findShow(sId);
			if(show !=null) {
				movie.setMShow(show);
				Movie upmovie = dao.updateMovie(movie, mId);
				
				ResponseStructure<Movie> structure = new ResponseStructure<Movie>();
				structure.setMessage("Show assign to movie");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(upmovie);
				
				return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.OK);
			}
			throw new ShowNotFound("Show does not found with given id");
		}
		throw new MovieNotFound("Movie does not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> deleteShowFromMovie(int mId, int sId){
		
		Movie movie = dao.findMovie(mId);
		if(movie != null) {
			Shows show = movie.getMShow();
			int id = show.getShowId();
			if(id == sId) {
				movie.setMShow(null);
				show = sdao.deleteShow(id);
				Movie upmovie = dao.updateMovie(movie, mId);
				
				ResponseStructure<Movie> structure = new ResponseStructure<Movie>();
				structure.setMessage("Show deleted from movie");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(upmovie);
				
				return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.OK);
			}
			throw new ShowNotFound("Show does not found with given id");
		}
		throw new MovieNotFound("Movie does not found with given id");
	}
}
