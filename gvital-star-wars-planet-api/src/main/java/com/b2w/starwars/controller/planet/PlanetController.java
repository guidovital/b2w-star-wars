/**
 * 
 */
package com.b2w.starwars.controller.planet;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.b2w.starwars.controller.IGenericController;
import com.b2w.starwars.exception.ObjectNotFoundException;
import com.b2w.starwars.model.client.PlanetApiSW;
import com.b2w.starwars.model.planet.Planet;
import com.b2w.starwars.service.planet.PlanetService;
import com.b2w.starwars.service.sequence.SequenceGeneratorService;
import com.b2w.starwars.service.swapi.SWAPIRestService;

/**
 * @author Guilherme Vital
 *
 */
@RestController
@RequestMapping(value="/planets")
public class PlanetController implements IGenericController<Planet> {
	
	@Autowired
	private PlanetService service;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private SWAPIRestService swapiService;

	@Override
	public ResponseEntity<Planet> add(@Valid @RequestBody Planet planet) {
		planet.setId(sequenceGenerator.generateSequence(Planet.SEQUENCE_NAME));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planet.getId()).toUri();
		Planet created = service.add(planet);
		return ResponseEntity.created(uri).body(getQtdFilms(created));
	}

	@Override
	public ResponseEntity<List<Planet>> findAll() {
		List<Planet> planets = service.findAll().stream().map(planet -> getQtdFilms(planet)).collect(Collectors.toList());
		return ResponseEntity.ok().body(planets);
	}

	@Override
	public ResponseEntity<Planet> findById(@PathVariable("id") Planet planet) {
		if(planet == null) {
			throw new ObjectNotFoundException("The planet was not found");
		}
		
		return ResponseEntity.ok().body(getQtdFilms(planet));
	}

	@Override
	public ResponseEntity<List<Planet>> findByName(@NotBlank @RequestParam(value="nome") String name) {
		List<Planet> planets = service.findByName(name).stream().map(planet -> getQtdFilms(planet)).collect(Collectors.toList());
		return ResponseEntity.ok().body(planets);
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") Planet planet) {
		if(planet == null) {
			throw new ObjectNotFoundException("The planet was not found");
		}
		
		service.delete(planet);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Set qtdFilms in a {@link Planet}
	 * 
	 * @param planet
	 * @return planet
	 */
	private Planet getQtdFilms(Planet planet) {
		planet.setQtdFilmes(findQtdFilms(planet.getNome()));
		return planet;
	}

	/**
	 * Find number of films in swapi service
	 * 
	 * @param name
	 * @return qtdFilms
	 */
	private int findQtdFilms(String name) {
		String upperCaseName = name.toUpperCase();
		Map<String, PlanetApiSW> swapiPlanets = swapiService.getSwapiPlanets();
		if(swapiPlanets.containsKey(upperCaseName)) {
			return swapiPlanets.get(upperCaseName).getFilms().size();
		}
		return 0;
	}

}
