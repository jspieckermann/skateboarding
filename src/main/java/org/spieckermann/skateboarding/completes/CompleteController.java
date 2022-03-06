package org.spieckermann.skateboarding.completes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompleteController {
	
	@Autowired
	private CompleteService service;
	
	@GetMapping("/complete")
	List<Complete> getCompletes() {
		return service.getCompletes();
	}
	
	@GetMapping("/complete/{id}")
	Complete getComplete(@PathVariable Long id) {
		return service.getComplete(id);
	}
	
	@PostMapping("/complete")
	Complete createComplete(@RequestBody Complete complete) {
		return service.createComplete(complete);
	}
	
	@PutMapping("/complete/{id}")
	Complete updateComplete(@PathVariable Long id, @RequestBody Complete complete) {
		return service.updateComplete(id, complete);
	}
	
	@DeleteMapping("/complete/{id}")
	void deleteComplete(@PathVariable Long id) {
		service.deleteComplete(id);
	}

}
