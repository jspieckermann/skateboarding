package org.spieckermann.skateboarding.trucks;

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
public class TruckController {
	
	@Autowired
	private TruckService service;
	
	@GetMapping("/trucks")
	List<Truck> getTrucks(@RequestParam(required = false) String width) {
		return width == null ? service.getTrucks() : service.getTrucks(Double.parseDouble(width));
	}
	
	@GetMapping("/trucks/{id}")
	Truck getTruck(@PathVariable Long id) {
		return service.getTruck(id);
	}
	
	@PostMapping("/trucks")
	Truck createTruck(@RequestBody Truck truck) {
		return service.createTruck(truck);
	}
	
	@PutMapping("/trucks/{id}")
	Truck updateTruck(@PathVariable Long id, @RequestBody Truck truck) {
		return service.updateTruck(id, truck);
	}
	
	@DeleteMapping("/trucks/{id}")
	void deleteTruck(@PathVariable Long id) {
		service.deleteTruck(id);
	}

}
