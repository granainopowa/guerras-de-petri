package es.granainopowa.neural_network.base;

import java.util.Random;

/**
 * Connection between neurons
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class Connector {
	public static final Random RANDOM = new Random();
	
	private double weight = 1;

	/**
	 * Sets a random weight in the interval (-1, 1)
	 */
	public void setRandomWeight() {
		this.weight = (RANDOM.nextDouble() * 2) - 1;
	}

	public double getValue(double input) {
		return input * this.weight;
	}

}
