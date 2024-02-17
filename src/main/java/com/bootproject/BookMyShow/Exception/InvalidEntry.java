package com.bootproject.BookMyShow.Exception;

public class InvalidEntry extends RuntimeException{
	
	String message;

	public String getMessage() {
		return message;
	}

	public InvalidEntry(String message) {
		super();
		this.message = message;
	}
	
	

}
