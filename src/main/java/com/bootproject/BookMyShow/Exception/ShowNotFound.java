package com.bootproject.BookMyShow.Exception;

public class ShowNotFound extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public ShowNotFound(String message) {
		super();
		this.message = message;
	}
	
	
}
