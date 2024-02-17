package com.bootproject.BookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.TheatreNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.TheatreDao;
import com.bootproject.BookMyShow.entity.Theatre;

@Service
public class TheatreService {

	@Autowired
	TheatreDao dao;
	
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre) {
		
		ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
		structure.setMessage("Theatre Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveTheatre(theatre));
		
		return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int tId){
		
		Theatre theatre = dao.findTheatre(tId);
		if(theatre!=null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();			
			structure.setMessage("Theatre found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatre);
			
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre, int tId){
		
		Theatre extheatre = dao.findTheatre(tId);
		
		if(extheatre != null) {
			
			Theatre uptheatre = dao.updateTheatre(theatre, tId);
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();		
			
			structure.setMessage("Theatre updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(uptheatre);
			
				return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int tId){
		
		Theatre theatre = dao.findTheatre(tId);
		
		if(theatre != null) {
			Theatre deltheatre = dao.deleteTheatre(tId);
				
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();

			structure.setMessage("Theatre deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(deltheatre);
				
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		}
		throw new TheatreNotFound("Theatre not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre(){
		
		List<Theatre> theatreList = dao.findAllTheatre();

		if(! theatreList.isEmpty()) {
			ResponseStructure<List<Theatre>> structure = new ResponseStructure<List<Theatre>>();
			structure.setMessage("All Theatres are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatreList);
			return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure, HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatres are not found");
	}
}
