package org.spieckermann.skateboarding.completes;

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
import org.spieckermann.skateboarding.decks.Deck;
import org.spieckermann.skateboarding.griptape.Griptape;
import org.spieckermann.skateboarding.hardware.Hardware;
import org.spieckermann.skateboarding.trucks.Truck;
import org.spieckermann.skateboarding.wheels.Wheel;

@ExtendWith(MockitoExtension.class)
public class CompleteServiceTests {
	
	@InjectMocks
	private CompleteService sut;
	
	@Mock
	private CompleteRepository repository;
	
	@Mock
	private Complete complete;
	
	@Mock
	private Deck deck;
	
	@Mock
	private Truck trucks;
	
	@Mock
	private Wheel wheels;
	
	@Mock
	private Griptape griptape;
	
	@Mock
	private Hardware hardware;
	
	@Test
	public void testGetCompletes() {
		sut.getCompletes();
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void testCreateComplete() {
		sut.createComplete(complete);
		verify(repository, times(1)).save(complete);
	}
	
	@Test
	public void testDeleteComplete() {
		sut.deleteComplete(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testGetCompleteException() {
		Assertions.assertThrows(CompleteNotFoundException.class, () -> {
			sut.getComplete(1L);
		}, "CompleteNotFoundException was expected");
	}
	
	@Test
	public void testGetComplete() {
		when(repository.findById(1L)).thenReturn(Optional.of(complete));
		sut.getComplete(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testUpdateCompleteException() {
		Assertions.assertThrows(CompleteNotFoundException.class, () -> {
			sut.updateComplete(1L, complete);
		}, "CompleteNotFoundException was expected");
	}
	
	@Test
	public void testUpdateComplete() {
		
		Complete myComplete = new Complete(deck, trucks, wheels, griptape, hardware);
		
		when(repository.findById(1L)).thenReturn(Optional.of(complete));
		sut.updateComplete(1L, myComplete);
		verify(repository, times(1)).findById(1L);
		verify(complete, times(1)).setDeck(deck);
		verify(complete, times(1)).setTrucks(trucks);
		verify(complete, times(1)).setWheels(wheels);
		verify(complete, times(1)).setGriptape(griptape);
		verify(complete, times(1)).setHardware(hardware);
		verify(repository, times(1)).save(complete);
	}
	
}
