package com.bootproject.BookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bootproject.BookMyShow.Exception.SeatsNotfound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.SeatsDao;
import com.bootproject.BookMyShow.entity.Seats;

@Service
public class SeatsService {

	@Autowired
	SeatsDao dao;
	
public ResponseEntity<ResponseStructure<Seats>> saveSeats(Seats movie) {
		
		ResponseStructure<Seats> structure = new ResponseStructure<Seats>();
		structure.setMessage("Seat Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveSeats(movie));
		
		return new ResponseEntity<ResponseStructure<Seats>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Seats>> findSeats(int sId){
		
		Seats seat = dao.findSeats(sId);
		if(seat!=null) {
			ResponseStructure<Seats> structure = new ResponseStructure<Seats>();			
			structure.setMessage("Seat found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(seat);
			
			return new ResponseEntity<ResponseStructure<Seats>>(structure, HttpStatus.FOUND);
		}
		throw new SeatsNotfound("Seat not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Seats>> updateSeats(Seats seat, int sId){
		
		Seats exseat = dao.findSeats(sId);
		
		if(exseat != null) {

			Seats upseat = dao.updateSeats(seat, sId);
			ResponseStructure<Seats> structure = new ResponseStructure<Seats>();		
			
			structure.setMessage("Seat updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(upseat);
			
				return new ResponseEntity<ResponseStructure<Seats>>(structure, HttpStatus.OK);
			}
			throw new SeatsNotfound("Seat not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<Seats>> deleteSeats(int sId){
		
		Seats seat = dao.findSeats(sId);
		
		if(seat != null) {
			Seats delseat = dao.deleteSeats(sId);
				
			ResponseStructure<Seats> structure = new ResponseStructure<Seats>();

			structure.setMessage("Seat deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(delseat);
				
			return new ResponseEntity<ResponseStructure<Seats>>(structure, HttpStatus.OK);
		}
		throw new SeatsNotfound("Seat not found with given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Seats>>> findAllSeats(){
		
		List<Seats> seatList = dao.findAllSeats();

		if(! seatList.isEmpty()) {
			ResponseStructure<List<Seats>> structure = new ResponseStructure<List<Seats>>();
			structure.setMessage("All Seats are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(seatList);
			return new ResponseEntity<ResponseStructure<List<Seats>>>(structure, HttpStatus.FOUND);
		}
		throw new SeatsNotfound("Seats are not found");
	}
}
