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
import com.bootproject.BookMyShow.entity.Movie;
import com.bootproject.BookMyShow.services.MovieService;

@RestController
@RequestMapping("movie")
public class MovieController {

	@Autowired
	MovieService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestBody Movie movie){
		return service.saveMovie(movie);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam int mId){
		return service.findMovie(mId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int mId){
		return service.deleteMovie(mId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestBody Movie movie, @RequestParam int mId){
		return service.updateMovie(movie, mId);
	}
	
	@GetMapping("movielist")
	public ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie(){
		return service.findAllMovie();
	}

}
