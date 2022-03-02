package org.spieckermann.skateboarding.wheels;

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
public class WheelServiceTests {
	
	@InjectMocks
	private WheelService sut;
	
	@Mock
	private WheelRepository repository;
	
	@Mock
	private Wheel wheel;
	
	@Test
	public void testGetWheels() {
		sut.getWheels();
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void testGetWheelsBySize() {
		sut.getWheels(53);
		verify(repository, times(1)).findBySize(53);
	}
	
	@Test
	public void testCreateWheel() {
		sut.createWheel(wheel);
		verify(repository, times(1)).save(wheel);
	}
	
	@Test
	public void testDeleteWheel() {
		sut.deleteWheel(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testGetWheelException() {
		Assertions.assertThrows(WheelNotFoundException.class, () -> {
			sut.getWheel(1L);
		}, "WheelNotFoundException was expected");
	}
	
	@Test
	public void testGetWheel() {
		when(repository.findById(1L)).thenReturn(Optional.of(wheel));
		sut.getWheel(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testUpdateWheelException() {
		Assertions.assertThrows(WheelNotFoundException.class, () -> {
			sut.updateWheel(1L, wheel);
		}, "WheelNotFoundException was expected");
	}
	
	@Test
	public void testUpdateWheel() {
		Wheel myWheel = new Wheel(Company.SPITFIRE, "F4 Conical Full", 53, 33.5, 21.5, "99", 225, 59);
		
		when(repository.findById(1L)).thenReturn(Optional.of(wheel));
		sut.updateWheel(1L, myWheel);
		verify(repository, times(1)).findById(1L);
		verify(wheel, times(1)).setCompany(Company.SPITFIRE);
		verify(wheel, times(1)).setName("F4 Conical Full");
		verify(wheel, times(1)).setSize(53);
		verify(wheel, times(1)).setWidth(33.5);
		verify(wheel, times(1)).setRidingSurface(21.5);
		verify(wheel, times(1)).setDuro("99");
		verify(wheel, times(1)).setWeight(225);
		verify(repository, times(1)).save(wheel);
	}
	
}
