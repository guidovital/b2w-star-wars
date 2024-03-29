package com.b2w.starwars.exception;

/**
 * @author Guilherme Vital
 *
 */
public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
