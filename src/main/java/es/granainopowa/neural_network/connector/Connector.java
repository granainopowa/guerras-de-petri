package es.granainopowa.neural_network.connector;

import java.util.Random;

import es.granainopowa.neural_network.neuron.Neuron;

/**
 * Connection between neurons
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class Connector {
	private static final Random RANDOM = new Random();

	private double weight;
	private Neuron inputNeuron;

	/**
	 * Sets a random weight in the interval (-1, 1)
	 */
	public Connector(Neuron inputNeuron) {
		this.weight = (RANDOM.nextDouble() * 2) - 1;
		this.inputNeuron = inputNeuron;
	}

	public final double getValue() {
		return getConnectorInput() * this.weight;
	}

	protected double getConnectorInput() {
		return inputNeuron.getOutput();
	}

}
