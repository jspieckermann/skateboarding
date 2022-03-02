package org.spieckermann.skateboarding.decks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckService {
	
	@Autowired
	private DeckRepository repository;
	
	public List<Deck> getDecks() {		
		return repository.findAll();
	}
	
	public List<Deck> getDecks(double width) {
		return repository.findByWidth(width);
	}
	
	public Deck getDeck(Long id) {
		return repository.findById(id).orElseThrow(() -> new DeckNotFoundException(id));
	}
	
	public Deck createDeck(Deck deck) {
		return repository.save(deck);	
	}
	
	public Deck updateDeck(Long id, Deck deck) {
		
		return repository.save(repository.findById(id).map(myDeck -> {
			myDeck.setCompany(deck.getCompany());
			myDeck.setName(deck.getName());
			myDeck.setWidth(deck.getWidth());
			myDeck.setLength(deck.getLength());
			myDeck.setWheelbase(deck.getWheelbase());
			myDeck.setConcave(deck.getConcave());
			myDeck.setNose(deck.getNose());
			myDeck.setTail(deck.getTail());
			myDeck.setWeight(deck.getWeight());
			myDeck.setPrice(deck.getPrice());
			return myDeck;
		}).orElseThrow(() -> new DeckNotFoundException(id)));
	}
	
	public void deleteDeck(Long id) {
		repository.deleteById(id);
	}

}
