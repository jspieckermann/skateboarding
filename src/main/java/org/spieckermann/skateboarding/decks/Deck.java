package org.spieckermann.skateboarding.decks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "width"})
	)
@Entity
public class Deck {
	
	private @Id @GeneratedValue Long id;
	private Company company;
	private String name;
	private double width;
	private double length;
	private double wheelbase;
	private Concave concave;
	private double nose;
	private double tail;
	private int weight;
	
	public Deck() {
		// do nothing
	}
	
	public Deck(Company company, String name, double width, double length, double wheelbase, Concave concave, double nose, double tail, int weight) {
		setCompany(company);
		setName(name);
		setWidth(width);
		setLength(length);
		setWheelbase(wheelbase);
		setConcave(concave);
		setNose(nose);
		setTail(tail);
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
