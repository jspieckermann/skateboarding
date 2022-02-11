package org.spieckermann.skateboarding.brands;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Brand {
	
	private @Id @GeneratedValue Long id;
	private String name;
	
	public Brand() { }
	
	public Brand(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Name: " + name + " id: " + id;
	}
	

}
