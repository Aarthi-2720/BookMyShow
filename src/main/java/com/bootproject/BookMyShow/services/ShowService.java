package com.bootproject.BookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.ShowNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.MovieDao;
import com.bootproject.BookMyShow.dao.ShowDao;
import com.bootproject.BookMyShow.entity.Movie;
import com.bootproject.BookMyShow.entity.Shows;

@Service
public class ShowService {

	@Autowired
	ShowDao dao;
	
	@Autowired
	MovieDao mdao;
	
public ResponseEntity<ResponseStructure<Shows>> saveShows(Shows show) {
		
		ResponseStructure<Shows> structure = new ResponseStructure<Shows>();
		structure.setMessage("Show Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveShow(show));
		
		return new ResponseEntity<ResponseStructure<Shows>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Shows>> findShows(int sId){
		
		Shows show = dao.findShow(sId);
		if(show!=null) {
			ResponseStructure<Shows> structure = new ResponseStructure<Shows>();			
			structure.setMessage("Show found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(show);
			
			return new ResponseEntity<ResponseStructure<Shows>>(structure, HttpStatus.FOUND);
		}
		throw new ShowNotFound("Show not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Shows>> updateShow(Shows show, int sId){
		
		Shows exshow = dao.findShow(sId);
		
		if(exshow != null) {

			Shows upshow = dao.updateShow(show, sId);
			ResponseStructure<Shows> structure = new ResponseStructure<Shows>();		
			
			structure.setMessage("Show updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(upshow);
			
				return new ResponseEntity<ResponseStructure<Shows>>(structure, HttpStatus.OK);
			}
			throw new ShowNotFound("Show not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Shows>> deleteShows(int sId){
		
		Shows show = dao.findShow(sId);
		
		if(show != null) {
			Shows delshow = dao.deleteShow(sId);
				
			ResponseStructure<Shows> structure = new ResponseStructure<Shows>();

			structure.setMessage("Seat deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(delshow);
				
			return new ResponseEntity<ResponseStructure<Shows>>(structure, HttpStatus.OK);
		}
		throw new ShowNotFound("Show not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Shows>>> findAllShows(){
		
		List<Shows> showList = dao.findAllShow();

		if(! showList.isEmpty()) {
			ResponseStructure<List<Shows>> structure = new ResponseStructure<List<Shows>>();
			structure.setMessage("All Shows are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(showList);
			return new ResponseEntity<ResponseStructure<List<Shows>>>(structure, HttpStatus.FOUND);
		}
		throw new ShowNotFound("Shows are not found");
	}
	
//	public ResponseEntity<ResponseStructure<Shows>> assignMovieToShow(int sId, int mId){
//		
//		Shows show = dao.findShow(sId);
//		if(show!=null) {
//			Movie movie = mdao.findMovie(mId);
//			if(movie != null) {
//				show.setSMovie(movie);
//				Shows upshow = dao.updateShow(show, sId);
//				
//				ResponseStructure<Shows> structure = new ResponseStructure<Shows>();
//				structure.setMessage(null);
//			}
//		}
//	}
}
