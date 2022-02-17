package org.spieckermann.skateboarding.trucks;

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
public class TruckServiceTests {
	
	@InjectMocks
	private TruckService sut;
	
	@Mock
	private TruckRepository repository;
	
	@Mock
	private Truck truck;
	
	@Test
	public void testGetTrucks() {
		sut.getTrucks();
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void testGetTrucksByWidth() {
		sut.getTrucks(8.0);
		verify(repository, times(1)).findByWidth(8.0);
	}
	
	@Test
	public void testCreateTruck() {
		sut.createTruck(truck);
		verify(repository, times(1)).save(truck);
	}
	
	@Test
	public void testDeleteTruck() {
		sut.deleteTruck(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testGetTruckException() {
		Assertions.assertThrows(TruckNotFoundException.class, () -> {
			sut.getTruck(1L);
		}, "TruckNotFoundException was expected");
	}
	
	@Test
	public void testGetTruck() {
		when(repository.findById(1L)).thenReturn(Optional.of(truck));
		sut.getTruck(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testUpdateTruckException() {
		Assertions.assertThrows(TruckNotFoundException.class, () -> {
			sut.updateTruck(1L, truck);
		}, "TruckNotFoundException was expected");
	}
	
	@Test
	public void testUpdateTruck() {
		Truck myTruck = new Truck(Company.THUNDER, "Titanium Lights", 147, 8.0, 49, 288);
		
		when(repository.findById(1L)).thenReturn(Optional.of(truck));
		sut.updateTruck(1L, myTruck);
		verify(repository, times(1)).findById(1L);
		verify(truck, times(1)).setCompany(Company.THUNDER);
		verify(truck, times(1)).setName("Titanium Lights");
		verify(truck, times(1)).setSize(147);
		verify(truck, times(1)).setWidth(8.0);
		verify(truck, times(1)).setHeight(49);
		verify(truck, times(1)).setWeight(288);
		verify(repository, times(1)).save(truck);
	}
	
}
