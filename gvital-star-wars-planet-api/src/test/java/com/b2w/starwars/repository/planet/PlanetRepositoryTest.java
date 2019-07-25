package com.b2w.starwars.repository.planet;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.b2w.starwars.model.planet.Planet;

/**
 * @author Guilherme Vital
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetRepositoryTest {

	Planet planet1, planet2;

	@Autowired
	PlanetRepository repository;

	@Before
	public void setUp() {

		planet1 = repository.save(new Planet(99999998L, "Aldebaran", "teste", "teste"));
		planet2 = repository.save(new Planet(99999999L, "Dagobah", "teste", "teste"));
	}

	@After
	public void tearDown() {
		repository.delete(planet1);
		repository.delete(planet2);
	}
	
	@Test
    public void add_planet() {
    	Planet planet = repository.save(new Planet(9999999L, "Bespin","teste","teste"));
        Assert.assertFalse(planet.getId() == null);
        repository.delete(planet);
    }

    @Test
    public void find_by_name() {
    	
    	List<Planet> result = repository.findByNomeContaining(planet1.getNome());
    	Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void find_by_id() {
    	Optional<Planet> obj = repository.findById(planet1.getId());
    	Assert.assertNotNull(obj);
    }

    @Test
    public void find_all() {
    	List<Planet> result = repository.findAll();
    	Assert.assertFalse(result.isEmpty());
    }
    
    @Test
    public void delete_planet() {
    	repository.delete(planet2);
    	Optional<Planet> response = repository.findById(planet2.getId());
    	Assert.assertFalse(response.isPresent());
    }

}
