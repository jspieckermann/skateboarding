package org.spieckermann.skateboarding;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spieckermann.skateboarding.company.Company;
import org.spieckermann.skateboarding.trucks.Truck;
import org.spieckermann.skateboarding.trucks.TruckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	private static final Logger LOG = 
			LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner
	initDatabase(TruckRepository repository) {
		return args -> {
			LOG.info("Start preloading database");
			List<Truck> trucks = getTrucks();
			for (Truck truck : trucks) {
				repository.save(truck);
			}
			LOG.info("Finsihed preloading database");
		};
	}
	
	private static List<Truck> getTrucks() {
		List<Truck> trucks = new ArrayList<Truck>();
		trucks.add(new Truck(Company.THUNDER, "Titanium Lights", 147, 8, 49, 283));
		trucks.add(new Truck(Company.THUNDER, "Titanium Lights", 148, 8.25, 52, 308));
		return trucks;
	}
	
	

}
