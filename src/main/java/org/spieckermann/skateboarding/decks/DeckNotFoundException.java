package org.spieckermann.skateboarding.decks;

@SuppressWarnings("serial")
public class DeckNotFoundException extends RuntimeException {
	
	DeckNotFoundException(Long id) {
		super("Could not find deck with id: " + id);
	}

}
