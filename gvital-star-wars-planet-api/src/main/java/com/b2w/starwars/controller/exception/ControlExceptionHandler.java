package com.b2w.starwars.controller.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.b2w.starwars.exception.ObjectNotFoundException;
import com.b2w.starwars.exception.ServiceUnavailable;
import com.b2w.starwars.model.exception.GenericError;

@ControllerAdvice
public class ControlExceptionHandler {

	/**
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<GenericError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		GenericError err = new GenericError(LocalDateTime.now(), status.value(), "Not found", request.getRequestURI(), null, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	/**
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GenericError> badRequest(MethodArgumentNotValidException e, HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		HttpStatus status = HttpStatus.BAD_REQUEST;
		GenericError err = new GenericError(LocalDateTime.now(), status.value(), "Invalid request", request.getRequestURI(), errors, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	/**
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ServiceUnavailable.class)
	public ResponseEntity<GenericError> servicUnavailable(ServiceUnavailable e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		GenericError err = new GenericError(LocalDateTime.now(), status.value(), "Unavailable service", request.getRequestURI(), null, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

}
