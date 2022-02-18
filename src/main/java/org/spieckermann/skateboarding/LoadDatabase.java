package org.spieckermann.skateboarding;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spieckermann.skateboarding.company.Company;
import org.spieckermann.skateboarding.decks.Concave;
import org.spieckermann.skateboarding.decks.Deck;
import org.spieckermann.skateboarding.decks.DeckRepository;
import org.spieckermann.skateboarding.trucks.Truck;
import org.spieckermann.skateboarding.trucks.TruckRepository;
import org.spieckermann.skateboarding.wheels.Wheel;
import org.spieckermann.skateboarding.wheels.WheelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	private static final Logger LOG = 
			LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner
	initDatabase(TruckRepository truckRepo, WheelRepository wheelRepo, DeckRepository deckRepo) {
		return args -> {
			LOG.info("Start preloading database");
			List<Truck> trucks = getTrucks();
			for (Truck truck : trucks) {
				truckRepo.save(truck);
			}
			List<Wheel> wheels = getWheels();
			for (Wheel wheel : wheels) {
				wheelRepo.save(wheel);
			}
			List<Deck> decks = getDecks();
			for (Deck deck : decks) {
				deckRepo.save(deck);
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
	
	private static List<Wheel> getWheels() {
		List<Wheel> wheels = new ArrayList<Wheel>();
		wheels.add(new Wheel(Company.SPITFIRE, "F4 Conical Full", 53, 33.5, 21.5, "99", 225));
		wheels.add(new Wheel(Company.SPITFIRE, "F4 Conical Full", 52, 32.5, 21, "101", 220));
		return wheels;
	}
	
	private static List<Deck> getDecks() {
		List<Deck> decks = new ArrayList<Deck>();
		decks.add(new Deck(Company.BAKER, "Zach Goon Wall", 8.25, 31.875, 14.25, Concave.MELLOW, 7, 6.5, 1200));
		decks.add(new Deck(Company.BAKER, "Team Brand Logo", 8.475, 31.9, 14.25, Concave.MEDIUM, 7, 6.5, 1300));
		return decks;
	}
	
	

}
