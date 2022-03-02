package org.spieckermann.skateboarding.griptape;

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
	private GriptapeService sut;
	
	@Mock
	private GriptapeRepository repository;
	
	@Mock
	private Griptape griptape;
	
	@Test
	public void testGetGriptapeList() {
		sut.getGriptape();
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void testCreateGriptape() {
		sut.createGriptape(griptape);
		verify(repository, times(1)).save(griptape);
	}
	
	@Test
	public void testDeleteGriptape() {
		sut.deleteGriptape(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testGetGriptapeException() {
		Assertions.assertThrows(GriptapeNotFoundException.class, () -> {
			sut.getGriptape(1L);
		}, "GriptapeNotFoundException was expected");
	}
	
	@Test
	public void testGetGriptape() {
		when(repository.findById(1L)).thenReturn(Optional.of(griptape));
		sut.getGriptape(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testUpdateGriptapeException() {
		Assertions.assertThrows(GriptapeNotFoundException.class, () -> {
			sut.updateGriptape(1L, griptape);
		}, "GriptapeNotFoundException was expected");
	}
	
	@Test
	public void testUpdateGriptape() {
		Griptape myGriptape = new Griptape(Company.GRIZZLY, "Santiago Signature Stamp", 33, 9, 34, 11);
		when(repository.findById(1L)).thenReturn(Optional.of(griptape));
		sut.updateGriptape(1L, myGriptape);
		verify(repository, times(1)).findById(1L);
		verify(griptape, times(1)).setCompany(Company.GRIZZLY);
		verify(griptape, times(1)).setName("Santiago Signature Stamp");
		verify(griptape, times(1)).setLength(33);
		verify(griptape, times(1)).setWidth(9);
		verify(griptape, times(1)).setWeight(34);
		verify(griptape, times(1)).setPrice(11);
		verify(repository, times(1)).save(griptape);
	}
	
}
