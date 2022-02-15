package org.spieckermann.skateboarding.trucks;

@SuppressWarnings("serial")
public class TruckNotFoundException extends RuntimeException {
	
	TruckNotFoundException(Long id) {
		super("Could not find truck with id: " + id);
	}

}
