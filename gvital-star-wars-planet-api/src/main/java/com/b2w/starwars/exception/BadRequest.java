package com.b2w.starwars.exception;

public class BadRequest extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BadRequest(String msg) {
		super(msg);
	}
}
