package com.b2w.starwars.service.swapi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.starwars.exception.ServiceUnavailable;
import com.b2w.starwars.model.client.PlanetApiSW;
import com.b2w.starwars.model.client.ResultApiSW;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Guilherme Vital
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class SWAPIRestService {
	
	@Autowired
	private Environment env;
	
	RestTemplate restTemplate = new RestTemplate();

	/**
	 * @return
	 */
	@Cacheable("swapi-planets")
	public Map<String, PlanetApiSW> getSwapiPlanets() {
		Map<String, PlanetApiSW> planetMap = new HashMap<>(); 
		try {
			ResultApiSW body = restTemplate.exchange(env.getProperty("apiSWAPI.url"), HttpMethod.GET, buildHeader(), ResultApiSW.class).getBody();
			body.getResults().forEach(planet -> planetMap.put(planet.getName().toUpperCase(), planet));
			while(body.getNext() != null) {
				body = restTemplate.exchange(body.getNext(), HttpMethod.GET, buildHeader(), ResultApiSW.class).getBody();
				body.getResults().forEach(planet -> planetMap.put(planet.getName().toUpperCase(), planet));
			}
			return planetMap;
		} catch (Exception e) {
			throw new ServiceUnavailable("SWAPI is unavailable");
		}
	}

	/**
	 * @return
	 */
	private HttpEntity<String> buildHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return entity;
	}

}
