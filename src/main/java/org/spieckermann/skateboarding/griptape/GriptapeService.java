package org.spieckermann.skateboarding.griptape;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GriptapeService {
	
	@Autowired
	private GriptapeRepository repository;
	
	public List<Griptape> getGriptape() {		
		return repository.findAll();
	}
	
	public Griptape getGriptape(Long id) {
		return repository.findById(id).orElseThrow(() -> new GriptapeNotFoundException(id));
	}
	
	public Griptape createGriptape(Griptape griptape) {
		return repository.save(griptape);	
	}
	
	public Griptape updateGriptape(Long id, Griptape griptape) {
		
		return repository.save(repository.findById(id).map(myGriptape -> {
			myGriptape.setCompany(griptape.getCompany());
			myGriptape.setName(griptape.getName());
			myGriptape.setWidth(griptape.getWidth());
			myGriptape.setLength(griptape.getLength());
			myGriptape.setWeight(griptape.getWeight());
			myGriptape.setPrice(griptape.getPrice());
			return myGriptape;
		}).orElseThrow(() -> new GriptapeNotFoundException(id)));
	}
	
	public void deleteGriptape(Long id) {
		repository.deleteById(id);
	}
}
