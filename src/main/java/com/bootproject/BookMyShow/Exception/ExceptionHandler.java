package com.bootproject.BookMyShow.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bootproject.BookMyShow.config.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AdminNotFoundException(AdminNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Admin not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MovieNotFoundException(MovieNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Movie not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BookingNotFoundException(BookingNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Booking not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ShowNotFoundException(ShowNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Show not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> UserNotFoundException(UserNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("User not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TheatreNotFoundException(TheatreNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Theatre not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TicketNotFoundException( TicketNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Ticket not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SeatNotFoundException(SeatsNotfound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Seat not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<String>> InvalidentryException(InvalidEntry ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		structure.setMessage("Provide valud details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.FORBIDDEN);
	}
	
}
