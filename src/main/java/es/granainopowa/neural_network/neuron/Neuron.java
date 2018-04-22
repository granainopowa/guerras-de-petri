package es.granainopowa.neural_network.neuron;

import es.granainopowa.neural_network.model.ModelDTO;

/**
 * Neuron
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public abstract class Neuron implements ModelDTO {

	private double output;

	protected abstract double getInput();

	public double getOutput() {
		return output;
	}

	public final void computeOutput() {
		this.output = sigmoid(getInput());
	}

	private double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

}
