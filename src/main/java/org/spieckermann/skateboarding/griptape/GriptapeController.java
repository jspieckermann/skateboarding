package org.spieckermann.skateboarding.griptape;

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
public class GriptapeController {
	
	@Autowired
	private GriptapeService service;
	
	@GetMapping("/griptape")
	List<Griptape> getGriptape() {
		return service.getGriptape();
	}
	
	@GetMapping("/griptape/{id}")
	Griptape getGriptape(@PathVariable Long id) {
		return service.getGriptape(id);
	}
	
	@PostMapping("/griptape")
	Griptape createGriptape(@RequestBody Griptape gripetape) {
		return service.createGriptape(gripetape);
	}
	
	@PutMapping("/griptape/{id}")
	Griptape updateGriptape(@PathVariable Long id, @RequestBody Griptape griptape) {
		return service.updateGriptape(id, griptape);
	}
	
	@DeleteMapping("/griptape/{id}")
	void deleteGriptape(@PathVariable Long id) {
		service.deleteGriptape(id);
	}

}
