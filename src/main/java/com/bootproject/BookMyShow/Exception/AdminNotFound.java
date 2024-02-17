package com.bootproject.BookMyShow.Exception;

public class AdminNotFound extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public AdminNotFound(String message) {
		super();
		this.message = message;
	}
	
	
}
