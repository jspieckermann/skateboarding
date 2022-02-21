package org.spieckermann.skateboarding.hardware;

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
public class HardwareController {
	
	@Autowired
	private HardwareService service;
	
	@GetMapping("/hardware")
	List<Hardware> getHardware(@RequestParam(required = false) Head head) {
		return head == null ? service.getHardware() : service.getHardware(head);
	}
	
	@GetMapping("/hardware/{id}")
	Hardware getHardware(@PathVariable Long id) {
		return service.getHardware(id);
	}
	
	@PostMapping("/hardware")
	Hardware createHardware(@RequestBody Hardware hardware) {
		return service.createHardware(hardware);
	}
	
	@PutMapping("/hardware/{id}")
	Hardware updateHardware(@PathVariable Long id, @RequestBody Hardware hardware) {
		return service.updateHardware(id, hardware);
	}
	
	@DeleteMapping("/hardware/{id}")
	void deleteHardware(@PathVariable Long id) {
		service.deleteHardware(id);
	}

}
