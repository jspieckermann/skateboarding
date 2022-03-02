package org.spieckermann.skateboarding.hardware;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spieckermann.skateboarding.BaseEntity;
import org.spieckermann.skateboarding.company.Company;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"company", "name", "head", "length"})
	)
@Entity
public class Hardware extends BaseEntity {
	
	private Head head;
	private double length;
	
	/**
	 * Default constructor.
	 */
	public Hardware() { }
	
	/**
	 * Constructor.
	 * 
	 * @param company
	 * @param name
	 * @param head
	 * @param length (inch)
	 * @param weight ({@link BaseEntity})
	 * @param price ({@link BaseEntity})
	 */
	public Hardware(Company company, String name, Head head, double length, int weight, double price) {
		super(company, name, weight, price);
		setHead(head);
		setLength(length);
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
