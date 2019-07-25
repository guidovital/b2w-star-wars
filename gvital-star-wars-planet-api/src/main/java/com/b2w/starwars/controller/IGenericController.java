package com.b2w.starwars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller interface
 * 
 * @author Guilherme Vital
 *
 * @param <T>
 */
public interface IGenericController<T> {
	
	/**
	 * Insert a new entity
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping
	ResponseEntity<T> add(T model);
	
	/**
	 * Find all entities
	 * 
	 * @return
	 */
	@GetMapping
	ResponseEntity<List<T>> findAll();
	
	/**
	 * Find a entity by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	ResponseEntity<T> findById(T object);
	
	/**
	 * Find entities by Name
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping(value="/findByName")
	ResponseEntity<List<T>> findByName(String name);

	/**
	 * Delete a entity
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/{id}")
	ResponseEntity<Void> delete(T object);
}
