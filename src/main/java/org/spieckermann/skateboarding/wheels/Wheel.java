package org.spieckermann.skateboarding.wheels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "size", "duro"})
	)
@Entity
public class Wheel {
	
	private @Id @GeneratedValue Long id;
	private Company company;
	private String name;
	private int size;
	private double width;
	private double ridingSurface;
	private String duro;
	private int weight;
	
	public Wheel() {
		// do nothing
	}
	
	public Wheel(Company company, String name, int size, double width, double ridingSurface, String duro, int weight) {
		setCompany(company);
		setName(name);
		setSize(size);
		setWidth(width);
		setRidingSurface(ridingSurface);
		setDuro(duro);
		setWeight(weight);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the size (mm)
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @param size the size (mm) to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * @return the width (mm)
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * @param width the width (mm) to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * @return the riding surface (mm)
	 */
	public double getRidingSurface() {
		return ridingSurface;
	}
	
	/**
	 * @param ridingSurface the riding surface (mm) to set
	 */
	public void setRidingSurface(double ridingSurface) {
		this.ridingSurface = ridingSurface;
	}
	
	/**
	 * @return the durometer
	 */
	public String getDuro() {
		return duro;
	}
	/**
	 * @param duro the durometer to set
	 */
	public void setDuro(String duro) {
		this.duro = duro;
	}
	/**
	 * @return the weight (gram)
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight (gram) to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}


}
