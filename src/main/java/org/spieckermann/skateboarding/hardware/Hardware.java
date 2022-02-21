package org.spieckermann.skateboarding.hardware;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "head", "length"})
	)
@Entity
public class Hardware {
	
	private @Id @GeneratedValue Long id;
	private Company company;
	private String name;
	private Head head;
	private double length;
	private int weight;
	
	public Hardware() {
		// do nothing
	}
	
	public Hardware(Company company, String name, Head head, double length, int weight) {
		setCompany(company);
		setName(name);
		setHead(head);
		setLength(length);
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
	 * @return the head
	 */
	public Head getHead() {
		return head;
	}
	
	/**
	 * @param head the head to set
	 */
	public void setHead(Head head) {
		this.head = head;
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

}
