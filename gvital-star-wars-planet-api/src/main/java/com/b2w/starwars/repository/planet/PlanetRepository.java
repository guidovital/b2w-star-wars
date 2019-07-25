package com.b2w.starwars.repository.planet;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.b2w.starwars.model.planet.Planet;

/**
 * @author Guilherme Vital
 *
 */
@Repository
public interface PlanetRepository extends MongoRepository<Planet, Long> {

	List<Planet> findByNomeContaining(String nome);

}
