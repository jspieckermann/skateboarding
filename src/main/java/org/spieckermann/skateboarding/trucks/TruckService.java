package org.spieckermann.skateboarding.trucks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckService {
	
	@Autowired
	private TruckRepository repository;
	
	public List<Truck> getTrucks() {		
		return repository.findAll();
	}
	
	public List<Truck> getTrucks(double width) {
		return repository.findByWidth(width);
	}
	
	public Truck getTruck(Long id) {
		return repository.findById(id).orElseThrow(() -> new TruckNotFoundException(id));
	}
	
	public Truck createTruck(Truck truck) {
		return repository.save(truck);	
	}
	
	public Truck updateTruck(Long id, Truck truck) {
		
		return repository.save(repository.findById(id).map(myTruck -> {
			myTruck.setCompany(truck.getCompany());
			myTruck.setName(truck.getName());
			myTruck.setSize(truck.getSize());
			myTruck.setWeight(truck.getWeight());
			myTruck.setHeight(truck.getHeight());
			myTruck.setWidth(truck.getWidth());
			return myTruck;
		}).orElseThrow(() -> new TruckNotFoundException(id)));
	}
	
	public void deleteTruck(Long id) {
		repository.deleteById(id);
	}

}
