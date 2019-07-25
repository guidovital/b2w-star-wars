package com.b2w.starwars.service.planet;

import java.util.List;

import org.springframework.stereotype.Service;

import com.b2w.starwars.model.planet.Planet;
import com.b2w.starwars.repository.planet.PlanetRepository;
import com.b2w.starwars.service.IGenericService;

/**
 * @author Guilherme Vital
 *
 */
@Service
public class PlanetService implements IGenericService<Planet>{
	
	private PlanetRepository repository;
	
	/**
	 * @param repository
	 */
	
	public PlanetService(PlanetRepository repository) {
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see com.b2w.starwars.service.IGenericService#add(java.lang.Object)
	 */
	@Override
	public Planet add(Planet planet) {
		return repository.save(planet);
	}

	/* (non-Javadoc)
	 * @see com.b2w.starwars.service.IGenericService#findAll()
	 */
	@Override
	public List<Planet> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.b2w.starwars.service.IGenericService#findByName(java.lang.String)
	 */
	@Override
	public List<Planet> findByName(String name) {
		return repository.findByNomeContaining(name);
	}

	/* (non-Javadoc)
	 * @see com.b2w.starwars.service.IGenericService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Planet planet) {
		repository.delete(planet);
	}
	
}
