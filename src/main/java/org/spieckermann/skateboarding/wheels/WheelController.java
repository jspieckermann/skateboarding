package org.spieckermann.skateboarding.wheels;

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
public class WheelController {
	
	@Autowired
	private WheelService service;
	
	@GetMapping("/wheels")
	List<Wheel> getWheels(@RequestParam(required = false) String size) {
		return size == null ? service.getWheels() : service.getWheels(Integer.parseInt(size));
	}
	
	@GetMapping("/wheels/{id}")
	Wheel getWheel(@PathVariable Long id) {
		return service.getWheel(id);
	}
	
	@PostMapping("/wheels")
	Wheel createWheel(@RequestBody Wheel wheel) {
		return service.createWheel(wheel);
	}
	
	@PutMapping("/wheels/{id}")
	Wheel updateWheels(@PathVariable Long id, @RequestBody Wheel wheel) {
		return service.updateWheel(id, wheel);
	}
	
	@DeleteMapping("/wheels/{id}")
	void deleteWheel(@PathVariable Long id) {
		service.deleteWheel(id);
	}

}
