package org.spieckermann.skateboarding.hardware;

@SuppressWarnings("serial")
public class HardwareNotFoundException extends RuntimeException {
	
	HardwareNotFoundException(Long id) {
		super("Could not find hardware with id: " + id);
	}

}
