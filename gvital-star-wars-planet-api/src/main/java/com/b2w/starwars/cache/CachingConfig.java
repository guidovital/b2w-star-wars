package com.b2w.starwars.cache;

import java.time.LocalDateTime;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Guilherme Vital
 *
 */
@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {
	public static final String SWAPI = "swapi-planets";
	
	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(SWAPI);

		return cacheManager;
	}

	/**
	 * Reload cache hours * minutes * seconds * milisec(1000)
	 * 
	 * Example: 1 * 1 * 1 * 60 * 1000 = 60.000 milisec = 1 minute
	 * Example: 1 * 1 * 60 * 60 * 1000 = 3.600.000 milisec = 1 hour
	 */
	@CacheEvict(allEntries = true, value = { SWAPI })
	@Scheduled(fixedDelay = 1 * 1 * 60 * 60 * 1000, initialDelay = 500) 
	public void reportCacheEvict() {
		System.out.println("Flush Cache " + LocalDateTime.now());
	}
}