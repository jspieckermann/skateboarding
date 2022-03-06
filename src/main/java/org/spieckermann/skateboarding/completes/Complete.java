package org.spieckermann.skateboarding.completes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.spieckermann.skateboarding.decks.Deck;
import org.spieckermann.skateboarding.griptape.Griptape;
import org.spieckermann.skateboarding.hardware.Hardware;
import org.spieckermann.skateboarding.trucks.Truck;
import org.spieckermann.skateboarding.wheels.Wheel;

@Entity
public class Complete {

	private @Id @GeneratedValue Long id;
	@OneToOne
	private Deck deck;
	@OneToOne
	private Truck trucks;
	@OneToOne
	private Wheel wheels;
	@OneToOne
	private Griptape griptape;
	@OneToOne
	private Hardware hardware;

	/**
	 * Default constructor.
	 */
	public Complete() {
	}

	/**
	 * Constructor.
	 * 
	 * @param deck
	 * @param trucks
	 * @param wheels
	 * @param griptape
	 * @param hardware
	 */
	public Complete(Deck deck, Truck trucks, Wheel wheels, Griptape griptape, Hardware hardware) {
		setDeck(deck);
		setTrucks(trucks);
		setWheels(wheels);
		setGriptape(griptape);
		setHardware(hardware);
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * @return the trucks
	 */
	public Truck getTrucks() {
		return trucks;
	}

	/**
	 * @param trucks the trucks to set
	 */
	public void setTrucks(Truck trucks) {
		this.trucks = trucks;
	}

	/**
	 * @return the wheels
	 */
	public Wheel getWheels() {
		return wheels;
	}

	/**
	 * @param wheels the wheels to set
	 */
	public void setWheels(Wheel wheels) {
		this.wheels = wheels;
	}

	/**
	 * @return the griptape
	 */
	public Griptape getGriptape() {
		return griptape;
	}

	/**
	 * @param griptape the griptape to set
	 */
	public void setGriptape(Griptape griptape) {
		this.griptape = griptape;
	}

	/**
	 * @return the hardware
	 */
	public Hardware getHardware() {
		return hardware;
	}

	/**
	 * @param hardware the hardware to set
	 */
	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the accumulated weight (gram) of all components
	 */
	public int getWeight() {
		return deck.getWeight() + trucks.getWeight() + wheels.getWeight() + griptape.getWeight() + hardware.getWeight();
	}
	
	/**
	 * @return the accumulated price (€) of all components
	 */
	public double getPrice() {
		return deck.getPrice() + trucks.getPrice() + wheels.getPrice() + griptape.getPrice() + hardware.getPrice();
	}

}
