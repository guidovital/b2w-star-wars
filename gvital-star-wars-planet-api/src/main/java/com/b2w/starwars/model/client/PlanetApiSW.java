package com.b2w.starwars.model.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Guilherme Vital
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetApiSW {
	
	private String name;

	private List<String> films;

	/**
	 * @param name
	 * @param films
	 */
	public PlanetApiSW(String name, List<String> films) {
		this.name = name;
		this.films = films;
	}

}
