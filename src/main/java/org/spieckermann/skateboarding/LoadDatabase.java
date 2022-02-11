package org.spieckermann.skateboarding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spieckermann.skateboarding.brands.Brand;
import org.spieckermann.skateboarding.brands.BrandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	private static final Logger LOG = 
			LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner
	initDatabase(BrandRepository repository) {
		return args -> {
			LOG.info("Loading" + repository.save(new Brand("Thunder")) + " " +
					repository.save(new Brand("Independent")));
		};
	}

}
