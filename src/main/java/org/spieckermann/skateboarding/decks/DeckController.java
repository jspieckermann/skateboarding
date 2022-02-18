package org.spieckermann.skateboarding.decks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckController {
	
	@Autowired
	private DeckService service;
	
	@GetMapping("/decks")
	List<Deck> getDecks(@RequestParam(required = false) String width) {
		return width == null ? service.getDecks() : service.getDecks(Double.parseDouble(width));
	}
	
	@GetMapping("/decks/{id}")
	Deck getDeck(@PathVariable Long id) {
		return service.getDeck(id);
	}
	
	@PostMapping("/decks")
	Deck createDeck(@RequestBody Deck deck) {
		return service.createDeck(deck);
	}
	
	@PutMapping("/decks/{id}")
	Deck updateDeck(@PathVariable Long id, @RequestBody Deck deck) {
		return service.updateDeck(id, deck);
	}
	
	@DeleteMapping("/decks/{id}")
	void deleteDeck(@PathVariable Long id) {
		service.deleteDeck(id);
	}

}
