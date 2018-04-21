package es.granainopowa.neural_network.connector;

import java.util.Random;

import es.granainopowa.neural_network.base.neuron.Neuron;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public abstract class Connector {

	private static final Random RANDOM = new Random();

	private double weight;

	/**
	 * Sets a random weight in the interval (-1, 1)
	 */
	public Connector() {
		this.weight = (RANDOM.nextDouble() * 2) - 1;
	}

	public final double getValue() {
		return getConnectorInput() * this.weight;
	}
	
	protected abstract double getConnectorInput();
}
