package org.spieckermann.skateboarding.wheels;

@SuppressWarnings("serial")
public class WheelNotFoundException extends RuntimeException {
	
	WheelNotFoundException(Long id) {
		super("Could not find wheel with id: " + id);
	}

}
