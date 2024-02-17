package com.bootproject.BookMyShow.Exception;

public class BookingNotFound extends RuntimeException {

	String message;

	public String getMessage() {
		return message;
	}

	public BookingNotFound(String message) {
		super();
		this.message = message;
	}
	
	
}
