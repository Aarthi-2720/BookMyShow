package com.bootproject.BookMyShow.Exception;

public class MovieNotFound extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public MovieNotFound(String message) {
		super();
		this.message = message;
	}
	
	
}
