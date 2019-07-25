package com.b2w.starwars.controller.planet;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.starwars.model.planet.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetControllerTest {

	private static final String API = "/planets/";

	private static final String BASE_PATH = "http://localhost:";

	@LocalServerPort
	private int port;

	RestTemplate rest;

	@Before
	public void setUp() {
		rest = new RestTemplate();
	}

	@Test
	public void add_Planet_Test() {

		Planet planet = new Planet("Luke", "Teste", "Teste");
		ResponseEntity<String> response = rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		Assert.assertEquals(201, response.getStatusCodeValue());
		rest.delete(response.getHeaders().getLocation());
	}

	@Test
	public void add_Empty_Name() {
		Planet planet = new Planet("", "Teste", "Teste");
		try {
			rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void add_Empty_Climate() {
		Planet planet = new Planet("Darth Vader", "", "Teste");
		try {
			rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void add_Empty_Terrain() {
		Planet planet = new Planet("Yoda", "Teste", "");
		try {
			rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void add_Null_Name() {
		Planet planet = new Planet(null, "Teste", "Teste");
		try {
			rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void add_Null_Climate() {
		Planet planet = new Planet("Chewbacca", null, "Teste");
		try {
			rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void add_Null_Terrain() {
		Planet planet = new Planet("Han Solo", "Teste", null);
		try {
			rest.postForEntity(BASE_PATH + port + API, planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void find_By_Id() {
		Planet planet = new Planet("Léia", "Teste", "Teste");
		ResponseEntity<String> response = rest.postForEntity(BASE_PATH + port + API, planet, String.class);

		ResponseEntity<String> planetFound = rest.getForEntity(response.getHeaders().getLocation(), String.class);
		Assert.assertEquals(200, planetFound.getStatusCodeValue());

		rest.delete(response.getHeaders().getLocation());
	}

	@Test
	public void find_by_id_not_found() {
		try {
			rest.getForEntity(BASE_PATH + port + API + "findByName?id=0", String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void find_by_name() {
		Planet planet = new Planet("Anakin", "Teste", "Teste");
		ResponseEntity<String> response = rest.postForEntity(BASE_PATH + port + API, planet, String.class);

		ResponseEntity<String> findResult = rest.getForEntity(BASE_PATH + port + API + "findByName?nome=Anakin", String.class);
		Assert.assertEquals(200, findResult.getStatusCodeValue());

		rest.delete(response.getHeaders().getLocation());

	}

	@Test
	public void find_by_name_if_not_exists() {
		ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port + API + "findByName?nome=Teste", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
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
		ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port + API, String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}
}
