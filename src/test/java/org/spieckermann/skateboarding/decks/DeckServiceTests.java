package org.spieckermann.skateboarding.decks;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spieckermann.skateboarding.company.Company;

@ExtendWith(MockitoExtension.class)
public class DeckServiceTests {
	
	@InjectMocks
	private DeckService sut;
	
	@Mock
	private DeckRepository repository;
	
	@Mock
	private Deck deck;
	
	@Test
	public void testGetDecks() {
		sut.getDecks();
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void testGetDecksByWidth() {
		sut.getDecks(8.0);
		verify(repository, times(1)).findByWidth(8.0);
	}
	
	@Test
	public void testCreateDeck() {
		sut.createDeck(deck);
		verify(repository, times(1)).save(deck);
	}
	
	@Test
	public void testDeleteDeck() {
		sut.deleteDeck(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testGetDeckException() {
		Assertions.assertThrows(DeckNotFoundException.class, () -> {
			sut.getDeck(1L);
		}, "DeckNotFoundException was expected");
	}
	
	@Test
	public void testGetDeck() {
		when(repository.findById(1L)).thenReturn(Optional.of(deck));
		sut.getDeck(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testUpdateDeckException() {
		Assertions.assertThrows(DeckNotFoundException.class, () -> {
			sut.updateDeck(1L, deck);
		}, "DeckNotFoundException was expected");
	}
	
	@Test
	public void testUpdateDeck() {
		Deck myDeck = new Deck(Company.BAKER, "Zach Goon Wall", 8.25, 31.875, 14.25, Concave.MELLOW, 7, 6.5, 1200);
		
		when(repository.findById(1L)).thenReturn(Optional.of(deck));
		sut.updateDeck(1L, myDeck);
		verify(repository, times(1)).findById(1L);
		verify(deck, times(1)).setCompany(Company.BAKER);
		verify(deck, times(1)).setName("Zach Goon Wall");
		verify(deck, times(1)).setWidth(8.25);
		verify(deck, times(1)).setLength(31.875);
		verify(deck, times(1)).setWheelbase(14.25);
		verify(deck, times(1)).setConcave(Concave.MELLOW);
		verify(deck, times(1)).setNose(7.0);
		verify(deck, times(1)).setTail(6.5);
		verify(deck, times(1)).setWeight(1200);
		verify(repository, times(1)).save(deck);
	}
	
}
