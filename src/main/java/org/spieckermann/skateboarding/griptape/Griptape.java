package org.spieckermann.skateboarding.griptape;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.BaseEntity;
import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "width", "length"})
	)
@Entity
public class Griptape extends BaseEntity {
	
	private double length;
	private double width;
	
	/**
	 * Default constructor.
	 */
	public Griptape() { }
	
	/**
	 * Constructor.
	 * 
	 * @param company
	 * @param name
	 * @param length (inch)
	 * @param width (inch)
	 * @param weight ({@link BaseEntity})
	 * @param price ({@link BaseEntity})
	 */
	public Griptape(Company company, String name, double length, double width, int weight, double price) {
		super(company, name, weight, price);
		setLength(length);
		setWidth(width);
	}

	/**
	 * @return the length (inch)
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length (inch) to set
	 */
	public void setLength(double length) {
		this.length = length;
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

}
