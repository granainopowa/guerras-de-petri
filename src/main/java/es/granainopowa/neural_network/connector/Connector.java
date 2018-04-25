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

	private Neuron inputNeuron;
	private double weight;

	/**
	 * Sets a random weight in the interval (-1, 1)
	 */
	public Connector(Neuron inputNeuron) {
		this.weight = (RANDOM.nextDouble() * 2) - 1;
		this.inputNeuron = inputNeuron;
	}

	public Connector(Neuron inputNeuron, double weight) {
		this.weight = weight;
		this.inputNeuron = inputNeuron;
	}

	public Double getWeight() {
		return this.weight;
	}

	public final double getValue() {
		return inputNeuron.getOutput() * this.weight;
	}

}
