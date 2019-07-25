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
public class ResultApiSW {
	
	private List<PlanetApiSW> results;
	
	private String next;

	/**
	 * @param planets
	 * @param name
	 */
	public ResultApiSW(List<PlanetApiSW> results, String next) {
		this.results = results;
		this.next = next;
	}

}
