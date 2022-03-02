package org.spieckermann.skateboarding.decks;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.BaseEntity;
import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "width"})
	)
@Entity
public class Deck extends BaseEntity {
	
	private double width;
	private double length;
	private double wheelbase;
	private Concave concave;
	private double nose;
	private double tail;
	
	/**
	 * Default constructor.
	 */
	public Deck() { }
	
	/**
	 * Constructor.
	 * 
	 * @param company
	 * @param name
	 * @param width (inch)
	 * @param length (inch)
	 * @param wheelbase (inch)
	 * @param concave
	 * @param nose (inch)
	 * @param tail (inch)
	 * @param weight ({@link BaseEntity})
	 * @param price ({@link BaseEntity})
	 */
	public Deck(Company company, String name, double width, double length, double wheelbase, Concave concave, double nose, double tail, int weight, double price) {
		super(company, name, weight, price);
		setWidth(width);
		setLength(length);
		setWheelbase(wheelbase);
		setConcave(concave);
		setNose(nose);
		setTail(tail);
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
	 * @return the wheel base (inch)
	 */
	public double getWheelbase() {
		return wheelbase;
	}

	/**
	 * @param wheelbase the wheel base (inch) to set
	 */
	public void setWheelbase(double wheelbase) {
		this.wheelbase = wheelbase;
	}

	/**
	 * @return the concave
	 */
	public Concave getConcave() {
		return concave;
	}

	/**
	 * @param concave the concave to set
	 */
	public void setConcave(Concave concave) {
		this.concave = concave;
	}

	/**
	 * @return the nose (inch)
	 */
	public double getNose() {
		return nose;
	}

	/**
	 * @param nose the nose (inch) to set
	 */
	public void setNose(double nose) {
		this.nose = nose;
	}

	/**
	 * @return the tail (inch)
	 */
	public double getTail() {
		return tail;
	}

	/**
	 * @param tail the tail (inch) to set
	 */
	public void setTail(double tail) {
		this.tail = tail;
	}

}
