package org.spieckermann.skateboarding.hardware;

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
public class HardwareServiceTests {
	
	@InjectMocks
	private HardwareService sut;
	
	@Mock
	private HardwareRepository repository;
	
	@Mock
	private Hardware hardware;
	
	@Test
	public void testGetHardwareList() {
		sut.getHardware();
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void testGetHardwareByHead() {
		sut.getHardware(Head.ALLEN);
		verify(repository, times(1)).findByHead(Head.ALLEN);
	}
	
	@Test
	public void testCreateHardware() {
		sut.createHardware(hardware);
		verify(repository, times(1)).save(hardware);
	}
	
	@Test
	public void testDeleteHardware() {
		sut.deleteHardware(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testGetHardwareException() {
		Assertions.assertThrows(HardwareNotFoundException.class, () -> {
			sut.getHardware(1L);
		}, "HardwareNotFoundException was expected");
	}
	
	@Test
	public void testGetHardware() {
		when(repository.findById(1L)).thenReturn(Optional.of(hardware));
		sut.getHardware(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testUpdateHardwareException() {
		Assertions.assertThrows(HardwareNotFoundException.class, () -> {
			sut.updateHardware(1L, hardware);
		}, "HardwareNotFoundException was expected");
	}
	
	@Test
	public void testUpdateHardware() {
		Hardware myHardware = new Hardware(Company.ANTIX, "Standards", Head.ALLEN, 1.0, 42, 7.5);
		when(repository.findById(1L)).thenReturn(Optional.of(hardware));
		sut.updateHardware(1L, myHardware);
		verify(repository, times(1)).findById(1L);
		verify(hardware, times(1)).setCompany(Company.ANTIX);
		verify(hardware, times(1)).setName("Standards");
		verify(hardware, times(1)).setHead(Head.ALLEN);
		verify(hardware, times(1)).setLength(1.0);
		verify(hardware, times(1)).setWeight(42);
		verify(hardware, times(1)).setPrice(7.5);
		verify(repository, times(1)).save(hardware);
	}
	
}
