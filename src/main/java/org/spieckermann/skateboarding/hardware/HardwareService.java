package org.spieckermann.skateboarding.hardware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardwareService {
	
	@Autowired
	private HardwareRepository repository;
	
	public List<Hardware> getHardware() {		
		return repository.findAll();
	}
	
	public List<Hardware> getHardware(Head head) {
		return repository.findByHead(head);
	}
	
	public Hardware getHardware(Long id) {
		return repository.findById(id).orElseThrow(() -> new HardwareNotFoundException(id));
	}
	
	public Hardware createHardware(Hardware hardware) {
		return repository.save(hardware);	
	}
	
	public Hardware updateHardware(Long id, Hardware hardware) {
		return repository.save(repository.findById(id).map(myHardware -> {
			myHardware.setCompany(hardware.getCompany());
			myHardware.setName(hardware.getName());
			myHardware.setHead(hardware.getHead());
			myHardware.setLength(hardware.getLength());
			myHardware.setWeight(hardware.getWeight());
			return myHardware;
		}).orElseThrow(() -> new HardwareNotFoundException(id)));
	}
	
	public void deleteHardware(Long id) {
		repository.deleteById(id);
	}

}
