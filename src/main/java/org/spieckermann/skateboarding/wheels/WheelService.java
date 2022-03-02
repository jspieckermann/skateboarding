package org.spieckermann.skateboarding.wheels;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WheelService {
	
	@Autowired
	private WheelRepository repository;
	
	public List<Wheel> getWheels() {		
		return repository.findAll();
	}
	
	public List<Wheel> getWheels(int size) {
		return repository.findBySize(size);
	}
	
	public Wheel getWheel(Long id) {
		return repository.findById(id).orElseThrow(() -> new WheelNotFoundException(id));
	}
	
	public Wheel createWheel(Wheel wheel) {
		return repository.save(wheel);	
	}
	
	public Wheel updateWheel(Long id, Wheel wheel) {
		
		return repository.save(repository.findById(id).map(myWheel -> {
			myWheel.setCompany(wheel.getCompany());
			myWheel.setName(wheel.getName());
			myWheel.setSize(wheel.getSize());
			myWheel.setWeight(wheel.getWeight());
			myWheel.setRidingSurface(wheel.getRidingSurface());
			myWheel.setDuro(wheel.getDuro());
			myWheel.setWidth(wheel.getWidth());
			myWheel.setPrice(wheel.getPrice());
			return myWheel;
		}).orElseThrow(() -> new WheelNotFoundException(id)));
	}
	
	public void deleteWheel(Long id) {
		repository.deleteById(id);
	}

}
