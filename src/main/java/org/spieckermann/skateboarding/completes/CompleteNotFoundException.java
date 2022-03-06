package org.spieckermann.skateboarding.completes;

@SuppressWarnings("serial")
public class CompleteNotFoundException extends RuntimeException {
	
	CompleteNotFoundException(Long id) {
		super("Could not find complete with id: " + id);
	}

}
