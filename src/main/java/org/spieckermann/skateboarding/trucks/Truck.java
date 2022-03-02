package org.spieckermann.skateboarding.trucks;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.BaseEntity;
import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "size"})
	)
@Entity
public class Truck extends BaseEntity {
	
	private int size;
	private double width;
	private double height;
	
	/**
	 * Default constructor.
	 */
	public Truck() { }
	
	/**
	 * Constructor.
	 * 
	 * @param company
	 * @param name
	 * @param size
	 * @param width (inch)
	 * @param height (mm)
	 * @param weight ({@link BaseEntity})
	 * @param price ({@link BaseEntity})
	 */
	public Truck(Company company, String name, int size, double width, double height, int weight, double price) {
		super(company, name, weight, price);
		setSize(size);
		setWidth(width);
		setHeight(height);
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

}
