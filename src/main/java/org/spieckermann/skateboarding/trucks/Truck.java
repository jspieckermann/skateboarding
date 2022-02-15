package org.spieckermann.skateboarding.trucks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "size"})
	)
@Entity
public class Truck {
	
	private @Id @GeneratedValue Long id;
	private Company company;
	private String name;
	private int size;
	private double width;
	private double height;
	private int weight;
	
	public Truck() {
		// do nothing
	}
	
	public Truck(Company company, String name, int size, double width, double height, int weight) {
		setCompany(company);
		setName(name);
		setSize(size);
		setWidth(width);
		setHeight(height);
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
	 * @return the company specific size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the company specific size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the width (inch)
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * @param width the width (inch) to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	/**
	 * @return the height (mm)
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param height the height (mm) to set
	 */
	public void setHeight(double height) {
		this.height = height;
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
