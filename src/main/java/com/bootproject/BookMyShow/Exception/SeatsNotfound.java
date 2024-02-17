package com.bootproject.BookMyShow.Exception;

public class SeatsNotfound extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public SeatsNotfound(String message) {
		super();
		this.message = message;
	}
	
	
}
