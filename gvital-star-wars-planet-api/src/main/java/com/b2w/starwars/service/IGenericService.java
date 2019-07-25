package com.b2w.starwars.service;

import java.util.List;

/**
 * @author Guilherme Vital
 *
 * @param <T>
 */
public interface IGenericService<T> {
	
	T add(T object);
	
	List<T> findAll();
	
	List<T> findByName(String name);

	void delete(T object);
}
