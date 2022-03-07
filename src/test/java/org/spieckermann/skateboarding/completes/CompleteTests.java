package org.spieckermann.skateboarding.completes;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spieckermann.skateboarding.decks.Deck;
import org.spieckermann.skateboarding.griptape.Griptape;
import org.spieckermann.skateboarding.hardware.Hardware;
import org.spieckermann.skateboarding.trucks.Truck;
import org.spieckermann.skateboarding.wheels.Wheel;

@ExtendWith(MockitoExtension.class)
public class CompleteTests {
	
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
	public void testGetPrice() {
		when(deck.getPrice()).thenReturn(80.0);
		when(trucks.getPrice()).thenReturn(120.0);
		when(wheels.getPrice()).thenReturn(50.0);
		when(griptape.getPrice()).thenReturn(10.0);
		when(hardware.getPrice()).thenReturn(5.0);
		Complete complete = new Complete(deck, trucks, wheels, griptape, hardware);
		Assertions.assertEquals(265.0, complete.getPrice());
	}
	
	@Test
	public void testGetWeight() {
		when(deck.getWeight()).thenReturn(1200);
		when(trucks.getWeight()).thenReturn(600);
		when(wheels.getWeight()).thenReturn(200);
		when(griptape.getWeight()).thenReturn(50);
		when(hardware.getWeight()).thenReturn(40);
		Complete complete = new Complete(deck, trucks, wheels, griptape, hardware);
		Assertions.assertEquals(2090, complete.getWeight());
	}

}
