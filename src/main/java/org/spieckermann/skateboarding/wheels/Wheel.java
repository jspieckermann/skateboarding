package org.spieckermann.skateboarding.wheels;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.BaseEntity;
import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "size", "duro"})
	)
@Entity
public class Wheel extends BaseEntity {
	
	private int size;
	private double width;
	private double ridingSurface;
	private String duro;

	/**
	 * Default constructor.
	 */
	public Wheel() { }
	
	/**
	 * Constructor.
	 * 
	 * @param company
	 * @param name
	 * @param size
	 * @param width (inch)
	 * @param ridingSurface (mm)
	 * @param duro
	 * @param weight ({@link BaseEntity})
	 * @param price ({@link BaseEntity})
	 */
	public Wheel(Company company, String name, int size, double width, double ridingSurface, String duro, int weight, double price) {
		super(company, name, weight, price);
		setSize(size);
		setWidth(width);
		setRidingSurface(ridingSurface);
		setDuro(duro);
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

}
