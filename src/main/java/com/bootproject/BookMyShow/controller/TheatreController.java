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
import com.bootproject.BookMyShow.entity.Theatre;
import com.bootproject.BookMyShow.services.TheatreService;


@RestController
@RequestMapping("theatre")
public class TheatreController {

	@Autowired
	TheatreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestBody Theatre theatre){
		return service.saveTheatre(theatre);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(@RequestParam int tId){
		return service.findTheatre(tId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam int tId){
		return service.deleteTheatre(tId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestBody Theatre theatre, @RequestParam int tId){
		return service.updateTheatre(theatre, tId);
	}
	
	@GetMapping("theatrelist")
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre(){
		return service.findAllTheatre();
	}
}
