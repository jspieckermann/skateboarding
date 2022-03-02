package org.spieckermann.skateboarding;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.spieckermann.skateboarding.company.Company;

@MappedSuperclass
public abstract class BaseEntity {
	
	private @Id @GeneratedValue Long id;
	private Company company;
	private String name;
	private int weight;
	private double price;
	
	/**
	 * Default constructor.
	 */
	public BaseEntity() { }
	
	/**
	 * Constructor.
	 * 
	 * @param company
	 * @param name
	 * @param weight (gram)
	 * @param price (€)
	 */
	public BaseEntity(Company company, String name, int weight, double price) {
		setCompany(company);
		setName(name);
		setWeight(weight);
		setPrice(price);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the price (€)
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price (€) to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}


}
