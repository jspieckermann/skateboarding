package org.spieckermann.skateboarding.completes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompleteService {

	@Autowired
	private CompleteRepository repository;
	
	public List<Complete> getCompletes() {		
		return repository.findAll();
	}
	
	public Complete getComplete(Long id) {
		return repository.findById(id).orElseThrow(() -> new CompleteNotFoundException(id));
	}
	
	public Complete createComplete(Complete complete) {
		return repository.save(complete);	
	}
	
	public Complete updateComplete(Long id, Complete complete) {
		
		return repository.save(repository.findById(id).map(myComplete -> {
			myComplete.setDeck(complete.getDeck());
			myComplete.setTrucks(complete.getTrucks());
			myComplete.setWheels(complete.getWheels());
			myComplete.setGriptape(complete.getGriptape());
			myComplete.setHardware(complete.getHardware());
			return myComplete;
		}).orElseThrow(() -> new CompleteNotFoundException(id)));
	}
	
	public void deleteComplete(Long id) {
		repository.deleteById(id);
	}
	
}
