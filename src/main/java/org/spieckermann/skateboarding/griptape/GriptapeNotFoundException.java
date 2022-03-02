package org.spieckermann.skateboarding.griptape;

@SuppressWarnings("serial")
public class GriptapeNotFoundException extends RuntimeException {
	
	GriptapeNotFoundException(Long id) {
		super("Could not find griptape with id: " + id);
	}

}
