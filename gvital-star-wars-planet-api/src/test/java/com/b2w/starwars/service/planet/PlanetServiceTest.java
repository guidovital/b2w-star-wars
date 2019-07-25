package com.b2w.starwars.service.planet;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.b2w.starwars.model.planet.Planet;
import com.b2w.starwars.repository.planet.PlanetRepository;

/**
 * @author Guilherme Vital
 *
 */
public class PlanetServiceTest {

	private PlanetService service;

	@Mock
	private PlanetRepository repository;

	@Before
	public void setUp() {
		repository = Mockito.mock(PlanetRepository.class);
		service = new PlanetService(repository);
	}

	@Test
	public void add() {
		Planet planet = new Planet(123559L, "Tattoine", "hot", "desert");
		when(repository.save(planet)).thenReturn(planet);

		Planet created = service.add(planet);
		Assert.assertEquals(created.getNome(), planet.getNome());
	}

	@Test
	public void find_all() {
		Planet planet1 = new Planet("Teste1", "Teste", "Teste");
		Planet planet2 = new Planet("Teste2", "Teste", "Teste");
		Planet planet3 = new Planet("Teste3", "Teste", "Teste");
		Planet planet4 = new Planet("Teste4", "Teste", "Teste");
		List<Planet> planets = new ArrayList<Planet>();
		planets.add(planet1);
		planets.add(planet2);
		planets.add(planet3);
		planets.add(planet4);

		when(repository.findAll()).thenReturn(planets);

		List<Planet> result = service.findAll();
		Assert.assertEquals(result.get(0).getNome(), planet1.getNome());
	}

	@Test
	public void find_by_name() {
		Planet planet1 = new Planet("Star Destroyer 1", "Teste", "Teste");
		Planet planet2 = new Planet("Star Destroyer 2", "Teste", "Teste");
		Planet planet3 = new Planet("Star Destroyer 3", "Teste", "Teste");
		Planet planet4 = new Planet("Star Destroyer 4", "Teste", "Teste");
		List<Planet> planets = new ArrayList<Planet>();
		planets.add(planet1);
		planets.add(planet2);
		planets.add(planet3);
		planets.add(planet4);

		when(repository.findByNomeContaining("Star")).thenReturn(planets);

		List<Planet> result = service.findByName("Star");
		Assert.assertEquals(result.get(0).getNome(), planet1.getNome());
	}
}
