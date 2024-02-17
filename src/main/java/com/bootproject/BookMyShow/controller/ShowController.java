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
import com.bootproject.BookMyShow.entity.Shows;
import com.bootproject.BookMyShow.services.ShowService;

@RestController
@RequestMapping("show")
public class ShowController {

	@Autowired
	ShowService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Shows>> saveShows(@RequestBody Shows show){
		return service.saveShows(show);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Shows>> findShows(@RequestParam int sId){
		return service.findShows(sId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Shows>> deleteShows(@RequestParam int sId){
		return service.deleteShows(sId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Shows>> updateShows(@RequestBody Shows show, @RequestParam int sId){
		return service.updateShow(show, sId);
	}
	
	@GetMapping("showlist")
	public ResponseEntity<ResponseStructure<List<Shows>>> findAllShows(){
		return service.findAllShows();
	}
}
